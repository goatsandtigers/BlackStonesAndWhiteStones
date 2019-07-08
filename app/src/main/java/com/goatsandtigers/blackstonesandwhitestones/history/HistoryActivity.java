package com.goatsandtigers.blackstonesandwhitestones.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.goatsandtigers.blackstonesandwhitestones.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout mainLayout;

    private interface Duration {
        String WEEK = "Show one week";
        String MONTH = "Show one month";
    }

    private String durationSelection = Duration.WEEK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        createCloseButton();
        createSpinner();
        showData();
        setContentView(mainLayout);
    }

    private void showData() {

        if (Duration.WEEK.equals(durationSelection)) {
            showStatsForWeek();
        } else {
            showStatsForMonth();
        }
    }

    private void createSpinner() {
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add(Duration.WEEK);
        spinnerArray.add(Duration.MONTH);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinner = new Spinner(this);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                durationSelection = spinner.getSelectedItem().toString();
                removePreviousRows();
                showData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        mainLayout.addView(spinner);
    }

    private void removePreviousRows() {
        for (int i = 0; i < mainLayout.getChildCount(); i++) {
            View child = mainLayout.getChildAt(i);
            if (child.getTag() == Boolean.TRUE) {
                mainLayout.removeView(child);
                i--;
            }
        }
    }

    private void createCloseButton() {
        Button closeButton = new Button(this);
        closeButton.setText("Return to main view");
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mainLayout.addView(closeButton);
    }

    private void showStatsForWeek() {
        for (int i = 0; i < 7; i++) {
            Date dateForRow = getDateSubtractingDays(i);
            addRow(dateForRow);
        }
    }

    private void showStatsForMonth() {
        int currentDay = new Date().getDate();
        for (int i = 0; i < currentDay; i++) {
            Date dateForRow = getDateSubtractingDays(i);
            addRow(dateForRow);
        }
    }

    private Date getDateSubtractingDays(int numDaysToSubtract) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -numDaysToSubtract);
        return cal.getTime();
    }

    private void addRow(Date date) {
        TextView tv = new TextView(this);
        tv.setText(new SimpleDateFormat("EEEE dd MMM yyyy").format(date));
        tv.setTag(Boolean.TRUE);
        mainLayout.addView(tv);
        int numWhitePebbles = Database.getWhitePebbleCount(this, date);
        int numBlackPebbles = Database.getBlackPebbleCount(this, date);
        HistoryBarChart barChart = new HistoryBarChart(this, numWhitePebbles, numBlackPebbles);
        barChart.setTag(Boolean.TRUE);
        mainLayout.addView(barChart);
    }
}
