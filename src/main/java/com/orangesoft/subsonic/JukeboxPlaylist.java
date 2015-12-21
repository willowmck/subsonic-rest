package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class JukeboxPlaylist
{
    final static String CURRENT_INDEX = "currentIndex";
    final static String PLAYING = "playing";
    final static String GAIN = "gain";
    final static String POSITION = "position";

    private int currentIndex;
    private boolean playing;
    private double gain;
    private int position;

    public JukeboxPlaylist(JSONObject json) throws JSONException
    {
        currentIndex = json.getInt(CURRENT_INDEX);
        playing = json.getBoolean(PLAYING);
        gain = json.getDouble(GAIN);
        position = json.getInt(POSITION);
    }

    public int getCurrentIndex()
    {
        return currentIndex;
    }

    public boolean isPlaying()
    {
        return playing;
    }

    public double getGain()
    {
        return gain;
    }

    public int getPosition()
    {
        return position;
    }
}
