package com.tianzh.cm.constant;

/**
 * Author: cyc
 * Date: 12-3-21
 * Time: 上午10:08
 * Description: to write something
 */
public class ChannelConstants {
    public static final int DEFAULT_PROVINCE_ID = -1; //全国

    /**
     * 运营商
     */
    public static class Provider {
        public static final int CHINA_MOBILE = 0;
        public static final int CHINA_UNICOM = 1;
        public static final int CHINA_TELECOM = 2;
    }

    /**
     * 区域类型
     */
    public static class AreaType {
        public static final String PROVINCE = "0";
        public static final String CITY = "1";
    }

    /**
     * 通道部署状态
     */
    public static class ChannelDeployStatus {
        public static final String OPEN = "0";
        public static final String CLOSE = "1";
    }

    /**
     * 通道限制策略状态
     */
    public static class ChannelLimitStatus {
        public static final String OPEN = "0";
        public static final String CLOSE = "1";
    }

    /**
     * 通道二次确认策略状态
     */
    public static class ChannelConfirmStatus {
        public static final String OPEN = "0";
        public static final String CLOSE = "1";
    }

    /**
     * 通道业务提示策略状态
     */
    public static class ChannelBusinessPromptStatus {
        public static final String OPEN = "0";
        public static final String CLOSE = "1";
    }

    /**
     * 业务提示下行拦截策略
     */
    public static class ChannelBusinessPromptStrategy {
        public static final String INTERCEPT = "0";
        public static final String NON_INTERCEPT = "1";
    }

    /**
     * 二次确认拦截策略
     */
    public static class ChannelConfirmStrategy {
        public static final String INTERCEPT_AND_ANSWER = "0";
        public static final String INTERCEPT_AND_PROMPT = "1";
        public static final String NON_INTERCEPT = "2";
    }

    /**
     * 二次确认类型
     */
    public static class ChannelConfirmType {
        public static final String FIXED_CONTENT_CONFIRM = "0";
        public static final String RANDOM_PASSWORD_CONFIRM = "1";
        public static final String QUESTION_CONFIRM = "2";
        public static final String NO_CONFIRM = "3";
    }

    /**
     * 通道状态
     */
    public static class ChannelStatus {
        public static final String OPEN = "0";
        public static final String CLOSE = "1";
    }

    /**
     * 是否包月通道
     */
    public static class MonthPlanChannel {
        public static final String YES = "0";
        public static final String NO = "1";
    }

    /**
     * 通道类型
     */
    public static class ChannelType {
        public static final String SMS = "0";
        public static final String NET_GAME = "1";
        public static final String XINJIANG_PAY = "2";
        public static final String VEDIO_BASE_PAY = "3";
        public static final String MUSIC_BASE_PAY = "4";
        public static final String CARTOON_BASE_PAY = "5";
        public static final String NETGAME_BASE_PAY = "6";
        public static final String READ_BASE_PAY = "7";
        public static final String BOX_BASE_PAY = "8";
        public static final String IVR_PAY = "9";
        public static final String PHONE_FEE_PAY = "10";
    }

    /**
     * 通道区域屏蔽
     */
    public static class ChannelAreaShield {
        public static final String SHIELD = "1";
        public static final String NOT_SHIELD = "0";
    }
}
