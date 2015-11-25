/*
 * Copyright 2015 Orangesoft
 */
package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import java.util.Map;
import java.io.IOException;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public abstract class Command 
{
    static final String STATUS = "status";
    static final String OK = "ok";
    static final String ERROR = "error";
    static final String MESSAGE = "message";
    static final String BAD_HOST_ERROR = "Unable to connect to host";
    static final String AUTH_FAILURE = "Wrong username or password.";
    static final String PARSE_ERROR = "Unable to parse response";
    protected Connection connection;
    protected String failureMessage = "";
    protected boolean status = false;
    protected Map<String, String> params;
    
    public void execute()
    {
        try
        {
            connection.doCommand(this);
        }
        catch (IOException e)
        {
            if (e.getMessage().contains("401"))
                failureMessage = AUTH_FAILURE;
            else
                failureMessage = BAD_HOST_ERROR;
        }
        catch (JSONException e)
        {
            failureMessage = PARSE_ERROR;
        }
    }
    
    public void parse( JSONObject json )
    {
        try 
        {
            if ( json.getString(STATUS).equals(OK) )
            {
                status = true;
                parseObjects(json);
            }
            else
                parseError( json );
        }
        catch (JSONException e)
        {
            failureMessage = PARSE_ERROR;
        }
    }
    
    public boolean getStatus() { return status; }
    
    private void parseError(JSONObject subsonicResponse) throws JSONException
    {
        JSONObject error = subsonicResponse.getJSONObject(ERROR);
        failureMessage = error.getString(MESSAGE);
    }
    
    public String getFailureMessage() { return failureMessage; }
    
    public abstract String getCommand();
    
    public Map<String, String> getParams() { return params; }
    
    public abstract void parseObjects( JSONObject json ) throws JSONException;
}
