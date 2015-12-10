package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import java.util.Map;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class Star extends Command 
{
    private final static String COMMAND = "star.view";
    
    public Star(Connection connection, Map<String, String> params)
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
    }
    
}
