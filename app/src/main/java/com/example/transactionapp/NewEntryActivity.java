package com.example.transactionapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.transactionapp.misc.FileReadWrite;
import com.example.transactionapp.misc.JSONConverter;
import com.example.transactionapp.structure.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NewEntryActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add Entry");

        // Create DatePickerDialog listener
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateField();
            }
        };

        // Add date field listener
        EditText editTextDate = findViewById(R.id.fieldDate);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NewEntryActivity.this, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button okay = findViewById(R.id.btnOkay);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewTransaction();
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        Button cancel = findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

    /**
     * Populate the Date field with a human-readable string for selected the date
     */
    private void updateDateField() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EditText edittext = findViewById(R.id.fieldDate);
        edittext.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Save off the data in the fields as a new transaction
     */
    private void addNewTransaction() {
        Transaction tr = new Transaction();
        // Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        tr.setDate(sdf.format(myCalendar.getTime()));
        // Amount
        EditText amountField = findViewById(R.id.fieldAmount);
        tr.setAmount(amountField.getText().toString());
        // Category
        EditText categoryField = findViewById(R.id.fieldCategory);
        tr.setCategory(categoryField.getText().toString());
        // Payee
        EditText payeeField = findViewById(R.id.fieldPayee);
        tr.setPayee(payeeField.getText().toString());
        // Description
        EditText descField = findViewById(R.id.fieldDescription);
        tr.setDescription(descField.getText().toString());

        // Append transaction to existing file
        Context ctx = getApplicationContext();
        List<Transaction> lst = JSONConverter.JSONToTransactionList(FileReadWrite.read(ctx));
        lst.add(tr);
        FileReadWrite.write(ctx, JSONConverter.TransactionToJSON(lst));
    }
}