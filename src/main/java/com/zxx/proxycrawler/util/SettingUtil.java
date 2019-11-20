package com.zxx.proxycrawler.util;

import cn.hutool.setting.Setting;

/**
 * 配置文件获取
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/20 14:12
 */
public class SettingUtil {

    private static Setting setting;

    static {
        setting = new Setting("config.setting");
    }

    public static String getValue(String key){
        if(setting == null){
            setting = new Setting("config.setting");
        }
        return setting.get(key);
    }

}
