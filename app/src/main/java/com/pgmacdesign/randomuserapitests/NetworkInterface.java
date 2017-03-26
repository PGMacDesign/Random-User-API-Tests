package com.pgmacdesign.randomuserapitests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Network interface for retrofit calls
 * Created by PatrickSSD2 on 3/25/2017.
 */
public interface NetworkInterface {

    static final String API_BASE = "/api";

    /**
     * Get Users from the API
     * @param seed Custom seed. For this project I will be using one custom, unchanging seed
     * @param numResults Int, number of results to return
     * @param numPage Int, which page number to be on (For pagination)
     * @param gender String gender, to return only people of that gender
     * @param customFormat String, custom return format, defaults to json, but can do xml + more
     * @param commaDelFieldsToExclude String, comma-separated string of fields to
     *                                exclude (IE: gender, name, nat) etc.
     * @param makeDownloadable Send in the String dl if you want the return to be downloadable.
     *                         This is useful for parsing and storing locally.
     * @return {@link RandomUserPojo}
     */
    @GET(API_BASE)
    Call<RandomUserPojo> getUsers(@Query("seed") String seed,
                                  @Query("results") int numResults,
                                  @Query("page") int numPage,
                                  @Query("gender") String gender,
                                  @Query("format") String customFormat,
                                  @Query("exc") String commaDelFieldsToExclude,
                                  @Query("dl") String makeDownloadable
    );


}
