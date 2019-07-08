package com.goatsandtigers.blackstonesandwhitestones;

import android.content.Context;

public class BlackPebbleJar extends PebbleJar {

    public BlackPebbleJar(Context context, int numPebbles, PebbleJarListener listener) {
        super(context, numPebbles, listener);
    }

    protected int getBackgroundResourceId() {
        return R.drawable.black_pebble;
    }

    protected void savePebbleCount() {
        Database.setBlackPebbleCount(getContext(), getNumPebbles());
    }
}
