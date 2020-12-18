package com.nikolasiker.lib_api.model;

import com.google.gson.Gson;

public abstract class BaseDomainTest {
    protected String mockedJson;
    protected final Gson gson = new Gson();
}
