/*
 * Copyright 2015 Orangesoft.
 */
package com.orangesoft.subsonic.system;

import com.orangesoft.subsonic.command.Command;
import us.monoid.json.JSONObject;
import us.monoid.json.JSONException;
import java.util.Map;

public class Ping extends Command
{
    static final String COMMAND = "ping.view";
    
    public Ping(Connection connection) 
    {
        this.connection = connection;
    }
    
    @Override
    public void parseObjects( JSONObject json ) throws JSONException
    {
        
    }
    
    @Override
    public String getCommand() { return COMMAND; }
    
    @Override
    public Map<String, String> getParams() { return null; }
}
