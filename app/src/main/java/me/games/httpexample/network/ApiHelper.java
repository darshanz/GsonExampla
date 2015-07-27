package me.games.httpexample.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import me.games.httpexample.model.Category;
import me.games.httpexample.model.CategoryResponse;
import me.games.httpexample.model.UserSubmitResponse;

/**
 * Created by darshanz on 7/17/15.
 */
public class ApiHelper {

    private Context context;
    private static final String CATEGORY_URL = "http://oshika.me/categories.json";
    private static final String REGISTER_USER_URL = "htto://technoguff.getsandbox.com/users";

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



            HttpClient client = new DefaultHttpClient();

            HttpGet getter = new HttpGet(CATEGORY_URL);

            HttpResponse response = client.execute(getter);

            HttpEntity entity = response.getEntity();

            InputStream is = entity.getContent();
            /*URL url = new URL(CATEGORY_URL);

            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();*/

             InputStreamReader reader = new InputStreamReader(is, "utf-8");
             categoryResponse = new Gson().fromJson(reader, CategoryResponse.class);



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categoryResponse.getData();
    }



    public UserSubmitResponse submitUser(String firstName, String lastName,  String address, String country){

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(REGISTER_USER_URL);


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(0, new BasicNameValuePair("firstName", firstName));
        params.add(1, new BasicNameValuePair("lastName", lastName));
        params.add(2, new BasicNameValuePair("address", address));
        params.add(3, new BasicNameValuePair("country", country));


        UrlEncodedFormEntity entity = null;
        try {
             entity = new UrlEncodedFormEntity(params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(entity);

        HttpResponse response = null;
        try {
           response  = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity responseEntity = response.getEntity();


        InputStream is = null;
        try {
            is = responseEntity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
            /*URL url = new URL(CATEGORY_URL);

            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();*/

        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(is, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        UserSubmitResponse submitResponse = new Gson().fromJson(reader, UserSubmitResponse.class);


        return submitResponse;
    }
}
