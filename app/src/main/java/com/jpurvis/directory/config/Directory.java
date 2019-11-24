package com.jpurvis.directory.config;

import com.jpurvis.directory.BuildConfig;

public class Directory {

    private static final String BUILD_TYPE_RELEASE = "release";
    private static final String BUILD_TYPE_DEV = "dev";

    private static final String SERVICE_URL_DEV = "https://5cc736f4ae1431001472e333.mockapi.io/api/v1/";
    private static final String SERVICE_URL_LIVE = "https://5cc736f4ae1431001472e333.mockapi.io/api/v1/"; /*TODO: Replace with live URL */


    public static String getServiceUrl() {
        if (BuildConfig.FLAVOR.contentEquals(BUILD_TYPE_RELEASE)) {
            return SERVICE_URL_LIVE;
        } else {
            return SERVICE_URL_DEV;
        }
    }
}
