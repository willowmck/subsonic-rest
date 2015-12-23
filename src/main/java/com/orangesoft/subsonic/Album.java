/*
 * Copyright 2015 Orangesoft
 */
package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class Album extends DataObject
{
    private final static String PARENT = "parent";
    private final static String ALBUM = "album";
    private final static String ARTIST = "artist";
    private final static String YEAR = "year";
    private final static String GENRE = "genre";
    private final static String CREATED = "created";
    private final static String TITLE = "title";
    private final static String NAME = "name";
    private final static String ARTIST_ID = "artistId";
    private final static String SONG_COUNT = "songCount";
    private final static String DURATION = "duration";
    
    private final int parent;
    private final String albumName;
    private final String artist;
    private int year = UNDEFINED_INT;
    private String genre;
    private String coverArt;
    private final String created;
    private final String title;
    private final String name;
    private final int artistId;
    private final int songCount;
    private final int duration;
    
    public Album(JSONObject json) throws JSONException
    {
        super(json);
        parent = json.optInt(PARENT);
        parseIsDir(json);
        albumName = json.optString(ALBUM);
        artist = json.getString(ARTIST);
        created = json.getString(CREATED);
        genre = json.optString(GENRE);

        coverArt = json.optString(COVER_ART);
        
        if (json.has(YEAR))
            year = json.optInt(YEAR);

        title = json.optString(TITLE);
        name = json.optString(NAME);
        artistId = json.optInt(ARTIST_ID);
        songCount = json.optInt(SONG_COUNT);
        duration = json.optInt(DURATION);
        
    }
    
    public int getParent()
    {
        return parent;
    }
    
    public boolean isDir()
    {
        return isDir;
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
    
    public String getGenre()
    {
        return genre;
    }
    
    public String getCoverArt()
    {
        return coverArt;
    }
    
    public String getCreated()
    {
        return created;
    }

    public String getTitle()
    {
        return title;
    }

    public String getName()
    {
        return name;
    }

    public int getSongCount()
    {
        return songCount;
    }

    public int getArtistId()
    {
        return artistId;
    }

    public int getDuration()
    {
        return duration;
    }
    
}
