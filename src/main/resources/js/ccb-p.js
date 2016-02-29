/**
 * 建设银行.个人网银
 * @author Jony Zhang
 * @required bridge.js
 */
var callbackJava = arguments[arguments.length - 1];

(function(){
    var _X = top._X || {};
    var mainFrame = $('iframe[src*="TXCODE=N31010"]').get(0);
    var dataFrame = $('iframe[src*="TXCODE=N41001"]').get(0);
    var listData = [];
    var USER = {
            USERID: DAT_USERBASE.USERID,
            NAME: DAT_USERBASE.NAME,
            BRANCHID: DAT_USERBASE.BRANCHID,
            SKEY: DAT_USERBASE.SKEY
        };

    // 队列
    var Q = [];
    Q.done = Q.next = function() {this.length && this.shift()()};
    Q.push(start, getAccountInfo, nav2details, fetchAccounts, complete);
    Q.next();

    // 任务开始
    function start() {
        start.startTime = new Date().getTime();
        _X.log('[start]: '+start.startTime);
        Q.done();
    }

    // 任务结束
    function complete() {
        start.endTime = new Date().getTime();

        _X.log('[end]: ' + start.endTime);
        _X.log('[totle]: ' + (start.endTime - start.startTime) / 1000 + 's');

        _X.log(listData);
        callbackJava(JSON.stringify(listData));
        Q.done();
    }

    // 获取账户信息
    function getAccountInfo() {
        var win = mainFrame.contentWindow;
        _X.log('getAccountInfo');
        win.$('span.hide_mey')
            .each(function(){
                //lct|6227007200020637858|442000000||01
                var values = this.getAttribute('values'), arr;
                if (!values) return;
                arr = values.split('|');
                listData.push({
                    bankName: '\u5efa\u8bbe\u94f6\u884c',
                    accountName: USER.NAME,
                    account: arr[1],
                    currency: 'CNY',
                    balance: getBalance(arr[1], arr[0], arr[4]),
                    startDate: _X.params.startDate || _X.formatDate(null, '-2m', ''),
                    endDate: _X.params.endDate || _X.formatDate(null, '')
                });
            });

        // 获取余额
        function getBalance(ACC_NO, accType, CUR_CODE) {
            var TXCODE, ret;
            var url = '/CCBIS/B2CMainPlat_03?SERVLET_NAME=B2CMainPlat_03&CCB_IBSVersion=V6&PT_STYLE=1&';
            var data = {
                    isAjaxRequest:true,
                    TXCODE: 'N31002',
                    SKEY: USER.SKEY,
                    USERID: USER.USERID,
                    BRANCHID: USER.BRANCHID,
                    ACC_NO: ACC_NO,
                    SEND_USERID: ""
                };
            switch (accType) {
                case 'xyk':
                    data.TXCODE = 'N31004';
                    break;
                case 'dkzh':
                    data.TXCODE = 'N31012';
                    data.CUR_CODE = CUR_CODE;
                    break;
                default:
                    data.TXCODE = 'N31002';
            }
            url += win.$.param(data);

            win.$.ajax({
                url: url,
                type: 'post',
                async: false,
                success: function(d) {
                    var data = JSON.parse(d);
                    ret = data.yu_e;
                }
            });
            return ret;
        }

        Q.done();
    }

    // 导航到交易明细（改写弹出窗口为：直接 iframe 打开）
    function nav2details() {
        var $ = mainFrame.contentWindow.$,
            url;

        // 生成明细 URL
        $('span[data_id="mingxi"]').each(function(){
            var arr = this.getAttribute('values').split('|'),
                acc_no = arr[0],
                branchid = arr[1],
                bbranchid = arr[2],
                acc_type = arr[3],
                SEND_USERID = arr[4];

            // 不处理他行卡
            if (arr[3] === 'th') return;

            url ='/CCBIS/B2CMainPlat_03?SERVLET_NAME=B2CMainPlat_03&CCB_IBSVersion=V6&PT_STYLE=1&TXCODE=310203';
            url += '&SKEY='+ USER.SKEY +'&USERID='+ USER.USERID + '&STR_USERID='+ USER.USERID;
            url += '&BRANCHID='+ branchid +'&ACC_NO='+ acc_no;
            url += '&FLAG_CARD=0&BANK_NAME='+bbranchid+'&ACC_SIGN='+acc_no+'&SEND_USERID='+SEND_USERID;
        });

        _X.log('flowURL: '+url);

        _X.on('load', dataFrame, completePage);
        function completePage() {
            _X.off('load', dataFrame, completePage);
            // 内嵌的第一个 iframe 才是内容主体
            dataFrame = dataFrame.contentWindow.document.getElementsByTagName('iframe')[0];
            Q.done();
        }

        dataFrame.contentWindow.location.href = url;
    }

    // 查询多账户
    function fetchAccounts(index) {
        var len = listData.length, obj;
        index = index || 0;
        obj = listData[index];
        // 如果还没有下载链接
        if (!obj.download) {
            getFlowData(obj.account, function(){
                obj.download = getDownloadLink(obj.account, obj.startDate, obj.endDate);
            });
        }

        // 全部账号完成
        if (index >= len-1) {
            Q.done();
        } else {
            fetchAccounts(index++);
        }
    }


    // 查询交易明细
    function getFlowData(cardNo, callback) {
        var win = dataFrame.contentWindow;
        var resultFrame = win.document.getElementById('result');
        var endDate = win.document.getElementById('END_DATE').value;

        _X.on('load', resultFrame, completePage);
        function completePage() {
            _X.off('load', resultFrame, completePage);
            callback();
        }

        // 设置卡号
        win.document.getElementById('ACC_NO').value = cardNo;
        // 设置开始日期
        win.document.getElementById('START_DATE').value = _X.formatDate(endDate, '-1y', '');
        // 提交查询
        win.document.getElementById('qd1').click();
    }

    // 生成下载链接
    function getDownloadLink(cardNo, startDate, endDate) {
        var $ = dataFrame.contentWindow.$;
        var url = " https://ibsbjstar.ccb.com.cn/CCBIS/B2CMainPlat_03";
        var data = {
                "ACC_NO": cardNo,
                "SKEY": USER.SKEY,
                "USERID": USER.USERID,
                "BRANCHID": USER.BRANCHID,
                "TXCODE": "310206",
                "FILESEARCHSTR": $('#FILESEARCHSTR').val(),
                "clientFileName": '\u4ea4\u6613\u660e\u7ec6'+ '_'+ cardNo.slice(-4) +'_'+ startDate +'_'+ endDate +'.xls',
                "ACC_SIGN": "E0000000000010",
                "START_DATE": startDate,
                "END_DATE": endDate,
                "LUGANGTONG": 0,
                "PAGE": 1
            };
        url += '?' + $.param(data);
        return url;
    }

})();