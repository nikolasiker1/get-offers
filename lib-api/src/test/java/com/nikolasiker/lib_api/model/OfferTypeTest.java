package com.nikolasiker.lib_api.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class OfferTypeTest extends BaseDomainTest {

    private OfferType offerType = new OfferType("testPayout", "testTimeToPayout");


    @Before
    public void setUp() {
        mockedJson = "{\"payout\":\"testPayout\",\"timeToPayout\":\"testTimeToPayout\"}";
    }

    @Test
    public void testParsedOfferType() {
        OfferType offerType = gson.fromJson(mockedJson, OfferType.class);
        TestCase.assertNotNull(offerType);
        TestCase.assertEquals(gson.toJson(this.offerType), gson.toJson(offerType));
    }
}