package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class TitledObject extends DataObject
{
    final static String TITLE = "title";
    
    private String title;
    public TitledObject(JSONObject json) throws JSONException 
    {
        super(json);
        title = json.getString(TITLE);
    }
    
    public String getTitle()
    {
        return title;
    }
    
}
