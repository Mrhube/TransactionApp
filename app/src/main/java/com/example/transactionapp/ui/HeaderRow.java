package com.example.transactionapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TableRow;
import android.widget.TextView;

public class HeaderRow extends TableRow {

    public HeaderRow(Context context) {
        super(context);
        this.setBackgroundColor(Color.parseColor("#0079D6"));
        this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));

        String text[] = {"Date","Amount","Category"};
        Float weight[] = {1F,2F,2F};

        for (int i = 0; i < 3; i++) {
            TextView view = new TextView(context);
            view.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, weight[i]));
            view.setPadding(10,10,10,10);
            view.setTypeface(view.getTypeface(), Typeface.BOLD);
            view.setTextColor(Color.parseColor("#FFFFFF"));
            view.setTextSize(15);
            view.setText(text[i]);
            this.addView(view);
        }
    }
}
