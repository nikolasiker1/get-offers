package com.nikolasiker.lib_api.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class OfferTest extends BaseDomainTest {

    private Offer offer = new Offer(
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
    );

    @Before
    public void setUp() {
        mockedJson = "{\"link\":\"testLink\",\"offerId\":\"offerId\",\"offerTypes\":[{\"payout\":\"testPayout\",\"timeToPayout\":\"testTimeToPayout\"},{\"payout\":\"testPayout1\",\"timeToPayout\":\"testTimeToPayout1\"}],\"requiredActions\":\"requiredActions\",\"teaser\":\"teaser\",\"thumbnail\":{\"hires\":\"hiresTest\",\"lowres\":\"lowresTest\"},\"title\":\"title\"}";
    }

    @Test
    public void testParsedOffer() {
        Offer offer = gson.fromJson(mockedJson, Offer.class);
        TestCase.assertNotNull(offer);
        TestCase.assertEquals(gson.toJson(this.offer), gson.toJson(offer));
    }
}