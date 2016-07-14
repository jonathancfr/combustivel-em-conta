package br.inf.combustivelemconta.models;

import java.util.List;

public class GasStation {
    private String mapsId;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private float geofenceRadius;
    private List<Price> priceList;

    public GasStation() {}

    public GasStation(String name, String address, double latitude, double longitude, float geofenceRadius) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geofenceRadius = geofenceRadius;
    }

    public GasStation(String mapsId, String name, String address, double latitude, double longitude, float geofenceRadius) {
        this.mapsId = mapsId;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geofenceRadius = geofenceRadius;
    }

    public String getMapsId() {
        return mapsId;
    }

    public void setMapsId(String id) {
        this.mapsId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getGeofenceRadius() {
        return geofenceRadius;
    }

    public void setGeofenceRadius(float geofenceRadius) {
        this.geofenceRadius = geofenceRadius;
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }
}
