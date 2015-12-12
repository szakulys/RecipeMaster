package com.example.szak.recipemaster;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.FacebookSdk;

import java.io.Console;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        final Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton menuButton = (FloatingActionButton) findViewById(R.id.menuButton);
        final FloatingActionButton menuButtonX = (FloatingActionButton) findViewById(R.id.menuButtonX);
        final FloatingActionButton menuFacebookButton = (FloatingActionButton) findViewById(R.id.menuFacebookButton);;
        final FloatingActionButton menuRecipeButton = (FloatingActionButton) findViewById(R.id.menuRecipeButton);
        final DialogTitle recipeDialogTitle = (DialogTitle) findViewById(R.id.recipeDialogTitle);
        final DialogTitle facebookDialogTitle = (DialogTitle) findViewById(R.id.facebookDialogTitle);
        final AppBarLayout appBar = (AppBarLayout) findViewById(R.id.appBar);
        final RelativeLayout contentMain = (RelativeLayout) findViewById(R.id.contentMain);



        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menuFacebookButton.setVisibility(View.VISIBLE);
                menuRecipeButton.setVisibility(View.VISIBLE);
                recipeDialogTitle.setVisibility(View.VISIBLE);
                facebookDialogTitle.setVisibility(View.VISIBLE);
                menuButtonX.setVisibility(View.VISIBLE);
                menuButton.setVisibility(View.GONE);
                appBar.setAlpha(0.4f);
                contentMain.setAlpha(0.4f);

            }
        });

        menuButtonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menuFacebookButton.setVisibility(View.GONE);
                menuRecipeButton.setVisibility(View.GONE);
                recipeDialogTitle.setVisibility(View.GONE);
                facebookDialogTitle.setVisibility(View.GONE);
                menuButton.setVisibility(View.VISIBLE);
                menuButtonX.setVisibility(View.GONE);
                appBar.setAlpha(1.0f);
                contentMain.setAlpha(1.0f);

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

                startActivity(new Intent(getApplicationContext(), PizzaRecipe.class));

            }
        });

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.pizza1);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView)findViewById(R.id.circleView);
        circularImageView.setImageBitmap(circularBitmap);
    }


}
