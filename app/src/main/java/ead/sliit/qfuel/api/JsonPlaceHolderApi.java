package ead.sliit.qfuel.api;

import java.util.List;

import ead.sliit.qfuel.model.StationOwner;
import ead.sliit.qfuel.model.VehicleOwner;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface JsonPlaceHolderApi {
    //vehicle owners
    @GET("vehicleowners")
    Call<List<VehicleOwner>> getVDetails();

    @POST("vehicleowners")
    Call<List<VehicleOwner>> postVDetails(@Body VehicleOwner vehicleOwner);

    //station owner
    @GET("stationowners")
    Call<List<StationOwner>> getDetails();

    @POST("stationowners")
    Call<StationOwner> postDetails(@Body StationOwner stationOwner);

    @GET("bynerestloc/{location}")
    //Call<List<StationOwner>> getDetailsByLocation(@Path(value="location") String location);
    Call<StationOwner> getDetailsByLocation(@Path(value="location") String location);

    @PUT("upstationowner/{id}")
    Call<StationOwner> updateDetails(@Path(value="id") String id,@Body StationOwner stationOwner);

    @PUT("upvehi")
    Call<List<VehicleOwner>> updatestationDetails();


}
