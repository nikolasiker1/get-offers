package com.nikolasiker.lib_api.model;

public class Thumbnail {
    private String lowres;
    private String hires;

    public Thumbnail(String lowres, String hires) {
        this.lowres = lowres;
        this.hires = hires;
    }

    public String getLowres() {
        return lowres;
    }

    public String getHires() {
        return hires;
    }
}
