package com.goatsandtigers.blackstonesandwhitestones;

import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

public abstract class PebbleJar extends GridView {

    private int maxNumPebbles;
    private int numPebbles;
    private BaseAdapter baseAdapter;

    public PebbleJar(Context context, int numPebbles, final PebbleJarListener listener)
    {
        super(context);
        this.numPebbles = numPebbles;
        setBackgroundResource(R.drawable.jar);
        setPadding(40, 0, 40, 50);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
        setGravity(Gravity.CENTER);
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == android.view.MotionEvent.ACTION_UP) {
                    if (PebbleJar.this.numPebbles > 0) {
                        PebbleJar.this.numPebbles--;
                    }
                    listener.onNumPebblesChanged();
                    baseAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return PebbleJar.this.numPebbles;
            }

            // 3
            @Override
            public long getItemId(int position) {
                return 0;
            }

            // 4
            @Override
            public Object getItem(int position) {
                return null;
            }

            // 5
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return getPebbleView();
            }
        };
        setAdapter(baseAdapter);
        setStackFromBottom(true);
    }

    public void setMaxNumPebbles(int maxNumPebbles)
    {
        this.maxNumPebbles = maxNumPebbles;
        int numRows = (int) Math.sqrt(maxNumPebbles) + 1;
        int numCols = (maxNumPebbles / numRows) + 1;
        setNumColumns(numCols);
    }

    public void addPebble() {
        numPebbles++;
        savePebbleCount();
        baseAdapter.notifyDataSetChanged();
    }

    protected abstract void savePebbleCount();

    private View getPebbleView() {
        View view = new View(getContext());
        view.setBackgroundResource(getBackgroundResourceId());
        int width = getWidth() / getNumColumns();
        int widthWithPadding = (int) (width * 0.8);
        view.setLayoutParams(new AbsListView.LayoutParams(widthWithPadding, 50));
        return view;
    }

    protected abstract int getBackgroundResourceId();

    public int getNumPebbles() {
        return numPebbles;
    }

    public interface PebbleJarListener {
        void onNumPebblesChanged();
    }
}
