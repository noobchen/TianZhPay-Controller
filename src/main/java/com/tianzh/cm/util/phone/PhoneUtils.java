package com.tianzh.cm.util.phone;

import com.tianzh.cm.constant.ChannelConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: cyc
 * Date: 12-3-21
 * Time: 上午10:10
 * Description: to write something
 */
public class PhoneUtils {
    private static Logger unknownImsiLogger = LoggerFactory.getLogger("unknown_imsi");

    public static String getPhone(String phone) {
        if (StringUtils.isEmpty(phone) || phone.length() < 11) {
            return null;
        } else if (phone.length() > 11) {
            return phone.substring(phone.length() - 11);
        } else {
            return phone;
        }
    }

    /**
     * 根据IMSI判断出运营商
     *
     * @param imsi
     * @return
     */
    public static int getProviderId(String imsi) {
        if (StringUtils.isNotEmpty(imsi)) {
            if (imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007")) {
                return ChannelConstants.Provider.CHINA_MOBILE;
            } else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
                return ChannelConstants.Provider.CHINA_UNICOM;
            } else if (imsi.startsWith("46003") || imsi.startsWith("46011") || imsi.startsWith("46005")) {
                return ChannelConstants.Provider.CHINA_TELECOM;
            } else {
                unknownImsiLogger.error("unknown provider by imsi:{}", imsi);
                //默认是中国移动
                return ChannelConstants.Provider.CHINA_MOBILE;

            }
        } else {
            //默认是中国移动
            return ChannelConstants.Provider.CHINA_MOBILE;
        }
    }
}
