package com.instantgcm.middleware.net;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.instantgcm.middleware.model.PushPayload;
import com.instantgcm.middleware.model.PushResponse;
import com.instantgcm.middleware.model.UpdateFavoriteRequest;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Sneha Khadatare : 587823
 * on 4/14/2016.
 */

public class MiddlewareDBClient {

    private MiddlewareAPI middlewareService = null;
    private static MiddlewareDBClient dbClient;


    private MiddlewareDBClient(MiddlewareDataInterface client){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(client.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create());

        /*if(client.isLoggingEnabled()){
           retrofitBuilder.client(getLoggingClient());
        }
*/
        final Retrofit retrofit = retrofitBuilder.build();

        middlewareService = retrofit.create(MiddlewareAPI.class);
    }

    public static MiddlewareDBClient getInstance(MiddlewareDataInterface pushClient) {

        if (dbClient == null) {
            dbClient = new MiddlewareDBClient(pushClient);
        }

        return dbClient;

    }

    public void registerRequest(PushPayload payloadBean ,
                                final InstantGCMRegistrationListener instantGCMRegistrationListener) {

        Call<PushResponse> call = middlewareService.registerCall(payloadBean);

        call.enqueue(new Callback<PushResponse>() {
            @Override
            public void onResponse(Response<PushResponse> response,
                                   Retrofit retrofit) {

                if(response!=null && response.body()!=null){
                    PushResponse postResponse = response.body();
                    instantGCMRegistrationListener.onRegisterSuccess(postResponse);
                }else{
                    instantGCMRegistrationListener.onRegisterFailure("Unable to Register");
                }

                Log.d("@@@" , "RegisterRequest onResponse called");
            }

            @Override
            public void onFailure(Throwable t) {

                instantGCMRegistrationListener.onRegisterFailure("Unable to Register");

                Log.d("@@@" , "RegisterRequest onFailure called");
            }
        });
    }

    public void deRegisterRequest(PushPayload payloadBean ,
                                  final InstantGCMDeregistrationListener instantGCMDeregistrationListener) {

        Call<PushResponse> call = middlewareService.deRegisterCall(payloadBean);

        call.enqueue(new Callback<PushResponse>() {
            @Override
            public void onResponse(Response<PushResponse> response,
                                   Retrofit retrofit) {

                if(response!=null && response.body()!=null){
                    PushResponse postResponse = response.body();
                    instantGCMDeregistrationListener.onDeregisterSuccess(postResponse);
                }else{
                    instantGCMDeregistrationListener.onDeregisterFailure("Unable to Deregister");
                }
                Log.d("@@@" , "DeRegisterRequest onResponse called");

            }

            @Override
            public void onFailure(Throwable t) {

                instantGCMDeregistrationListener.onDeregisterFailure("Unable to Deregister");
                Log.d("@@@" , "DeRegisterRequest onFailure called");

            }
        });
    }

    public void postFavoriteRequest(UpdateFavoriteRequest payloadBean ,
                                    final InstantGCMPostFavListener instantGCMPostFavListener) {

        Call<PushResponse> call = middlewareService.postFavoritesCall(payloadBean);

        call.enqueue(new Callback<PushResponse>() {
            @Override
            public void onResponse(Response<PushResponse> response,
                                   Retrofit retrofit) {

                if(response!=null && response.body()!=null){
                    PushResponse postResponse = response.body();
                    instantGCMPostFavListener.onPostFavUpdatedSuccess(postResponse);
                }else{
                    instantGCMPostFavListener.onPostFavUpdatedFailure("Unable to post the Favorites");
                }
                Log.d("@@@" , "postFavoriteRequest onResponse called");

            }

            @Override
            public void onFailure(Throwable t) {

                instantGCMPostFavListener.onPostFavUpdatedFailure("Unable to post the Favorites");
                Log.d("@@@" , "postFavoriteRequest onFailure called");

            }
        });
    }


    public static OkHttpClient getLoggingClient() {
        OkHttpClient okHttpClient = new OkHttpClient();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(interceptor);

        return okHttpClient;
    }
}
