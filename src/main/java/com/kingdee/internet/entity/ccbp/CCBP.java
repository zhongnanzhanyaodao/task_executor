package com.kingdee.internet.entity.ccbp;

import com.kingdee.internet.entity.TxnDetail;

public class CCBP extends TxnDetail<CCBP.Builder> {
    public CCBP() {}

    private CCBP(Builder builder) {
        super(builder);
    }

    public static class Builder extends TxnDetail.Builder<CCBP, Builder> {
        @Override
        protected Builder getSelf() {
            return this;
        }

        @Override
        public CCBP build() {
            return new CCBP(this);
        }
    }
}
