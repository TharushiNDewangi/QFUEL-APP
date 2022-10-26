package ead.sliit.qfuel.api;

import java.util.List;

import ead.sliit.qfuel.model.StationOwner;
import ead.sliit.qfuel.model.VehicleOwner;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface JsonPlaceHolderApi {
    @GET("vehicleowners")
    Call<List<VehicleOwner>> getVDetails();

    @GET("fuelusers")
    Call<List<StationOwner>> getDetails();

    @POST("vehicleowners")
    Call<List<VehicleOwner>> postVDetails();

    @POST("fuelusers")
    Call<List<StationOwner>> postDetails();

    @PUT("upfuel")
    Call<List<StationOwner>> updateDetails();

    @PUT("upvehi")
    Call<List<VehicleOwner>> updatestationDetails();


}
