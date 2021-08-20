package com.example.myvault;

public class DistrictData {

    private String districtName;
    private int confirmed, active, recovered, deceased, dConfirmed, dRecovered, dDeceased;

    public DistrictData(String districtName, int confirmed, int active, int recovered, int deceased, int dConfirmed, int dRecovered, int dDeceased) {
        this.districtName = districtName;
        this.confirmed = confirmed;
        this.active = active;
        this.recovered = recovered;
        this.deceased = deceased;
        this.dConfirmed = dConfirmed;
        this.dRecovered = dRecovered;
        this.dDeceased = dDeceased;
    }

    public String getDistrictName() {
        return districtName;
    }

    public int getActive() {
        return active;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getDeceased() {
        return deceased;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getdRecovered() {
        return dRecovered;
    }

    public int getdDeceased() {
        return dDeceased;
    }

    public int getdConfirmed() {
        return dConfirmed;
    }
}
