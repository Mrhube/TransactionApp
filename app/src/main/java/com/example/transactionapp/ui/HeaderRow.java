package com.example.transactionapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TableRow;
import android.widget.TextView;

public class HeaderRow extends TableRow {

    /**
     * Constructor for HeaderRow
     * @param context - the application context
     */
    public HeaderRow(Context context) {
        super(context);
        this.setBackgroundColor(Color.parseColor("#0079D6"));
        this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));

        String text[] = {"Date","Amount","Category"};
        Float weight[] = {0.3F,0.3F,0.4F};

        for (int i = 0; i < 3; i++) {
            TextView view = new TextView(context);
            view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, weight[i]));
            view.setPadding(10,10,10,10);
            view.setTypeface(view.getTypeface(), Typeface.BOLD);
            view.setTextColor(Color.parseColor("#FFFFFF"));
            view.setTextSize(15);
            view.setText(text[i]);
            this.addView(view);
        }
    }
}
