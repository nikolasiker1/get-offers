package com.nikolasiker.lib_api.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


public class InformationTest extends BaseDomainTest {

    private Information information = new Information(
            "testAppName",
            "testAppId",
            "testVirtualCurrency",
            "testLanguage",
            "testSupportUrl"
    );

    @Before
    public void setUp() {
        mockedJson = "{\"appName\":\"testAppName\",\"appid\":\"testAppId\",\"language\":\"testLanguage\",\"supportUrl\":\"testSupportUrl\",\"virtualCurrency\":\"testVirtualCurrency\"}";
    }

    @Test
    public void testParsedInformation() {
        Information information = gson.fromJson(mockedJson, Information.class);
        TestCase.assertNotNull(information);
        TestCase.assertEquals(gson.toJson(this.information), gson.toJson(information));
    }
}