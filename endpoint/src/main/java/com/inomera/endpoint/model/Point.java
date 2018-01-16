package com.inomera.endpoint.model;

/**
 * Created by Hakan Bingöl on 13/01/17.
 */

public class Point {
    private String name;
    private String url;

    public Point(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
