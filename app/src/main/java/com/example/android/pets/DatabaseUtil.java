package com.example.android.pets;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class DatabaseUtil {
    //You need to declare permission
    // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    //in your Manifest file in order to use this class

    //______________________________________________________________________________________________

    //todo -> rename the database according to your application
    private final static String DATABASE_NAME = "shelter.db";
    //example WhatsApp :  /data/data/com.whatsapp/databases/pet.db
    private final static String FOLDER_EXTERNAL_DIRECTORY = Environment.getExternalStorageDirectory() + "/Pets-Database";

    //______________________________________________________________________________________________
    /**
     * Call this method from any activity in your app (
     * for example ->    DatabaseUtil.copyDatabaseToExtStg(MainActivity.this);
     */
    public static void copyDatabaseToExtStg(Context ctx) {
        //external storage file
        File externalDirectory = new File(FOLDER_EXTERNAL_DIRECTORY);
        if(!externalDirectory.exists())
            externalDirectory.mkdirs();
        File toFile = new File(externalDirectory, DATABASE_NAME);
        //internal storage file
        //https://developer.android.com/reference/android/content/Context.html#getDatabasePath(java.lang.String)
        File fromFile = ctx.getDatabasePath(DATABASE_NAME);
        //example WhatsApp :  /data/data/com.whatsapp/databases/pet.db
        if (fromFile.exists())
            copy(fromFile, toFile);
    }


    //______________________________________________________________________________________________ Utility function
    /**
     * @param fromFile source location
     * @param toFile destination location
     * copy file from 1 location to another
     */
    static void copy(File fromFile, File toFile) {
        try {
            FileInputStream is = new FileInputStream(fromFile);
            FileChannel src = is.getChannel();
            FileOutputStream os = new FileOutputStream(toFile);
            FileChannel dst = os.getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();    is.close();
            dst.close();    os.close();
        } catch (Exception e) {
            //todo in case of exception
        }
    }
}
