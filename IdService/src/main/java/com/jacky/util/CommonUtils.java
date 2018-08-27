package com.jacky.util;

import java.util.Arrays;

/**
 * Created by Jacky on 2018/8/27.
 */
public class CommonUtils {
    public static String[] SWITCH_ON_EXP = new String[]{"ON", "TRUE", "on", "true"};
    public static String[] SWITCH_OFF_EXP = new String[]{"OFF", "FALSE", "off", "false"};

    public static boolean isOn(String swtch) {
        if (Arrays.asList(SWITCH_ON_EXP).contains(swtch)) {
            return true;
        }
        return false;
    }

    public static boolean isPropKeyOn(String key) {
        String prop = System.getProperty(key);
        return isOn(prop);
    }


}
