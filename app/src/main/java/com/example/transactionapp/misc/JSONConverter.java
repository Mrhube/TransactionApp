package com.example.transactionapp.misc;

import com.example.transactionapp.structure.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONConverter {

    public static String TransactionToJSON(Object obj) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            // TODO: add more useful error handling here
            e.printStackTrace();
        }
        return json;
    }

//    public static String TransactionListToJSON(List<Transaction> lst) {
//        ObjectMapper mapper = new ObjectMapper();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        try {
//            mapper.writeValue(out, lst);
//        } catch (IOException e) {
//            // TODO: add more useful error handling here
//            e.printStackTrace();
//        }
//    }

    public static List<Transaction> JSONToTransactionList(String json) {
        List<Transaction> data = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            data = mapper.readValue(json, new TypeReference<List<Transaction>>(){});
        } catch (IOException e) {
            // TODO: add more useful error handling here
            e.printStackTrace();
        }
        return data;
    }

}
