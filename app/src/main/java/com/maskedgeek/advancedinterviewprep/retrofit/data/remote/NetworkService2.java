package com.maskedgeek.advancedinterviewprep.retrofit.data.remote;

import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.request.DummyRequest;
import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.response.DummyResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkService2 {


    @POST(EndPoints.dummy)
    Single<DummyResponse> doDummyCall(
            @Body DummyRequest dummyRequest,
            @Header(Networking.HEADER_API_KEY) String apiKey
    );

    @GET(EndPoints.dummy)
    Single<DummyResponse> doDummyGetCall(
            @Header(Networking.HEADER_API_KEY) String apiKey
    );

    @GET(EndPoints.dummy)
    Single<DummyResponse> doDummyGetQueryCall(
            @Header(Networking.HEADER_API_KEY) String apiKey,
            @Query("postId") String id
    );

    @DELETE(EndPoints.dummy)
    Single<DummyResponse> doDummyGetDeleteCall(
            @Header(Networking.HEADER_API_KEY) String apiKey,
            @Path("postId") String id
    );


}
