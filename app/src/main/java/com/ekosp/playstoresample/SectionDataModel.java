package com.ekosp.playstoresample;

/**
 * Created by Eko Setyo Purnomo on 09-Mar-18.
 * Find me on https://ekosp.com
 * Or email me directly to ekosetyopurnomo@gmail.com
 */

import java.util.ArrayList;

public class SectionDataModel {
    private String headerTitle;
    private ArrayList<SingleItemModel> allItemInSection;

    public SectionDataModel() {
    }

    public SectionDataModel(String headerTitle, ArrayList<SingleItemModel> allItemInSection) {
        this.headerTitle = headerTitle;
        this.allItemInSection = allItemInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<SingleItemModel> getAllItemInSection() {
        return allItemInSection;
    }

    public void setAllItemInSection(ArrayList<SingleItemModel> allItemInSection) {
        this.allItemInSection = allItemInSection;
    }
}