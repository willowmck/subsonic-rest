package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Share;
import com.orangesoft.subsonic.system.Connection;
import java.util.ArrayList;
import java.util.List;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 *
 * Copyright 2015 Orangesoft
 */
public class GetShares extends Command
{
    private final static String COMMAND = "getShares.view";
    private List<Share> shares;
    
    public GetShares(Connection connection)
    {
        this.connection = connection;
        shares = new ArrayList<>();
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
    
    public List<Share> getShares()
    {
        return shares;
    }
    
}
