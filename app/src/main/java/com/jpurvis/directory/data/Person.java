package com.jpurvis.directory.data;

import com.jpurvis.directory.config.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Person {
    private int id;
    private Date createdAt;
    private String avatarURL;
    private String jobTitle;
    private String phone;
    private String favouriteColor;
    private String email;
    private String firstName;
    private String lastName;

    public Person(JSONObject jsonObject) {
        try {
            this.id = Integer.parseInt(jsonObject.getString("id"));
            this.jobTitle = jsonObject.getString("jobTitle");
            this.phone = jsonObject.getString("phone");
            this.favouriteColor = jsonObject.getString("favouriteColor");
            this.email = jsonObject.getString("email");
            this.firstName = jsonObject.getString("firstName");
            this.lastName = jsonObject.getString("lastName");
            this.createdAt = Util.timezoneDateFromString(jsonObject.getString("createdAt"));
            this.avatarURL = jsonObject.getString("avatar");
        } catch (JSONException e) {
            // Don't create the object
        }
    }

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public String getFavouriteColor() {
        return favouriteColor;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}