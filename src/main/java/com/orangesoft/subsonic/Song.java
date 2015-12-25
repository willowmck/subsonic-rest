package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class Song extends TitledObject
{
    final static String PARENT = "parent";
    final static String ALBUM = "album";
    final static String ARTIST = "artist";
    final static String TRACK = "track";
    final static String YEAR = "year";
    final static String GENRE = "genre";
    final static String SIZE = "size";
    final static String CONTENT_TYPE = "contentType";
    final static String SUFFIX = "suffix";
    final static String DURATION = "duration";
    final static String BITRATE = "bitRate";
    final static String PATH = "path";
    final static String IS_VIDEO = "isVideo";
    final static String DISC_NUMBER = "discNumber";
    final static String CREATED = "created";
    final static String ALBUM_ID = "albumId";
    final static String ARTIST_ID = "artistId";
    final static String TYPE = "type";
    
    private final int parent;
    private final String album;
    private final String artist;
    private final int track;
    private int year;
    private final String genre;
    private final int size;
    private final String contentType;
    private final String suffix;
    private final int duration;
    private final int bitRate;
    private final String path;
    private final boolean isVideo;
    private final int discNumber;
    private final String created;
    private final String albumId;
    private final String artistId;
    private final String type;
    
    public Song(JSONObject json) throws JSONException
    {
        super(json);
        parent = json.getInt(PARENT);
        parseIsDir(json);
        album = json.getString(ALBUM);
        artist = json.getString(ARTIST);
        track = json.getInt(TRACK);
        year = json.optInt(YEAR);
        genre = json.optString(GENRE);
        parseCoverArt(json);
        size = json.getInt(SIZE);
        contentType = json.getString(CONTENT_TYPE);
        suffix = json.getString(SUFFIX);
        duration = json.getInt(DURATION);
        bitRate = json.getInt(BITRATE);
        path = json.getString(PATH);
        isVideo = json.getBoolean(IS_VIDEO);
        discNumber = json.optInt(DISC_NUMBER);
        created = json.getString(CREATED);
        albumId = json.getString(ALBUM_ID);
        artistId = json.optString(ARTIST_ID);
        type = json.getString(TYPE);
    }
    
    public int getParent()
    {
        return parent;
    }
    
    public boolean isDir()
    {
        return isDir;
    }
    
    public String getAlbum()
    {
        return album;
    }
    
    public String getArtist()
    {
        return artist;
    }
    
    public int getTrack()
    {
        return track;
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
    
    public int getSize()
    {
        return size;
    }
    
    public String getContentType()
    {
        return contentType;
    }
    
    public String getSuffix()
    {
        return suffix;
    }
    
    public int getDuration()
    {
        return duration;
    }
    
    public int getBitRate()
    {
        return bitRate;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public boolean isVideo()
    {
        return isVideo;
    }
    
    public int getDiscNumber()
    {
        return discNumber;
    }
    
    public String getCreated()
    {
        return created;
    }
    
    public String getAlbumId()
    {
        return albumId;
    }
    
    public String getArtistId()
    {
        return artistId;
    }
    
    public String getType()
    {
        return type;
    }
}
