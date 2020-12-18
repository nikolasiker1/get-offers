package com.nikolasiker.lib_api.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class ThumbnailTest extends BaseDomainTest {

    private Thumbnail thumbnail = new Thumbnail(
            "testLowRes",
            "testHiRes"
    );

    @Before
    public void setUp() {
        mockedJson = "{\"hires\":\"testHiRes\",\"lowres\":\"testLowRes\"}";
    }

    @Test
    public void testParsedThumbnail() {
        Thumbnail thumbnail = gson.fromJson(mockedJson, Thumbnail.class);
        TestCase.assertNotNull(thumbnail);
        TestCase.assertEquals(gson.toJson(this.thumbnail), gson.toJson(thumbnail));
    }
}