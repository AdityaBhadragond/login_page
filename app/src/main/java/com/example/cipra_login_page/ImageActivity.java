package com.example.cipra_login_page;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class ImageActivity extends AppCompatActivity {

    private TextView recommendationText;
    private ImageView recommendationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        recommendationText = findViewById(R.id.recommendationText);
        recommendationImage = findViewById(R.id.recommendationImage);

        String response = getIntent().getStringExtra("response");
        parseResponse(response);
    }

    private void parseResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String recommendation = jsonObject.getString("recommendation");
            String imageUrl = jsonObject.getString("imageUrl");

            recommendationText.setText(recommendation);
            Picasso.get().load(imageUrl).into(recommendationImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}