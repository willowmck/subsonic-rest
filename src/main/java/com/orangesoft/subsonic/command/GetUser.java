package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.User;
import com.orangesoft.subsonic.system.Connection;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.util.Map;

/**
 * Copyright 2015 Orangesoft
 */
public class GetUser extends Command
{
    final static String COMMAND = "getUser.view";
    final static String USER = "user";

    private User user;

    public GetUser(Connection connection, Map<String, String> params)
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
        JSONObject jsonUser = json.getJSONObject(USER);
        user = new User(jsonUser);
    }

    public User getUser()
    {
        return user;
    }
}

