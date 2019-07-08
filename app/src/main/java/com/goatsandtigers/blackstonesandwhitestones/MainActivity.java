package com.goatsandtigers.blackstonesandwhitestones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goatsandtigers.blackstonesandwhitestones.history.HistoryActivity;

public class MainActivity extends AppCompatActivity implements PebbleJar.PebbleJarListener {

    private View dropWhitePebbleView;
    private View dropBlackPebbleView;
    private PebbleJar whitePebbleJar;
    private PebbleJar blackPebbleJar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dropWhitePebbleView = new View(this);
        dropWhitePebbleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
        dropWhitePebbleView.setBackgroundResource(R.drawable.white_pebble);
        dropWhitePebbleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
                    whitePebbleJar.addPebble();
                    onNumPebblesChanged();
                }
                return true;
            }
        });
        dropBlackPebbleView = new View(this);
        dropBlackPebbleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
        dropBlackPebbleView.setBackgroundResource(R.drawable.black_pebble);
        dropBlackPebbleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
                    blackPebbleJar.addPebble();
                    onNumPebblesChanged();
                }
                return true;
            }
        });
        LinearLayout dropPebbleLayout = new LinearLayout(this);
        dropPebbleLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.75f));
        dropPebbleLayout.addView(dropWhitePebbleView);
        dropPebbleLayout.addView(dropBlackPebbleView);
        dropPebbleLayout.setOrientation(LinearLayout.HORIZONTAL);
        int numWhitePebbles = Database.getWhitePebbleCount(this);
        whitePebbleJar = new WhitePebbleJar(this, numWhitePebbles, this);
        int numBlackPebbles = Database.getBlackPebbleCount(this);
        blackPebbleJar = new BlackPebbleJar(this, numBlackPebbles, this);
        onNumPebblesChanged();
        LinearLayout jarLayout = new LinearLayout(this);
        jarLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.25f));
        jarLayout.setOrientation(LinearLayout.HORIZONTAL);
        jarLayout.addView(whitePebbleJar);
        jarLayout.addView(blackPebbleJar);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(buildInstructionTextView());
        mainLayout.addView(dropPebbleLayout);
        mainLayout.addView(jarLayout);
        setContentView(mainLayout);
    }

    private TextView buildInstructionTextView() {
        TextView tv = new TextView(this);
        tv.setText("Drag a white or black pebble into the jar. Drag one of the pebbles out of either of the jars to remove it.");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        return tv;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_view_history) {
            Intent intent = new Intent(getBaseContext(), HistoryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNumPebblesChanged() {
        int numWhitePebbles = Database.getWhitePebbleCount(this);
        int numBlackPebbles = Database.getBlackPebbleCount(this);
        int mostPebblesInJar = Math.max(numWhitePebbles, numBlackPebbles);
        int maxPebblesInJarRoundedUpToNearestTen = mostPebblesInJar + 10 - (mostPebblesInJar % 10);
        whitePebbleJar.setMaxNumPebbles(maxPebblesInJarRoundedUpToNearestTen);
        blackPebbleJar.setMaxNumPebbles(maxPebblesInJarRoundedUpToNearestTen);
    }

}
