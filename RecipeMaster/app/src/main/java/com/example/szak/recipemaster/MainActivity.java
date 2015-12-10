package com.example.szak.recipemaster;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton menuButton = (FloatingActionButton) findViewById(R.id.menuButton);
        final FloatingActionButton menuFacebookButton = (FloatingActionButton) findViewById(R.id.menuFacebookButton);;
        final FloatingActionButton menuRecipeButton = (FloatingActionButton) findViewById(R.id.menuRecipeButton);
        final DialogTitle recipeDialogTitle = (DialogTitle) findViewById(R.id.recipeDialogTitle);
        final DialogTitle facebookDialogTitle = (DialogTitle) findViewById(R.id.facebookDialogTitle);


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menuFacebookButton.setVisibility(View.VISIBLE);
                menuRecipeButton.setVisibility(View.VISIBLE);
                recipeDialogTitle.setVisibility(View.VISIBLE);
                facebookDialogTitle.setVisibility(View.VISIBLE);

            }
        });


        menuFacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        menuRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.pizza1);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView)findViewById(R.id.menuPizza);
        circularImageView.setImageBitmap(circularBitmap);
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
}
