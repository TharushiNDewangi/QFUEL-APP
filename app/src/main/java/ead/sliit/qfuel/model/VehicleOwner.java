package ead.sliit.qfuel.model;

import com.google.gson.annotations.SerializedName;

public class VehicleOwner {
    public String Id;
    @SerializedName("neareststation")
    public String Neareststation;
    @SerializedName("type")
    public String Type;
    @SerializedName("arrivaltime")
    public String Arrivaltime;
    @SerializedName("departtime")
    public String Departtime;

    public VehicleOwner(String id, String neareststation, String type, String arrivaltime, String departtime) {
        Id = id;
        Neareststation = neareststation;
        Type = type;
        Arrivaltime = arrivaltime;
        Departtime = departtime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNeareststation() {
        return Neareststation;
    }

    public void setNeareststation(String neareststation) {
        Neareststation = neareststation;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getArrivaltime() {
        return Arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        Arrivaltime = arrivaltime;
    }

    public String getDeparttime() {
        return Departtime;
    }

    public void setDeparttime(String departtime) {
        Departtime = departtime;
    }
}
