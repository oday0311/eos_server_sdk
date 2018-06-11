/*
 * Copyright (c) 2017-2018 PLACTAL.
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.fancyios.eoswallet.data.prefs;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fancyios.eoswallet.util.Consts;
import com.fancyios.eoswallet.util.RefValue;
import com.fancyios.eoswallet.util.StringUtils;

/**
 * Created by swapnibble on 2017-08-21.
 */
@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "eosc_pref";

    private static final String PREF_WALLET_DIR_NAME= "wallets";

    private static final String PREF_NODEOS_HOST = "eosd.host";
    private static final String PREF_NODEOS_PORT = "eosd.port";

    private static final String PREF_PREFIX_WALLET_PW = "wallet.pw.";
    private static final String PREF_SAVE_PASS_FOR_TESTING = "wallet.save.pw";
    private static final String PREF_DEFAULT_ACCOUNT_CREATOR= "account.default.creator";


    private static final String PREF_SKIP_SIGNING = "signing.skip";


    // cache values
    private Boolean mSkipSigning;






}
