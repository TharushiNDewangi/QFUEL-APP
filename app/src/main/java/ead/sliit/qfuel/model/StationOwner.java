package ead.sliit.qfuel.model;

import com.google.gson.annotations.SerializedName;

public class StationOwner {
    public String Id;
    @SerializedName("location")
    public String Location;
    @SerializedName("type")
    public String Type;
    @SerializedName("start")
    public String Start;
    @SerializedName("end")
    public String End;

    public StationOwner(String id, String location, String type, String start, String end) {
        Id = id;
        Location = location;
        Type = type;
        Start = start;
        End = end;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }
}

