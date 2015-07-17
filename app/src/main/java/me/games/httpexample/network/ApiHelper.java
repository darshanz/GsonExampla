package me.games.httpexample.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import me.games.httpexample.model.Category;
import me.games.httpexample.model.CategoryResponse;

/**
 * Created by darshanz on 7/17/15.
 */
public class ApiHelper {

    private Context context;
    private static final String CATEGORY_URL = "http://oshika.me/categories.json";

    public ApiHelper(Context context) {
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null){
            return false;
        }

        return networkInfo.isConnected();
    }


    public ArrayList<Category> getCategories(){
        CategoryResponse categoryResponse = new CategoryResponse();

        try {
            URL url = new URL(CATEGORY_URL);

            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

             InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
             categoryResponse = new Gson().fromJson(reader, CategoryResponse.class);



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categoryResponse.getData();
    }
}
