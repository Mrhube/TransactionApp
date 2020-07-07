package com.example.transactionapp.misc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWrite {

    /**
     * saveDirectory is the folder in application-specific memory where data will be written/read
     * saveFilename is the name of the file containing the data
     */
    private static String saveDirectory = "data";
    private static String saveFilename = "data.txt";

    /**
     * Writes a string to the application-specific memory
     * @param ctx - the application context
     * @param content - the string to save to memory
     */
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

        } catch (Exception e){
            // TODO: add more useful error handling here
        }
    }

    /**
     * Reads a string from the application-specific memory
     * @param ctx - the application context
     * @return the string that was previously written to memory
     */
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
        } catch (IOException e) {
            // TODO: add more useful error handling here
        }
        return text.toString();
    }
}
