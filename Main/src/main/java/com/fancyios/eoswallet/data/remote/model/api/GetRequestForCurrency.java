package com.fancyios.eoswallet.data.remote.model.api;

import com.google.gson.annotations.Expose;

import com.fancyios.eoswallet.data.remote.model.types.TypeName;
import com.fancyios.eoswallet.util.StringUtils;

/**
 * Created by swapnibble on 2018-04-16.
 */
public class GetRequestForCurrency {
    @Expose
    protected boolean json = false;

    @Expose
    protected TypeName code;

    @Expose
    protected String symbol;

    public GetRequestForCurrency(String tokenContract, String symbol){
        this.code = new TypeName(tokenContract);
        this.symbol = StringUtils.isEmpty(symbol) ? null : symbol;
    }
}
