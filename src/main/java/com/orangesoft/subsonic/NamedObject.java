/*
 * Copyright 2015 Orangesoft
 */
package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class NamedObject extends DataObject
{
    public final static String NAME = "name";
    private final String name;
    
    public NamedObject(JSONObject json) throws JSONException
    {
        super(json);
        this.name = json.getString(NAME);
    }
    
    public String getName()
    {
        return name;
    }
}
