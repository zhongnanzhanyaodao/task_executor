/**
 * 网银查询.基础脚本
 * @author Jony Zhang
 */

/*! JSON v3.3.2 | http://bestiejs.github.io/json3 | Copyright 2012-2014, Kit Cambridge | http://kit.mit-license.org */
(function(){function N(p,r){function q(a){if(q[a]!==w)return q[a];var c;if("bug-string-char-index"==a)c="a"!="a"[0];else if("json"==a)c=q("json-stringify")&&q("json-parse");else{var e;if("json-stringify"==a){c=r.stringify;var b="function"==typeof c&&s;if(b){(e=function(){return 1}).toJSON=e;try{b="0"===c(0)&&"0"===c(new t)&&'""'==c(new A)&&c(u)===w&&c(w)===w&&c()===w&&"1"===c(e)&&"[1]"==c([e])&&"[null]"==c([w])&&"null"==c(null)&&"[null,null,null]"==c([w,u,null])&&'{"a":[1,true,false,null,"\\u0000\\b\\n\\f\\r\\t"]}'==
c({a:[e,!0,!1,null,"\x00\b\n\f\r\t"]})&&"1"===c(null,e)&&"[\n 1,\n 2\n]"==c([1,2],null,1)&&'"-271821-04-20T00:00:00.000Z"'==c(new C(-864E13))&&'"+275760-09-13T00:00:00.000Z"'==c(new C(864E13))&&'"-000001-01-01T00:00:00.000Z"'==c(new C(-621987552E5))&&'"1969-12-31T23:59:59.999Z"'==c(new C(-1))}catch(f){b=!1}}c=b}if("json-parse"==a){c=r.parse;if("function"==typeof c)try{if(0===c("0")&&!c(!1)){e=c('{"a":[1,true,false,null,"\\u0000\\b\\n\\f\\r\\t"]}');var n=5==e.a.length&&1===e.a[0];if(n){try{n=!c('"\t"')}catch(d){}if(n)try{n=
1!==c("01")}catch(g){}if(n)try{n=1!==c("1.")}catch(m){}}}}catch(X){n=!1}c=n}}return q[a]=!!c}p||(p=k.Object());r||(r=k.Object());var t=p.Number||k.Number,A=p.String||k.String,H=p.Object||k.Object,C=p.Date||k.Date,G=p.SyntaxError||k.SyntaxError,K=p.TypeError||k.TypeError,L=p.Math||k.Math,I=p.JSON||k.JSON;"object"==typeof I&&I&&(r.stringify=I.stringify,r.parse=I.parse);var H=H.prototype,u=H.toString,v,B,w,s=new C(-0xc782b5b800cec);try{s=-109252==s.getUTCFullYear()&&0===s.getUTCMonth()&&1===s.getUTCDate()&&
10==s.getUTCHours()&&37==s.getUTCMinutes()&&6==s.getUTCSeconds()&&708==s.getUTCMilliseconds()}catch(Q){}if(!q("json")){var D=q("bug-string-char-index");if(!s)var x=L.floor,M=[0,31,59,90,120,151,181,212,243,273,304,334],E=function(a,c){return M[c]+365*(a-1970)+x((a-1969+(c=+(1<c)))/4)-x((a-1901+c)/100)+x((a-1601+c)/400)};(v=H.hasOwnProperty)||(v=function(a){var c={},e;(c.__proto__=null,c.__proto__={toString:1},c).toString!=u?v=function(a){var c=this.__proto__;a=a in(this.__proto__=null,this);this.__proto__=
c;return a}:(e=c.constructor,v=function(a){var c=(this.constructor||e).prototype;return a in this&&!(a in c&&this[a]===c[a])});c=null;return v.call(this,a)});B=function(a,c){var e=0,b,f,n;(b=function(){this.valueOf=0}).prototype.valueOf=0;f=new b;for(n in f)v.call(f,n)&&e++;b=f=null;e?B=2==e?function(a,c){var e={},b="[object Function]"==u.call(a),f;for(f in a)b&&"prototype"==f||v.call(e,f)||!(e[f]=1)||!v.call(a,f)||c(f)}:function(a,c){var e="[object Function]"==u.call(a),b,f;for(b in a)e&&"prototype"==
b||!v.call(a,b)||(f="constructor"===b)||c(b);(f||v.call(a,b="constructor"))&&c(b)}:(f="valueOf toString toLocaleString propertyIsEnumerable isPrototypeOf hasOwnProperty constructor".split(" "),B=function(a,c){var e="[object Function]"==u.call(a),b,h=!e&&"function"!=typeof a.constructor&&F[typeof a.hasOwnProperty]&&a.hasOwnProperty||v;for(b in a)e&&"prototype"==b||!h.call(a,b)||c(b);for(e=f.length;b=f[--e];h.call(a,b)&&c(b));});return B(a,c)};if(!q("json-stringify")){var U={92:"\\\\",34:'\\"',8:"\\b",
12:"\\f",10:"\\n",13:"\\r",9:"\\t"},y=function(a,c){return("000000"+(c||0)).slice(-a)},R=function(a){for(var c='"',b=0,h=a.length,f=!D||10<h,n=f&&(D?a.split(""):a);b<h;b++){var d=a.charCodeAt(b);switch(d){case 8:case 9:case 10:case 12:case 13:case 34:case 92:c+=U[d];break;default:if(32>d){c+="\\u00"+y(2,d.toString(16));break}c+=f?n[b]:a.charAt(b)}}return c+'"'},O=function(a,c,b,h,f,n,d){var g,m,k,l,p,r,s,t,q;try{g=c[a]}catch(z){}if("object"==typeof g&&g)if(m=u.call(g),"[object Date]"!=m||v.call(g,
"toJSON"))"function"==typeof g.toJSON&&("[object Number]"!=m&&"[object String]"!=m&&"[object Array]"!=m||v.call(g,"toJSON"))&&(g=g.toJSON(a));else if(g>-1/0&&g<1/0){if(E){l=x(g/864E5);for(m=x(l/365.2425)+1970-1;E(m+1,0)<=l;m++);for(k=x((l-E(m,0))/30.42);E(m,k+1)<=l;k++);l=1+l-E(m,k);p=(g%864E5+864E5)%864E5;r=x(p/36E5)%24;s=x(p/6E4)%60;t=x(p/1E3)%60;p%=1E3}else m=g.getUTCFullYear(),k=g.getUTCMonth(),l=g.getUTCDate(),r=g.getUTCHours(),s=g.getUTCMinutes(),t=g.getUTCSeconds(),p=g.getUTCMilliseconds();
g=(0>=m||1E4<=m?(0>m?"-":"+")+y(6,0>m?-m:m):y(4,m))+"-"+y(2,k+1)+"-"+y(2,l)+"T"+y(2,r)+":"+y(2,s)+":"+y(2,t)+"."+y(3,p)+"Z"}else g=null;b&&(g=b.call(c,a,g));if(null===g)return"null";m=u.call(g);if("[object Boolean]"==m)return""+g;if("[object Number]"==m)return g>-1/0&&g<1/0?""+g:"null";if("[object String]"==m)return R(""+g);if("object"==typeof g){for(a=d.length;a--;)if(d[a]===g)throw K();d.push(g);q=[];c=n;n+=f;if("[object Array]"==m){k=0;for(a=g.length;k<a;k++)m=O(k,g,b,h,f,n,d),q.push(m===w?"null":
m);a=q.length?f?"[\n"+n+q.join(",\n"+n)+"\n"+c+"]":"["+q.join(",")+"]":"[]"}else B(h||g,function(a){var c=O(a,g,b,h,f,n,d);c!==w&&q.push(R(a)+":"+(f?" ":"")+c)}),a=q.length?f?"{\n"+n+q.join(",\n"+n)+"\n"+c+"}":"{"+q.join(",")+"}":"{}";d.pop();return a}};r.stringify=function(a,c,b){var h,f,n,d;if(F[typeof c]&&c)if("[object Function]"==(d=u.call(c)))f=c;else if("[object Array]"==d){n={};for(var g=0,k=c.length,l;g<k;l=c[g++],(d=u.call(l),"[object String]"==d||"[object Number]"==d)&&(n[l]=1));}if(b)if("[object Number]"==
(d=u.call(b))){if(0<(b-=b%1))for(h="",10<b&&(b=10);h.length<b;h+=" ");}else"[object String]"==d&&(h=10>=b.length?b:b.slice(0,10));return O("",(l={},l[""]=a,l),f,n,h,"",[])}}if(!q("json-parse")){var V=A.fromCharCode,W={92:"\\",34:'"',47:"/",98:"\b",116:"\t",110:"\n",102:"\f",114:"\r"},b,J,l=function(){b=J=null;throw G();},z=function(){for(var a=J,c=a.length,e,h,f,k,d;b<c;)switch(d=a.charCodeAt(b),d){case 9:case 10:case 13:case 32:b++;break;case 123:case 125:case 91:case 93:case 58:case 44:return e=
D?a.charAt(b):a[b],b++,e;case 34:e="@";for(b++;b<c;)if(d=a.charCodeAt(b),32>d)l();else if(92==d)switch(d=a.charCodeAt(++b),d){case 92:case 34:case 47:case 98:case 116:case 110:case 102:case 114:e+=W[d];b++;break;case 117:h=++b;for(f=b+4;b<f;b++)d=a.charCodeAt(b),48<=d&&57>=d||97<=d&&102>=d||65<=d&&70>=d||l();e+=V("0x"+a.slice(h,b));break;default:l()}else{if(34==d)break;d=a.charCodeAt(b);for(h=b;32<=d&&92!=d&&34!=d;)d=a.charCodeAt(++b);e+=a.slice(h,b)}if(34==a.charCodeAt(b))return b++,e;l();default:h=
b;45==d&&(k=!0,d=a.charCodeAt(++b));if(48<=d&&57>=d){for(48==d&&(d=a.charCodeAt(b+1),48<=d&&57>=d)&&l();b<c&&(d=a.charCodeAt(b),48<=d&&57>=d);b++);if(46==a.charCodeAt(b)){for(f=++b;f<c&&(d=a.charCodeAt(f),48<=d&&57>=d);f++);f==b&&l();b=f}d=a.charCodeAt(b);if(101==d||69==d){d=a.charCodeAt(++b);43!=d&&45!=d||b++;for(f=b;f<c&&(d=a.charCodeAt(f),48<=d&&57>=d);f++);f==b&&l();b=f}return+a.slice(h,b)}k&&l();if("true"==a.slice(b,b+4))return b+=4,!0;if("false"==a.slice(b,b+5))return b+=5,!1;if("null"==a.slice(b,
b+4))return b+=4,null;l()}return"$"},P=function(a){var c,b;"$"==a&&l();if("string"==typeof a){if("@"==(D?a.charAt(0):a[0]))return a.slice(1);if("["==a){for(c=[];;b||(b=!0)){a=z();if("]"==a)break;b&&(","==a?(a=z(),"]"==a&&l()):l());","==a&&l();c.push(P(a))}return c}if("{"==a){for(c={};;b||(b=!0)){a=z();if("}"==a)break;b&&(","==a?(a=z(),"}"==a&&l()):l());","!=a&&"string"==typeof a&&"@"==(D?a.charAt(0):a[0])&&":"==z()||l();c[a.slice(1)]=P(z())}return c}l()}return a},T=function(a,b,e){e=S(a,b,e);e===
w?delete a[b]:a[b]=e},S=function(a,b,e){var h=a[b],f;if("object"==typeof h&&h)if("[object Array]"==u.call(h))for(f=h.length;f--;)T(h,f,e);else B(h,function(a){T(h,a,e)});return e.call(a,b,h)};r.parse=function(a,c){var e,h;b=0;J=""+a;e=P(z());"$"!=z()&&l();b=J=null;return c&&"[object Function]"==u.call(c)?S((h={},h[""]=e,h),"",c):e}}}r.runInContext=N;return r}var K=typeof define==="function"&&define.amd,F={"function":!0,object:!0},G=F[typeof exports]&&exports&&!exports.nodeType&&exports,k=F[typeof window]&&
window||this,t=G&&F[typeof module]&&module&&!module.nodeType&&"object"==typeof global&&global;!t||t.global!==t&&t.window!==t&&t.self!==t||(k=t);if(G&&!K)N(k,G);else{var L=k.JSON,Q=k.JSON3,M=!1,A=N(k,k.JSON3={noConflict:function(){M||(M=!0,k.JSON=L,k.JSON3=Q,L=Q=null);return A}});k.JSON={parse:A.parse,stringify:A.stringify}}K&&define(function(){return A})}).call(this);

