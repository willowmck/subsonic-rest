package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Indexes;
import com.orangesoft.subsonic.system.Connection;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class GetIndexes extends Command
{
    final static String COMMAND = "getIndexes.view";
    final static String INDEXES = "indexes";

    private Indexes indexes;

    public GetIndexes(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException
    {
        JSONObject indexesObject = json.getJSONObject(INDEXES);
        indexes = new Indexes(indexesObject);
    }

    public Indexes getIndexes()
    {
        return indexes;
    }
}
