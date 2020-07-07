package com.example.transactionapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.widget.TableRow;
import android.widget.TextView;

public class DataRow extends TableRow {

    /**
     * id is an integer used to control the alternating color scheme of the table rows
     * selected is flag used to indicate whether this row has been clicked by the user
     */
    public int id;
    Boolean selected = false;

    /**
     * Constructor for DataRow
     * @param context - the application context
     * @param id - integer used to determine row shading, should match the index of the row within the table
     * @param date - the data string to display
     * @param amount - the amount string to display, without the currency symbol
     * @param category - the category string to display
     */
    public DataRow(Context context, int id, String date, String amount, String category) {
        super(context);
        this.id = id;
        this.setBackgroundColor(Color.parseColor(getColor()));
        this.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));

        String text[] = {date,"$" + amount,category};
        Float weight[] = {0.3F,0.3F,0.4F};

        for (int i = 0; i < 3; i++) {
            TextView view = new TextView(context);
            TableRow.LayoutParams params = new TableRow.LayoutParams();
            view.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, weight[i]));
            view.setPadding(10,10,10,10);
            view.setTextSize(15);
            view.setText(text[i]);
            this.addView(view);
        }
    }

    /**
     * Toggles whether this row is selected or not and updates the background color accordingly
     */
    void toggle() {
        this.selected = !selected;
        this.setBackgroundColor(Color.parseColor(getColor()));
    }

    /**
     * Determines what color this row should be
     * @return a hex string representing a color
     */
    private String getColor() {
        if (selected) return "#FFFF77"; // yellow
        else if (id % 2 == 1) return "#FFFFFF"; // white
        else return "#DAE8FC"; // light blue
    }
}
