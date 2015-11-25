/*
 * Copyright 2015 Orangesoft
 */
package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class Album extends DataObject
{
    private final static String PARENT = "parent";
    private final static String IS_DIR = "isDir";
    private final static String TITLE = "title";
    private final static String ALBUM = "album";
    private final static String ARTIST = "artist";
    private final static String YEAR = "year";
    private final static String GENRE = "genre";
    private final static String CREATED = "created";
    private final static String COVER_ART = "coverArt";
    
    private final int parent;
    private final boolean isDir;
    private final String title;
    private final String albumName;
    private final String artist;
    private int year = UNDEFINED_INT;
    private int genre = UNDEFINED_INT;
    private int coverArt = UNDEFINED_INT;
    private final String created;
    
    public Album(JSONObject json) throws JSONException
    {
        super(json);
        parent = json.getInt(PARENT);
        isDir = json.getBoolean(IS_DIR);
        title = json.getString(TITLE);
        albumName = json.getString(ALBUM);
        artist = json.getString(ARTIST);
        created = json.getString(CREATED);
        if (json.has(GENRE))
            genre = json.optInt(GENRE);
        
        if (json.has(COVER_ART))
            coverArt = json.optInt(COVER_ART);
        
        if (json.has(YEAR))
            year = json.optInt(YEAR);
        
    }
    
    public int getParent()
    {
        return parent;
    }
    
    public boolean isDir()
    {
        return isDir;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getAlbumName()
    {
        return albumName;
    }
    
    public String getArtist()
    {
        return artist;
    }
    
    public int getYear()
    {
        return year;
    }
    
    public int getGenre()
    {
        return genre;
    }
    
    public int getCoverArt()
    {
        return coverArt;
    }
    
    public String getCreated()
    {
        return created;
    }
    
}
