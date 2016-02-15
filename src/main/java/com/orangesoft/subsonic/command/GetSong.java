package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Song;
import com.orangesoft.subsonic.system.Connection;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.util.Map;

/**
 * Copyright 2016 Orangesoft
 */
public class GetSong extends Command
{
    private final static String COMMAND = "getSong.view";
    private final static String SONG = "song";

    private Song song;

    public GetSong(Connection connection, Map<String, String> params)
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
        JSONObject songObj = json.getJSONObject(SONG);
        song = new Song(songObj);
    }

    public Song getSong()
    {
        return song;
    }
}
