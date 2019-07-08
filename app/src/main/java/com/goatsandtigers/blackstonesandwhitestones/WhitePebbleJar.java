package com.goatsandtigers.blackstonesandwhitestones;

import android.content.Context;

public class WhitePebbleJar extends PebbleJar {

    public WhitePebbleJar(Context context, int numPebbles, PebbleJarListener listener) {
        super(context, numPebbles, listener);
    }

    protected int getBackgroundResourceId() {
        return R.drawable.white_pebble;
    }

    protected void savePebbleCount() {
        Database.setWhitePebbleCount(getContext(), getNumPebbles());
    }
}
