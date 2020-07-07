package com.example.transactionapp.misc;

import com.example.transactionapp.structure.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONConverter {

    /**
     * Converts an object into a JSON string
     * @param obj - the object to convert
     * @return the specified object represented as a string
     */
    public static String TransactionToJSON(Object obj) {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            // TODO: add more useful error handling here
        }
        return json;
    }

    /**
     * Converts a JSON string into a list of Transaction objects
     * @param json - the string to convert
     * @return a list of Transaction objects
     */
    public static List<Transaction> JSONToTransactionList(String json) {
        List<Transaction> data = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            data = mapper.readValue(json, new TypeReference<List<Transaction>>(){});
        } catch (IOException e) {
            // TODO: add more useful error handling here
        }
        return data;
    }

}
