package com.example.transactionapp.structure;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Transaction {
    String category;
    String description;
    String payee;
    String amount;
    String date; // Should be formatted YYYY-MM-dd

    @JsonSetter("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonSetter("payee")
    public void setPayee(String payee) {
        this.payee = payee;
    }

    @JsonSetter("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonSetter("transactiondate")
    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPayee() {
        return payee;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
