package com.example.vumobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.vumobile.R;
import com.example.vumobile.adapters.ImageAdapter;
import com.example.vumobile.model.ImageModel;
import com.example.vumobile.networks.ApiUtil.ApiUtils;
import com.example.vumobile.networks.Remote.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView imageRecycler;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageRecycler = findViewById(R.id.imageRecycler);

        getImages();
    }

    private void getImages() {
        MaterialDialog dialog = new MaterialDialog.Builder(this).title(getResources().getString(R.string.loading))
                .content(getResources().getString(R.string.pleaseWait))
                .progress(true, 0)
                .cancelable(false)
                .show();
        APIService mApiService = ApiUtils.getApiService("https://reqres.in/");
        mApiService.getImageData().enqueue(new Callback<ImageModel>() {
            @Override
            public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                if (response.isSuccessful()) {

                    imageAdapter = new ImageAdapter(MainActivity.this, response.body().getData());
                    imageRecycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    imageRecycler.setHasFixedSize(true);
                    imageRecycler.setAdapter(imageAdapter);

                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ImageModel> call, Throwable t) {
                Log.d("ErrorMsg", "onFailure: " + t.getMessage());
                dialog.dismiss();
            }
        });
    }
}
