package com.example.szak.recipemaster;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PizzaRecipe extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new SaveTheFeed().execute();


    }


    public boolean onOptionsItemSelected(MenuItem item){

        finish();
        return true;

    }

    class SaveTheFeed extends AsyncTask<Void, Void, Void> {

        JSONObject jObject;
        TextView pizzaTitle;
        TextView pizzaContent;
        TextView ingredientsContent;
        TextView preparingContent;
        ImageView images;
        Bitmap[] sources;

        public  Bitmap getBitmapFromURL(String src) {
            try {
                Log.e("src",src);
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap;
                bitmap = BitmapFactory.decodeStream(input);
                Log.e("Bitmap","returned");
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Exception", e.getMessage());
                return null;
            }
        }


        @Override
        protected Void doInBackground(Void... voids) {

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

                String jsonString = sb.toString();
                jObject = new JSONObject(jsonString);
                JSONArray imgJArray = jObject.getJSONArray("imgs");
/*                sources = new Bitmap[imgJArray.length()];

                for (int i = 0; i < imgJArray.length(); i++){
                    String imgs = imgJArray.getString(i);
                    sources[i] = getBitmapFromURL(imgs);
                }
*/
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

            pizzaContent = (TextView) findViewById(R.id.contentPizza);
            pizzaTitle = (TextView) findViewById(R.id.titlePizza);

            try {

                String title = jObject.getString("title");
                pizzaTitle.setText(title + ":");
                String description = jObject.getString("description");
                pizzaContent.setText(description);

                JSONArray ingrJArray = jObject.getJSONArray("ingredients");
                JSONArray prepJArray = jObject.getJSONArray("preparing");
                JSONArray imgJArray = jObject.getJSONArray("imgs");


                for (int i = 0; i < ingrJArray.length(); i++) {
                    String ingredient = ingrJArray.getString(i);
                    int ingCon = R.id.ingredientsContent + i;
                    ingredientsContent = (TextView) findViewById(ingCon);
                    ingredientsContent.setText("- " + ingredient);
                }

                for (int i = 0; i < prepJArray.length(); i++) {
                    String preparing = prepJArray.getString(i);
                    int prepCon = R.id.preparingContent + i;
                    preparingContent = (TextView) findViewById(prepCon);
                    int j = i;
                    preparingContent.setText(++j + ". " + preparing);
                }
/*
                for (int i = 0; i < imgJArray.length(); i++) {
                    int imgCon = R.id.image + i;
                    images = (ImageView) findViewById(imgCon);
                    images.setImageBitmap(sources[i]);
                }
*/
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }



}
