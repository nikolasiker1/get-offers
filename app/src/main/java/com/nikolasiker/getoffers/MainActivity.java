package com.nikolasiker.getoffers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.nikolasiker.feature_offer_list.OfferListActivity;
import com.nikolasiker.feature_offer_list.common.Constants;

public class MainActivity extends AppCompatActivity {

    private Button getOffersButton;
    private EditText appIdEditText;
    private EditText userIdEditText;
    private EditText apiKeyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getOffersButton = findViewById(R.id.getOffersButton);
        appIdEditText = findViewById(R.id.appIdEditText);
        userIdEditText = findViewById(R.id.userIdEditText);
        apiKeyEditText = findViewById(R.id.apiKeyEditText);
        getOffersButton.setOnClickListener(v -> startActivity(buildOffersIntent()));
    }

    private Intent buildOffersIntent() {
        Intent offersIntent = new Intent(this, OfferListActivity.class);
        offersIntent.putExtra(Constants.appId, appIdEditText.getText().toString());
        offersIntent.putExtra(Constants.userId, userIdEditText.getText().toString());
        offersIntent.putExtra(Constants.apiKey, apiKeyEditText.getText().toString());

        return offersIntent;
    }
}