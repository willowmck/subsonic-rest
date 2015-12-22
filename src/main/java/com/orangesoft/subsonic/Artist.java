package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class Artist extends NamedObject
{
    public Artist(JSONObject json) throws JSONException
    {
        super(json);
    }
}
