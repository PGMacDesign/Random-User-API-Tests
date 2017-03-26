package com.pgmacdesign.randomuserapitests;

import android.content.Context;

import com.pgmacdesign.pgmacutilities.adaptersandlisteners.OnTaskCompleteListener;
import com.pgmacdesign.pgmacutilities.networkclasses.retrofitutilities.RetrofitClient;
import com.pgmacdesign.pgmacutilities.nonutilities.PGMacUtilitiesConstants;
import com.pgmacdesign.pgmacutilities.utilities.NetworkUtilities;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class for API Calls. Will likely only be one, but still modularizing it regardless
 * Created by PatrickSSD2 on 3/25/2017.
 */
public class APICalls {

    private static NetworkInterface client;
    private static Context context;
    /**
     * Static builder for hte interface client.
     */
    private static void init() {
        MyApplication.getInstance();
        if(client == null){
            RetrofitClient.Builder builder = new RetrofitClient.Builder(
                    NetworkInterface.class, Constants.URL_BASE);
            builder.setDateFormat(Constants.DATE_FORMAT);
            if(BuildConfig.DEBUG) {
                builder.setLogLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                builder.setLogLevel(HttpLoggingInterceptor.Level.NONE);
            }
            builder.setTimeouts(
                    (int) PGMacUtilitiesConstants.ONE_MINUTE,
                    (int)PGMacUtilitiesConstants.ONE_MINUTE);
            RetrofitClient client1 = builder.build();
            client = client1.buildServiceClient();
        }
        if(context == null){
            context = MyApplication.getContext();
        }
    }

    /**
     * Simple check for internet connection. If it is not avail, the call will not begin
     * {@link NetworkUtilities}
     * @return Boolean, true if internet is connected and call can be made.
     */
    private static boolean canProceed(){
        try {
            return NetworkUtilities.haveNetworkConnection(MyApplication.getContext());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * API Call to retrieve the users from the RandomUser web call
     * @param listener Listener to pass data back on.
     *                 {@link OnTaskCompleteListener}
     * @param pageNumber The page number to view. Used in pagination
     */
    public static void getUsers(final OnTaskCompleteListener listener, Integer pageNumber){
        if(listener == null){
            return;
        }
        init();
        if(!canProceed()){
            listener.onTaskComplete(Constants.STRING_NO_CONNECTIVITY,
                    Constants.TAG_NO_NETWORK_CONNECTION);
            return;
        }
        if(pageNumber == null){
            pageNumber = 0;
        }
        Call<RandomUserPojo> call = client.getUsers(Constants.API_CALL_SEED,
                Constants.NUM_RESULTS_INT, pageNumber, null, null, null, null);
        call.enqueue(new Callback<RandomUserPojo>() {
            @Override
            public void onResponse(Call<RandomUserPojo> call, Response<RandomUserPojo> response) {

                String error = Constants.STRING_UNKNOWN_ERROR;
                if(response.isSuccessful()){
                    RandomUserPojo pojo = (RandomUserPojo) response.body();
                    if(pojo != null) {
                        listener.onTaskComplete(pojo, Constants.TAG_RETROFIT_CALL_SUCCESS);
                    } else {
                        listener.onTaskComplete(error, Constants.TAG_RETROFIT_CALL_FAILURE);
                    }
                } else {
                    //Call was not successful
                    try {
                        error = response.errorBody().string();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    listener.onTaskComplete(error, Constants.TAG_RETROFIT_CALL_FAILURE);
                }
            }

            @Override
            public void onFailure(Call<RandomUserPojo> call, Throwable throwable) {
                throwable.printStackTrace();
                listener.onTaskComplete(throwable.getMessage(),
                        Constants.TAG_RETROFIT_CALL_FAILURE);
            }
        });
    }
}

