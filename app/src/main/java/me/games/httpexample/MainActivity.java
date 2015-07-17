package me.games.httpexample;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import me.games.httpexample.model.Category;
import me.games.httpexample.network.ApiHelper;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ArrayList<Category> mCategoryList;

    private ApiHelper apiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        apiHelper = new ApiHelper(MainActivity.this);
        if(apiHelper.isConnected()){

          new GetCategoriesTask().execute();
        }else{
            Snackbar.make(mRecyclerView, "Not connected to internet", Snackbar.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class GetCategoriesTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            mCategoryList =  apiHelper.getCategories();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(mCategoryList !=null) {
                mRecyclerView.setAdapter(new CategoryAdapter(mCategoryList));
            }else{
                Snackbar.make(mRecyclerView, "Failed to get Data", Snackbar.LENGTH_SHORT).show();
            }

        }
    }


}
