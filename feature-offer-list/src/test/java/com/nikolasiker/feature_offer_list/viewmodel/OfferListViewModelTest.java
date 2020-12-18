package com.nikolasiker.feature_offer_list.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.nikolasiker.feature_offer_list.ImmediateRxSchedulersOverrideRule;
import com.nikolasiker.lib_api.api.ApiResponse;
import com.nikolasiker.lib_api.api.CoreApi;
import com.nikolasiker.lib_api.di.ApiInjector;
import com.nikolasiker.lib_api.model.Information;
import com.nikolasiker.lib_api.model.Offer;
import com.nikolasiker.lib_api.model.OfferType;
import com.nikolasiker.lib_api.model.OfferResponse;
import com.nikolasiker.lib_api.model.Thumbnail;
import com.nikolasiker.lib_api.repository.OfferRepository;
import com.nikolasiker.lib_api.service.OfferService;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

public class OfferListViewModelTest {

    @Rule
    public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();
    @Rule
    public ImmediateRxSchedulersOverrideRule immediateRxSchedulersOverrideRule = new ImmediateRxSchedulersOverrideRule();


    private CoreApi coreApi;
    private OfferRepository offerRepository;
    private OfferService offerService;
    private OfferListViewModel offerListViewModel;

    private Observer observer;

    private OfferResponse offerResponse = new OfferResponse(
            "OK",
            "Ok",
            2,
            1,
            new Information(
                    "testAppName",
                    "testAppId",
                    "testVirtualCurrency",
                    "testLanguage",
                    "testSupportUrl"
            ),
            Arrays.asList(
                    new Offer(
                            "testLink1",
                            "offerId1",
                            "title1",
                            "teaser1",
                            "requiredActions1",
                            new Thumbnail("lowresTest", "hiresTest"),
                            Arrays.asList(
                                    new OfferType("testPayout", "testTimeToPayout"),
                                    new OfferType("testPayout1", "testTimeToPayout1")
                            )
                    ),
                    new Offer(
                            "testLink",
                            "offerId",
                            "title",
                            "teaser",
                            "requiredActions",
                            new Thumbnail("lowresTest", "hiresTest"),
                            Arrays.asList(
                                    new OfferType("testPayout", "testTimeToPayout"),
                                    new OfferType("testPayout1", "testTimeToPayout1")
                            )
                    )));

    @Before
    public void setUp() {
        ApiInjector.inject();
        offerService = Mockito.mock(OfferService.class);
        offerRepository = new OfferRepository(offerService, new Gson());
        coreApi = Mockito.mock(CoreApi.class);
        offerListViewModel = new OfferListViewModel(coreApi);
        observer = Mockito.mock(Observer.class);
        Mockito.when(coreApi.getOfferRepository()).thenReturn(offerRepository);
    }

    @Test
    public void shouldReturnSuccessResponse() {
        Mockito.when(offerService.getOffers(Mockito.any())).thenReturn(Single.just(offerResponse));
        offerListViewModel.getOffers(new OfferRepository.OfferParameters("test", "test", "test"));
        ApiResponse<List<Offer>> apiResponse = offerListViewModel.getOfferLiveData().getValue();
        TestCase.assertNotNull(apiResponse);
        TestCase.assertEquals(offerResponse.getOffers().size(), ((ApiResponse.Success<List<Offer>>) apiResponse).getData().size());
    }

    @Test
    public void shouldReturnNetworkErrorResponse() {
        Mockito.when(offerService.getOffers(Mockito.any())).thenReturn(Single.error(UnknownHostException::new));
        offerListViewModel.getOffers(new OfferRepository.OfferParameters("test", "test", "test"));
        ApiResponse<List<Offer>> apiResponse = offerListViewModel.getOfferLiveData().getValue();
        TestCase.assertNotNull(apiResponse);
        TestCase.assertTrue(apiResponse instanceof ApiResponse.Error);
    }

    @Test
    public void shouldReturnApiErrorResponse() {
        OfferResponse failOfferResponse = new OfferResponse(
                "401",
                "ERROR_INVALID_HASHKEY",
                2,
                1,
                new Information(
                        "testAppName",
                        "testAppId",
                        "testVirtualCurrency",
                        "testLanguage",
                        "testSupportUrl"
                ),
                Arrays.asList(
                        new Offer(
                                "testLink1",
                                "offerId1",
                                "title1",
                                "teaser1",
                                "requiredActions1",
                                new Thumbnail("lowresTest", "hiresTest"),
                                Arrays.asList(
                                        new OfferType("testPayout", "testTimeToPayout"),
                                        new OfferType("testPayout1", "testTimeToPayout1")
                                )
                        ),
                        new Offer(
                                "testLink",
                                "offerId",
                                "title",
                                "teaser",
                                "requiredActions",
                                new Thumbnail("lowresTest", "hiresTest"),
                                Arrays.asList(
                                        new OfferType("testPayout", "testTimeToPayout"),
                                        new OfferType("testPayout1", "testTimeToPayout1")
                                )
                        )));
        Mockito.when(offerService.getOffers(Mockito.any())).thenReturn(Single.just(failOfferResponse));
        offerListViewModel.getOffers(new OfferRepository.OfferParameters("test", "test", "test"));
        ApiResponse<List<Offer>> apiResponse = offerListViewModel.getOfferLiveData().getValue();
        TestCase.assertNotNull(apiResponse);
        TestCase.assertTrue(apiResponse instanceof ApiResponse.Error);
    }
}