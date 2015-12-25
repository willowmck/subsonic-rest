package com.orangesoft.subsonic;

import java.util.ArrayList;
import java.util.List;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class SearchResult2 
{
    final static String ARTIST = "artist";
    final static String ALBUM = "album";
    final static String SONG = "song";
    
    private final List<Artist> artists;
    private final List<Album> albums;
    private final List<Song> songs;
    
    public SearchResult2(JSONObject json) throws JSONException
    {
        artists = new ArrayList<>();
        JSONArray artistArray = json.getJSONArray(ARTIST);
        for (int i=0; i<artistArray.length(); i++)
            artists.add(new Artist(artistArray.getJSONObject(i)));
        
        albums = new ArrayList<>();
        JSONArray albumArray = json.getJSONArray(ALBUM);
        for (int i=0; i<albumArray.length(); i++)
            albums.add(new Album(albumArray.getJSONObject(i)));
        
        songs = new ArrayList<>();
        JSONArray songArray = json.getJSONArray(SONG);
        for (int i=0; i<songArray.length(); i++)
            songs.add(new Song(songArray.getJSONObject(i)));
    }
    
    public List<Artist> getArtists()
    {
        return artists;
    }
    
    public List<Album> getAlbums()
    {
        return albums;
    }
    
    public List<Song> getSongs()
    {
        return songs;
    }
}
