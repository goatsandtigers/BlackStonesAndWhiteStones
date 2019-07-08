package com.goatsandtigers.blackstonesandwhitestones.history;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HistoryBarChart extends LinearLayout {

    public HistoryBarChart(Context context, int numWhitePebbles, int numBlackPebbles) {
        super(context);
        int totalPebbles = numWhitePebbles + numBlackPebbles;
        if (totalPebbles > 0) {
            setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50));
            setBackgroundColor(Color.GRAY);
            setPadding(5, 5, 5, 5);
            float whiteBarPercentage = totalPebbles > 0 ? numWhitePebbles / (float) totalPebbles : 0.5f;
            View whiteBar = new View(context);
            whiteBar.setBackgroundColor(Color.WHITE);
            whiteBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1 - whiteBarPercentage));
            addView(whiteBar);
            View blackBar = new View(context);
            blackBar.setBackgroundColor(Color.BLACK);
            blackBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, whiteBarPercentage));
            addView(blackBar);
        } else {
            TextView tv = new TextView(context);
            tv.setText("No data for this day.");
            addView(tv);
        }
    }
}
