package com.michaelbarany.smarthome.services;

import android.content.Context;

import com.google.gson.Gson;
import com.michaelbarany.smarthome.services.modules.ForApplication;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JsonCache {

    private static final String EXT = ".json";

    @Inject
    @ForApplication
    Context mContext;

    public <T> void write(String fileName, T object) {
        String fullFileName = getFullFileName(fileName);
        Gson gson = new Gson();

        try {
            FileOutputStream fos = mContext.openFileOutput(fullFileName, Context.MODE_PRIVATE);
            fos.write(gson.toJson(object).getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(String fileName) {
        String fullFileName = getFullFileName(fileName);
        mContext.deleteFile(fullFileName);
    }

    public <T> T read(String fileName, Class<T> clazz) {
        String fullFileName = getFullFileName(fileName);
        Gson gson = new Gson();

        try {
            FileInputStream fis = mContext.openFileInput(fullFileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            return gson.fromJson(json, clazz);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFullFileName(String fileName) {
        return fileName + EXT;
    }
}
