package com.tianzh.cm.util.token;

import com.tianzh.cm.util.encrypt.Md5Utils;

/**
 * Created by pig on 2015-09-28.
 */
public class TokenUtils {

    public static String generateUserToken(String appid, String mac, String imei) {
        String origin = appid + "&" + mac + "&" + imei;

        return Md5Utils.getMd5(origin);
    }
}
