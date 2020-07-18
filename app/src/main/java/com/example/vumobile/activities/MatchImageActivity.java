package com.example.vumobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vumobile.R;
import com.example.vumobile.model.ImageDataListModel;

public class MatchImageActivity extends AppCompatActivity {

    private ImageView imageFull;
    private String imageUrl;
    private TextView userNameDetails, userEmailDetails;
    private ImageDataListModel imageDataListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_image);


        Toolbar myToolbar = findViewById(R.id.toolbarIMgDetails);
        setSupportActionBar(myToolbar);
        //getSupportActionBar().setTitle("Joining Part 2");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Details");

        imageDataListModel = (ImageDataListModel) getIntent().getSerializableExtra("MyClass");

        imageFull = findViewById(R.id.imageFull);
        userNameDetails = findViewById(R.id.userNameDetails);
        userEmailDetails = findViewById(R.id.userEmailDetails);

        Glide.with(this).load(imageDataListModel.getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)).into(imageFull);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);
        imageFull.setAnimation(animation);

        userNameDetails.setText(imageDataListModel.getFirstName() + " " + imageDataListModel.getLastName());
        userEmailDetails.setText(imageDataListModel.getEmail());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
