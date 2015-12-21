package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class GetBookmarks extends Command
{
    final static String COMMAND = "getBookmarks.view";

    public GetBookmarks(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public String getCommand()
    {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException {

    }
}
