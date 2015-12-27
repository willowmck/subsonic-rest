/*
 * Copyright 2015 Orangesoft
 */
package com.orangesoft.subsonic;

import java.util.ArrayList;
import java.util.List;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;


public class Playlist extends NamedObject
{
    public final static String COMMENT = "comment";
    public final static String OWNER = "owner";
    public final static String PUBLIC = "public";
    public final static String SONG_COUNT = "songCount";
    public final static String DURATION = "duration";
    public final static String CREATED = "created";
    public final static String CHANGED = "changed";
    public final static String ENTRY = "entry";
    private final String comment;
    private final String owner;
    private final boolean isPublic;
    private final int songCount;
    private final int duration;
    private final String created;
    private final String changed;
    private final List<Entry> entries;
    
    public Playlist(JSONObject json) throws JSONException
    {
        super(json);
        comment = json.getString(COMMENT);
        owner = json.getString(OWNER);
        isPublic = json.getBoolean(PUBLIC);
        songCount = json.getInt(SONG_COUNT);
        duration = json.getInt(DURATION);
        created = json.getString(CREATED);
        changed = json.getString(CHANGED);
        coverArt = json.getString(COVER_ART);
        entries = new ArrayList<>();
        JSONArray entriesArray = json.optJSONArray(ENTRY);
        if (entriesArray != null)
            for (int i=0; i<entriesArray.length(); i++)
                entries.add(new Entry(entriesArray.getJSONObject(i)));
    }
    
    public String getComment()
    {
        return comment;
    }
    
    public String getOwner()
    {
        return owner;
    }
    
    public boolean isPublic()
    {
        return isPublic;
    }
    
    public int getSongCount()
    {
        return songCount;
    }
    
    public int getDuration()
    {
        return duration;
    }
    
    public String getCreated()
    {
        return created;
    }
    
    public String getChanged()
    {
        return changed;
    }
    
    public String getCoverArt()
    {
        return coverArt;
    }
    
    public List<Entry> getEntries()
    {
        return entries;
    }
}
