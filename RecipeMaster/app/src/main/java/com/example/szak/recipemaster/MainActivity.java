package com.example.szak.recipemaster;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity {
 /*   private CallbackManager callbackManager;
    private TextView facebookName;
    Profile profile;
    private ImageView facebookAvatar;
    private FacebookCallback<LoginResult> callback;
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        final Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton menuButton = (FloatingActionButton) findViewById(R.id.menuButton);
        final FloatingActionButton menuButtonX = (FloatingActionButton) findViewById(R.id.menuButtonX);
        final LoginButton menuFacebookButton = (LoginButton) findViewById(R.id.menuFacebookButton);;
        final FloatingActionButton menuRecipeButton = (FloatingActionButton) findViewById(R.id.menuRecipeButton);
        final DialogTitle recipeDialogTitle = (DialogTitle) findViewById(R.id.recipeDialogTitle);
        final AppBarLayout appBar = (AppBarLayout) findViewById(R.id.appBar);
        final RelativeLayout contentMain = (RelativeLayout) findViewById(R.id.contentMain);
  /*      menuFacebookButton.setReadPermissions("user_id");
        callbackManager = CallbackManager.Factory.create();
        menuFacebookButton.registerCallback(callbackManager, callback);
        facebookName = (TextView) findViewById(R.id.facebookName);
        facebookAvatar = (ImageView) findViewById(R.id.facebookAvatar);

        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                profile = Profile.getCurrentProfile();
                if (profile != null){
                    facebookName.setText("Logged as " + profile.getName());
                    facebookAvatar.setImageURI(profile.getLinkUri());
                }

            }


            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
*/
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menuFacebookButton.setVisibility(View.VISIBLE);
                menuRecipeButton.setVisibility(View.VISIBLE);
                recipeDialogTitle.setVisibility(View.VISIBLE);
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



            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pizza1);
            Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

            ImageView circularImageView = (ImageView) findViewById(R.id.circleView);
            circularImageView.setImageBitmap(circularBitmap);
        }

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
*/



}
