/*
 * Copyright 2015 Orangesoft
 */
package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Playlist;
import com.orangesoft.subsonic.system.Connection;
import java.util.ArrayList;
import java.util.List;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class GetPlaylists extends Command
{
    private final static String COMMAND = "getPlaylists.view";
    private final static String PLAYLISTS = "playlists";
    private final static String PLAYLIST = "playlist";
    private List<Playlist> playlists;
    
    public GetPlaylists(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public String getCommand()
    {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException
    {
        playlists = new ArrayList<>();
        JSONObject playlistsObject = json.getJSONObject(PLAYLISTS);
        JSONArray playlistArray = playlistsObject.getJSONArray(PLAYLIST);
        for (int i=0; i<playlistArray.length(); i++)
        {
            JSONObject playlistObj = playlistArray.getJSONObject(i);
            Playlist playlist = new Playlist(playlistObj);
            playlists.add(playlist);
        }
    }
    
    public List<Playlist> getList()
    {
        return playlists;
    }
    
}
