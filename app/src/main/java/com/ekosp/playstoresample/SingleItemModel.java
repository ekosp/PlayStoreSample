package com.ekosp.playstoresample;

/**
 * Created by Eko Setyo Purnomo on 09-Mar-18.
 * Find me on https://ekosp.com
 * Or email me directly to ekosetyopurnomo@gmail.com
 */

public class SingleItemModel {
    private String name, url, description;

    public SingleItemModel() {

    }

    public SingleItemModel(String name, String url) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}