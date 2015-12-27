package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Playlist;
import com.orangesoft.subsonic.system.Connection;
import java.util.Map;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class GetPlaylist extends Command
{
    private final static String COMMAND = "getPlaylist.view";
    private final static String PLAYLIST = "playlist";
    
    private Playlist playlist;
    
    public GetPlaylist(Connection connection, Map<String, String> params)
    {
        this.connection = connection;
        this.params = params;
    }
    
    @Override
    public String getCommand() 
    {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException 
    {
        JSONObject playlistObj = json.getJSONObject(PLAYLIST);
        playlist = new Playlist(playlistObj);
    }
    
    public Playlist getPlaylist()
    {
        return playlist;
    }
}
