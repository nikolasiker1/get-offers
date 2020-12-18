package com.nikolasiker.lib_api.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class OfferResponseTest extends BaseDomainTest {
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
        mockedJson = "{\"code\":\"OK\",\"count\":2,\"information\":{\"appName\":\"testAppName\",\"appid\":\"testAppId\",\"language\":\"testLanguage\",\"supportUrl\":\"testSupportUrl\",\"virtualCurrency\":\"testVirtualCurrency\"},\"message\":\"Ok\",\"offers\":[{\"link\":\"testLink1\",\"offerId\":\"offerId1\",\"offerTypes\":[{\"payout\":\"testPayout\",\"timeToPayout\":\"testTimeToPayout\"},{\"payout\":\"testPayout1\",\"timeToPayout\":\"testTimeToPayout1\"}],\"requiredActions\":\"requiredActions1\",\"teaser\":\"teaser1\",\"thumbnail\":{\"hires\":\"hiresTest\",\"lowres\":\"lowresTest\"},\"title\":\"title1\"},{\"link\":\"testLink\",\"offerId\":\"offerId\",\"offerTypes\":[{\"payout\":\"testPayout\",\"timeToPayout\":\"testTimeToPayout\"},{\"payout\":\"testPayout1\",\"timeToPayout\":\"testTimeToPayout1\"}],\"requiredActions\":\"requiredActions\",\"teaser\":\"teaser\",\"thumbnail\":{\"hires\":\"hiresTest\",\"lowres\":\"lowresTest\"},\"title\":\"title\"}],\"pages\":1}";
    }

    @Test
    public void testParsedResponse() {
        OfferResponse offerResponse = gson.fromJson(mockedJson, OfferResponse.class);
        TestCase.assertNotNull(offerResponse);
        TestCase.assertEquals(gson.toJson(this.offerResponse), gson.toJson(offerResponse));
    }
}