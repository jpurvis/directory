package com.jpurvis.directory.network;

import android.os.AsyncTask;

import com.jpurvis.directory.config.Directory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public abstract class ServiceConnection extends AsyncTask<String, String, String> {
    private static final int maxRetries = 3;

    private int retryCount = 0;

    protected String connect(String endpoint) {
        int statusCode;

        try {
            URL url = new URL(getUrl(endpoint));
            HttpsURLConnection httpURLConnection = createConnection(url);
            statusCode = getResponseCode(httpURLConnection);
            return responseString(statusCode, httpURLConnection);
        } catch (MalformedURLException e) {
            return null;
        } catch (SocketTimeoutException e) {
            if (retryCount < maxRetries) {
                retryCount++;
                connect(endpoint);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }

        return null;
    }

    private String getUrl(String endpoint) {
        return Directory.getServiceUrl() + endpoint;
    }

    private int getResponseCode(HttpsURLConnection httpConnection) throws IOException {
        int responseCode;
        try {
            // Will throw an IOException if the server responds with 401
            responseCode = httpConnection.getResponseCode();
        } catch (Exception e) {
            // Will now return 401 because the connection has a correct internal state
            responseCode = httpConnection.getResponseCode();
        }
        return responseCode;
    }

    /**
     * Builds the connection request that the app needs to send to the service
     *
     * @param url - the service url
     **/
    private HttpsURLConnection createConnection(URL url) throws IOException {
        HttpsURLConnection httpConnection;
        httpConnection = (HttpsURLConnection) url.openConnection();
        return httpConnection;
    }

    /**
     * Receive the response and convert it to a string to return
     **/
    private String responseString(int statusCode, HttpsURLConnection httpConnection) throws IOException {
        BufferedReader reader;
        if (statusCode < 300) {
            reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), StandardCharsets.UTF_8));
        }
        StringBuilder builder = new StringBuilder();
        for (String line; (line = reader.readLine()) != null; ) {
            builder.append(line).append("\n");
        }
        return builder.toString();
    }

    /**
     * HTTP Error response with status code.
     **/
    private String httpError(int errorCode) {
        return String.valueOf(errorCode);
    }

}
