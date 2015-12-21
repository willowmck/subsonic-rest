package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class License
{
    final static String VALID = "valid";
    final static String EMAIL = "email";
    final static String TRIAL_EXPIRES = "trialExpires";

    private boolean valid;
    private String email;
    private String trialExpires;

    public License(JSONObject json) throws JSONException
    {
        valid = json.getBoolean(VALID);
        email = json.getString(EMAIL);
        trialExpires = json.getString(TRIAL_EXPIRES);
    }

    public boolean isValid()
    {
        return valid;
    }

    public String getEmail()
    {
        return email;
    }

    public String getTrialExpires()
    {
        return trialExpires;
    }
}
