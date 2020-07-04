package com.example.transactionapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWrite {

    private static String saveDirectory = "data";
    private static String saveFilename = "data.txt";

    public static void write(Context ctx, String content) {
        // Create the directory if it doesn't exist
        File dir = new File(ctx.getFilesDir(), saveDirectory);
        if(!dir.exists()){
            dir.mkdir();
        }
        // Replace the file contents
        try {
            File file = new File(dir, saveFilename);
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
            writer.close();
        // TODO: add more useful error handling here
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String read(Context ctx) {
        File file = new File(ctx.getFilesDir(), saveDirectory + "/" + saveFilename);
        // Read the file contents
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        // TODO: add more useful error handling here
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

}
