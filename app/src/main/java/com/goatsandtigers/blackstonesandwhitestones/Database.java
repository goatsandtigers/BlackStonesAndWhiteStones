package com.goatsandtigers.blackstonesandwhitestones;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.Date;

public class Database {

    public static int getWhitePebbleCount(Context context) {
        return getWhitePebbleCount(context, new Date());
    }

    public static int getWhitePebbleCount(Context context, Date date) {
        String key = buildWhitePebbleCountKey(date);
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 0);
    }

    public static void setWhitePebbleCount(Context context, int whitePebbleCount) {
        setWhitePebbleCount(context, whitePebbleCount, new Date());
    }

    public static void setWhitePebbleCount(Context context, int whitePebbleCount, Date date) {
        String key = buildWhitePebbleCountKey(date);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, whitePebbleCount).commit();
    }

    private static String buildWhitePebbleCountKey(Date date) {
        return "whitePebbleCountKey" + dateToString(date);
    }

    public static int getBlackPebbleCount(Context context) {
        return getBlackPebbleCount(context, new Date());
    }

    public static int getBlackPebbleCount(Context context, Date date) {
        String key = buildBlackPebbleCountKey(date);
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 0);
    }

    public static void setBlackPebbleCount(Context context, int blackPebbleCount) {
        setBlackPebbleCount(context, blackPebbleCount, new Date());
    }

    public static void setBlackPebbleCount(Context context, int blackPebbleCount, Date date) {
        String key = buildBlackPebbleCountKey(date);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, blackPebbleCount).commit();
    }

    private static String buildBlackPebbleCountKey(Date date) {
        return "blackPebbleCountKey" + dateToString(date);
    }

    private static String dateToString(Date date) {
        return "" + date.getYear() + "_" + date.getMonth() + "_" + date.getDate();
    }
}
