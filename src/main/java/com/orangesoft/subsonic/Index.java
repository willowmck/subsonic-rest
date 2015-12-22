package com.orangesoft.subsonic;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 Orangesoft
 */
public class Index
{
    final static String NAME = "name";
    final static String ARTIST = "artist";

    private String name;
    private List<Artist> artists;

    public Index(JSONObject json) throws JSONException
    {
        name = json.getString(NAME);
        artists = new ArrayList<>();
        JSONArray artistArray = json.getJSONArray(ARTIST);
        for (int i=0; i<artistArray.length(); i++)
            artists.add(new Artist(artistArray.getJSONObject(i)));
    }

    public String getName()
    {
        return name;
    }

    public List<Artist> getArtists()
    {
        return artists;
    }
}
