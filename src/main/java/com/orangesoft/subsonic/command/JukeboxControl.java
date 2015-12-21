package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.JukeboxPlaylist;
import com.orangesoft.subsonic.system.Connection;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.util.Map;

/**
 * Copyright 2015 Orangesoft
 */
public class JukeboxControl extends Command
{
    final static String COMMAND = "jukeboxControl.view";
    final static String JUKEBOX_PLAYLIST = "jukeboxPlaylist";

    private JukeboxPlaylist jukeboxPlaylist;

    public JukeboxControl(Connection connection, Map<String, String> params)
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
        JSONObject jukeboxJson = json.getJSONObject(JUKEBOX_PLAYLIST);
        jukeboxPlaylist = new JukeboxPlaylist(jukeboxJson);
    }

    public JukeboxPlaylist getJukeboxPlaylist()
    {
        return jukeboxPlaylist;
    }
}
