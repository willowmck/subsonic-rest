package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class Entry extends Song
{
    
    public Entry(JSONObject json) throws JSONException
    {
        super(json);
    }
}
