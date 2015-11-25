package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import java.io.InputStream;
import java.util.Map;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class Stream extends StreamCommand
{
    final static String COMMAND = "stream.view";
    
    public Stream(Connection connection, Map<String, String> params)
    {
        this.connection = connection;
        this.params = params;
    }

    @Override
    public String getCommand()
    {
        return COMMAND;
    }
    
    public InputStream getStream()
    {
        if (resource != null)
            return resource.stream();
        else
            return null;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
