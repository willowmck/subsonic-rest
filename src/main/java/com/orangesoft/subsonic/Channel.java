package com.orangesoft.subsonic;

import java.util.ArrayList;
import java.util.List;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 *
 * Copyright 2015 Orangesoft
 */
public class Channel extends DescriptionObject
{
    final static String URL = "url";
    final static String ORIGINAL_IMAGE_URL = "originalImageUrl";
    final static String EPISODE = "episode";
    
    private final String url;
    private final String originalImageUrl;
    private List<Episode> episodes;
    
    public Channel(JSONObject json) throws JSONException 
    {
        super(json);
        url = json.getString(URL);
        parseCoverArt(json);
        originalImageUrl = json.getString(ORIGINAL_IMAGE_URL);
        episodes = new ArrayList<>();
        JSONArray episodesArray = json.getJSONArray(EPISODE);
        for (int i = 0; i < episodesArray.length(); i++)
        {
            episodes.add(new Episode(episodesArray.getJSONObject(i)));
        }
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public String getCoverArt()
    {
        return coverArt;
    }
    
    public String getOriginalImageUrl()
    {
        return originalImageUrl;
    }
    
    public List<Episode> getEpisodes()
    {
        return episodes;
    }
    
}
