package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Channel;
import com.orangesoft.subsonic.system.Connection;
import java.util.ArrayList;
import java.util.List;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class GetPodcasts extends Command
{
    final static String COMMAND = "getPodcasts.view";
    final static String PODCASTS = "podcasts";
    final static String CHANNEL = "channel";
    private final List<Channel> channels;
    
    public GetPodcasts(Connection connection)
    {
        this.connection = connection;
        channels = new ArrayList<>();
    }
    
    @Override
    public String getCommand() 
    {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException 
    {
        JSONObject podcastsObject = json.getJSONObject(PODCASTS);
        JSONArray channelsArray = podcastsObject.getJSONArray(CHANNEL);
        for (int i=0; i<channelsArray.length(); i++)
        {
            channels.add(new Channel(channelsArray.getJSONObject(i)));
        }
    }
    
    public List<Channel> getChannels()
    {
        return channels;
    }
   
}
