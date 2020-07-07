package com.example.transactionapp.ui;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.transactionapp.misc.FileReadWrite;
import com.example.transactionapp.misc.JSONConverter;
import com.example.transactionapp.structure.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionTable extends TableLayout {

    private List<Transaction> data;
    private List<DataRow> rows = new ArrayList<>();
    private TextView textView;
    private int selection = -1;

    public TransactionTable(Context context, TextView textView) {
        super(context);
        this.textView = textView;
        this.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    }

    public void setData(List<Transaction> data) {
        this.data = data;
        buildRows();
    }

    private void buildRows() {
        this.removeAllViews();
        rows.clear();
        for (int i = 0; i < data.size(); i++) {
            Transaction trn = data.get(i);
            final DataRow row = new DataRow(getContext(), i, trn.getDate(), trn.getAmount(), trn.getCategory());
            row.setClickable(true);
            row.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectRow(row.id);
                }
            });
            this.addView(row);
            rows.add(row);
        }
    }

    private void selectRow(int idx) {
        if (selection == -1) {
            // No row was previously selected
            rows.get(idx).toggle();
            selection = idx;
            textView.setText(getAdditionalInfo(idx));
        } else if (idx != selection) {
            // A different row was previously selected
            rows.get(selection).toggle();
            rows.get(idx).toggle();
            selection = idx;
            textView.setText(getAdditionalInfo(idx));
        } else {
            // The same row was previously selected
            rows.get(idx).toggle();
            selection = -1;
            textView.setText("");
        }
    }

    public void deleteRow() {
        if (selection == -1) {
            return;
        } else {
            Context ctx = getContext();
            List<Transaction> data = JSONConverter.JSONToTransactionList(FileReadWrite.read(ctx));
            data.remove(selection);
            FileReadWrite.write(ctx, JSONConverter.TransactionToJSON(data));
            setData(data);
            selection = -1;
        }
    }

    private String getAdditionalInfo(int idx) {
        return "Payee: " + data.get(idx).getPayee() + "\nDescription: " + data.get(idx).getDescription();
    }
}
