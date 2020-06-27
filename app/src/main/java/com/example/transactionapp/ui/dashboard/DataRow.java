package com.example.transactionapp.ui.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.widget.TableRow;
import android.widget.TextView;

public class DataRow extends TableRow {

    public int id;
    Boolean expanded = false;

    public DataRow(Context context, int id, String date, String amount, String category) {
        super(context);
        this.id = id;
        this.setBackgroundColor(Color.parseColor(getColor()));
        this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));

        String text[] = {date,amount,category};
        Float weight[] = {1F,2F,2F};

        for (int i = 0; i < 3; i++) {
            TextView view = new TextView(context);
            view.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, weight[i]));
            view.setPadding(10,10,10,10);
            view.setTextSize(15);
            view.setText(text[i]);
            this.addView(view);
        }
    }

    public void toggle() {
        this.expanded = !expanded;
        this.setBackgroundColor(Color.parseColor(getColor()));
    }

    private String getColor() {
        if (expanded) return "#FFFF77";
        else if (id % 2 == 1) return "#FFFFFF";
        else return "#DAE8FC";
    }
}
