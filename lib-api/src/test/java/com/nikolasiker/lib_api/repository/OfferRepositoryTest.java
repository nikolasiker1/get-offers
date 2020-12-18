package com.nikolasiker.lib_api.repository;

import com.google.gson.Gson;
import com.nikolasiker.lib_api.ImmediateRxSchedulersOverrideRule;
import com.nikolasiker.lib_api.model.Information;
import com.nikolasiker.lib_api.model.Offer;
import com.nikolasiker.lib_api.model.OfferType;
import com.nikolasiker.lib_api.model.Response;
import com.nikolasiker.lib_api.model.Thumbnail;
import com.nikolasiker.lib_api.service.OfferService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.UnknownHostException;
import java.util.Arrays;

import io.reactivex.Single;

public class OfferRepositoryTest {

    private OfferRepository offerRepository;
    private OfferService offerService;

    private Response response = new Response(
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

    @Rule
    public ImmediateRxSchedulersOverrideRule immediateRxSchedulersOverrideRule = new ImmediateRxSchedulersOverrideRule();

    @Before
    public void setUp() {
        offerService = Mockito.mock(OfferService.class);
        offerRepository = new OfferRepository(offerService, new Gson());
    }

    @Test
    public void shouldReturnSuccessResponse() {
        Mockito.when(offerService.getOffers(Mockito.any())).thenReturn(Single.just(response));

        offerRepository.getOffers(
                new OfferRepository.OfferParameters("testFormat", "testAppId", "testUid")
        )
                .test()
                .assertSubscribed()
                .assertNoErrors()
                .assertValue(response)
                .assertComplete();
    }

    @Test
    public void shouldReturnFailResponse() {
        UnknownHostException unknownHostException = new UnknownHostException();
        Mockito.when(offerService.getOffers(Mockito.any())).thenReturn(Single.error(unknownHostException));

        offerRepository.getOffers(
                new OfferRepository.OfferParameters("testFormat", "testAppId", "testUid")
        )
                .test()
                .assertSubscribed()
                .assertError(unknownHostException)
                .assertNotComplete();
    }
}