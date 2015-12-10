package com.example.szak.recipemaster;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PizzaRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(this, "Getting recipe", Toast.LENGTH_LONG).show();
        new SaveTheFeed().execute();
    }

    public boolean onOptionsItemSelected(MenuItem item){

        finish();
        return true;

    }

    class SaveTheFeed extends AsyncTask<Void, Void, Void>{

        String jsonString = "";
        String result = "";


        @Override
        protected Void doInBackground(Void... params) {

            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
            HttpPost httpPost = new HttpPost("http://mooduplabs.com/test/info.php");
            httpPost.setHeader("content-type", "application/json");
            InputStream inputStream = null;

            try{
                HttpResponse response = httpClient.execute(httpPost);

                HttpEntity entity = response.getEntity();

                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);

                StringBuilder sb = new StringBuilder();

                String line = null;

                while((line = reader.readLine()) != null){

                    sb.append(line + "\n");

                }

                jsonString = sb.toString();

                JSONObject jObject = new JSONObject(jsonString);

                JSONArray jArray = jObject.getJSONArray("title");

                outputTitles(jArray);

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            TextView translationTextView = (TextView) findViewById(R.id.titlesTextView);

            translationTextView.setText(result);
        }

        protected void outputTitles(JSONArray jsonArray){

            String[] titles = {"title", "ingredients", "preparing", "imgs"};
            try{

                for(int i = 0; i < jsonArray.length(); i++){

                    JSONObject translationObject =
                            jsonArray.getJSONObject(i);

                    result = result + titles[i] + " : " +
                            translationObject.getString(titles[i]) + "\n";

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



}
