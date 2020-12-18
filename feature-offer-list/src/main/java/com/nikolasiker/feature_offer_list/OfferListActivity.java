package com.nikolasiker.feature_offer_list;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nikolasiker.feature_offer_list.common.Constants;
import com.nikolasiker.feature_offer_list.recycler.OfferListAdapter;
import com.nikolasiker.feature_offer_list.viewmodel.OfferListViewModel;
import com.nikolasiker.feature_offer_list.viewmodel.OfferViewModelFactory;
import com.nikolasiker.lib_api.api.ApiResponse;
import com.nikolasiker.lib_api.api.CoreApi;
import com.nikolasiker.lib_api.model.Offer;
import com.nikolasiker.lib_api.repository.OfferRepository;

import java.util.List;

//1c915e3b5d42d05136185030892fbb846c278927 api key
public class OfferListActivity extends AppCompatActivity {
    private CoreApi coreApi = new CoreApi();
    private RecyclerView offerRecyclerView;
    private View errorPage;
    private ProgressBar loadingView;

    private OfferListViewModel offerListViewModel;
    private String appId;
    private String userId;
    private String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_list);

        setupUi();

        getOfferData();

        offerListViewModel = new ViewModelProvider(this, new OfferViewModelFactory(coreApi)).get(OfferListViewModel.class);

        offerListViewModel.getOfferLiveData().observe(this, offers -> {
            if (offers instanceof ApiResponse.Success) {
                showSuccessPage();
                OfferListAdapter adapter = new OfferListAdapter(((ApiResponse.Success<List<Offer>>) offers).getData());
                offerRecyclerView.setAdapter(adapter);
            } else if (offers instanceof ApiResponse.Error) {
                showErrorPage(((ApiResponse.Error<List<Offer>>) offers).getErrorMessage());
            } else {
                showLoading();
            }
        });

        offerListViewModel.getOffers(buildOfferParameters());

    }

    private void setupUi() {
        offerRecyclerView = findViewById(R.id.offerRecyclerView);
        offerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        errorPage = findViewById(R.id.errorPage);
        loadingView = findViewById(R.id.loadingView);
    }

    private void showErrorPage(String error) {
        offerRecyclerView.setVisibility(View.GONE);
        errorPage.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);

        Button retryButton = findViewById(R.id.tryAgainButton);
        retryButton.setOnClickListener(v -> offerListViewModel.getOffers(buildOfferParameters()));
        TextView errorDescription = findViewById(R.id.errorText);
        errorDescription.setText(error);
    }

    private void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        offerRecyclerView.setVisibility(View.GONE);
        errorPage.setVisibility(View.GONE);
    }

    private void showSuccessPage() {
        offerRecyclerView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorPage.setVisibility(View.GONE);
    }

    private void getOfferData() {
        if (getIntent().getExtras() != null) {
            apiKey = getIntent().getExtras().getString(Constants.apiKey);
            appId = getIntent().getExtras().getString(Constants.appId);
            userId = getIntent().getExtras().getString(Constants.userId);
        } else {
            showErrorPage(getResources().getString(R.string.no_intent_data_error));
        }
    }

    private OfferRepository.OfferParameters buildOfferParameters() {
        OfferRepository.OfferParameters offerParameters = new OfferRepository.OfferParameters(
                Constants.format,
                appId,
                userId
        );

        offerParameters.setIp(Constants.ip);
        offerParameters.setLocale(Constants.locale);
        offerParameters.setOfferTypes(Constants.offerTypes);
        offerParameters.setApiKey(apiKey);

        return offerParameters;
    }
}