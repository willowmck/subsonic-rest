package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class InternetRadioStation extends NamedObject
{
    final static String STREAM_URL = "streamUrl";
    final static String HOME_PAGE_URL = "homePageUrl";

    private String streamUrl;
    private String homePageUrl;

    public InternetRadioStation(JSONObject json) throws JSONException
    {
        super(json);
        streamUrl = json.getString(STREAM_URL);
        homePageUrl = json.getString(HOME_PAGE_URL);
    }

    public String getStreamUrl()
    {
        return streamUrl;
    }

    public String getHomePageUrl()
    {
        return homePageUrl;
    }
}
