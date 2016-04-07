package com.durodola.mobile.naijahealthcentre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobile on 2016-03-16.
 */
public class NameClass {
    String name;
    String lga;
    String town;
    String contractor;
    String impact;
    List<String> testname;
    List<String> testlgs;


    public String getName() {
        return name;
    }

    NameClass(String town, String lga) {
        this.town = town;
        this.lga = lga;
        //  this.photoId = photoId;
    }

    NameClass(List<String> name, List<String> lga) {
        this.testname = name;
        this.testlgs = lga;
        //  this.photoId = photoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }


}
