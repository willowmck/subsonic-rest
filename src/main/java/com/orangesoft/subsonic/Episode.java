package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 *
 *  Copyright 2015 Orangesoft
 */
public class Episode extends DescriptionObject
{
    final static String CHANNEL_ID = "channelId";
    final static String PUBLISH_DATE = "publishDate";
    
    private final int channelId;
    private final String publishDate;
    
    public Episode(JSONObject json) throws JSONException 
    {
        super(json);
        parseIsDir(json);
        channelId = json.getInt(CHANNEL_ID);
        publishDate = json.getString(PUBLISH_DATE);
    }
    
    public boolean isDir()
    {
        return isDir;
    }
    
    public int getChannelId()
    {
        return channelId;
    }
    
    public String getPublishDate()
    {
        return publishDate;
    }
}