(function(win){
    var doc = win.document;

    var _X = {
        SESSION_ERROR: {errorMessage:'\u8d26\u53f7\u5df2\u81ea\u52a8\u9000\u51fa',errorCode:'-1'},

        cid: 0,
        callbacks: {},
        call: function(functionName, args, callback) {
            var hasCallback = typeof callback == "function",
                id = hasCallback ? _X.cid++ : 0,
                iframe, FRAME_NAME = 'callNativeProxy';
            if (hasCallback) _X.callbacks[id] = callback;

            try {
                iframe = document.createElement('iframe');
                iframe.name = iframe.id = FRAME_NAME;
                iframe.style.display = 'none';
                iframe.onload = function() {
                    iframe.onload = null;
                    iframe.parentNode.removeChild(iframe);
                };
                _X.log( 'call: ' + functionName + '('+ JSON.stringify(args) + ')');
                document.body.appendChild(iframe);
                iframe.src = "about:blank?id="+ id +"&func=" + functionName + "&args=" + (args ? encodeURIComponent(JSON.stringify(args)) : "");
            } catch(e) {
                _X.log(e);
            }
        },
        done: function(id, result) {
            try {
                var callback = _X.callbacks[id];
                if (!callback) return;
                callback.call(null, result);
            } catch(e) {}
        },

        writePostResult: false,

        post: function(url, data, callback) {
            var iframe, form, input, state,
                FRAME_NAME = 'postProxyFrame',
                CHARSET = 'utf-8',
                JSON = top.JSON, jsonData;

            function addParams(obj) {
                for (var k in _X.params) {
                    if (k==='_') continue;
                    obj[k] = _X.params[k];
                }
            }

            if (typeof url !== 'string') {
                callback = data;
                data = url;
                url = '';
            }

            if (!data) {
                _X.log('_X.post() need data');
                return;
            }
            /*if (data.balance) {
                data.token = md5(data.balance + data.queryTime);
            }*/
            var i = data.length;
            if (i) {
                while (i--) {
                    addParams(data[i]);
                }
            } else {
                addParams(data);
            }

            jsonData = JSON.stringify(data);

            if (_X.writePostResult) {
                var html = '<!DOCTYPE html><html><body>';
                html += '<form method="post" action="'+ url +'" accept-charset="utf-8">';
                html += '<textarea name="bankDataJson" id="bankDataJson" style="width:100%;height:'+ parseInt(doc.body.clientHeight*0.8) +'px;" readonly></textarea>';
                html += '</form>';
                html += '</body></html>';

                // 粗暴一点，直接清除整个页面
                doc.charset = CHARSET;
                doc.open("text/html", "replace");
                doc.write(html);
                doc.close();
                document.getElementById('bankDataJson').value = jsonData;
            }

            document.bankDataJson = jsonData;
            // 通知客户端
            _X.call('completeData', {calljs: 'return document.bankDataJson'});
        },

        $: function(id) {
            return doc.getElementById(id);
        },

        on: function(name, elem, callback) {
            if (typeof elem === 'string') elem = _X.$(elem);
            // IE 下优先使用老的绑定方式
            if (win.attachEvent) {
                elem.attachEvent('on'+name, callback);
            } else {
                elem.addEventListener(name, callback, false);
            }
            return elem;
        },

        off: function(name, elem, callback) {
            if (typeof elem === 'string') elem = _X.$(elem);
            if (win.detachEvent) {
                elem.detachEvent('on'+name, callback);
            } else {
                elem.removeEventListener(name, callback);
            }
            return elem;
        },

        log: function(data) {
            win.console && win.console.log(data);
        },

        params: {},

        unparam: function(str, params) {
            if (!str) return;
            params = params || {};
            str.split("?").pop().replace(/([^?=&]+)=([^&#]*)/g, function (m, key, value) {
                params[key] = decodeURIComponent(value);
            });
            return params;
        },

        setParams: function(str) {
            _X.params = _X.unparam(str);
        },

        trim: function(str) {
            return str.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
        },

        formatDate: function(date, str, div) {
            if (!date) {
                date = new Date();
            }
            else if (typeof date === 'number') {
                date = new Date(date);
            }
            else if (typeof date === 'string') {
                var arr = /(\d{4})\D?(\d{2})\D?(\d{2})\s?(\d{2})?:?(\d{2})?:?(\d{2})?/.exec(date);
                date = arr.slice(1,4).join('/');
                if (arr[4]&&arr[5]&&arr[6]) {
                    date += ' ' + arr.slice(4).join(':');
                }
                date = new Date( date );
            }
            var y, m, d, H, M, S, time = '';

            if (typeof str === 'string') {
                str = str.toLowerCase();
                if (str === 'datetime') {
                    H = date.getHours();
                    M = date.getMinutes();
                    S = date.getSeconds();
                    time = ' ' + (H>9 ? H : '0'+H) + ':' + (M>9 ? M : '0'+M) + ':' + (S>9 ? S : '0'+S);
                    if (!div) div = '-';
                }
                else if (/\d(y|m|d)$/i.test(str)) {
                    var n = str.slice(0, str.length-1);
                    switch (str.slice(-1)) {
                        case 'y':
                            date.setFullYear(date.getFullYear() + (+n)); break;
                        case 'm':
                            date.setMonth(date.getMonth() + (+n)); break;
                        case 'd':
                            date.setDate(date.getDate() + (+n)); break;
                    }
                } else {
                    div = div || str;
                }
            }
            if (div === undefined) div = '';
            y = date.getFullYear();
            m = date.getMonth() + 1;
            d = date.getDate();

            return y + div + (m>9 ? m : '0'+m) + div + (d>9 ? d : '0'+d) + time;
        },

        formatMoney: function(value){
            if (value===undefined) value = 0;
            var parts = Number(value).toFixed(2).toString().split('.');
            return parts[0].replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,') + '.' + parts[1];
        },

        setForm: function(form, data) {
            if (!form || !data) return;
            for (var name in data) {
                form[name].value = data[name];
            }
        },

        getForm: function(form) {
            if (!form) return null;
            var data = {}, elements = form.elements, input;

            for (var i=0; i<elements.length; i++) {
                input = elements[i];
                if (!input.name) continue;
                data[input.name] = input.value;
            }
            return data;
        },

        table2json: function(table, ignoreTitle) {
            if (ignoreTitle) {
                table.deleteRow(0);
            }
            var rows = table.rows, i, j, rlen = rows.length, clen = rows[0].cells.length, row;
            var list = [];

            for (i=0; i<rlen; i++) {
                row = rows[i].cells;
                if (row.length !== clen) continue;
                list[i] = [];
                for (j=0; j<clen; j++) {
                    list[i][j] = _X.trim(row[j].innerText);
                }
            }
            return list;
        }
    };

    win._X = _X;

})(top);