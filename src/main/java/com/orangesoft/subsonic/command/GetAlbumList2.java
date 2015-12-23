package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Album;
import com.orangesoft.subsonic.system.Connection;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Copyright 2015 Orangesoft
 */
public class GetAlbumList2 extends GetAlbumList
{
    private final static String COMMAND = "getAlbumList2.view";

    final static String ALBUM_LIST2 = "albumList2";

    public GetAlbumList2(Connection connection, Map<String, String> params)
    {
        super(connection, params);
    }

    @Override
    public String getCommand()
    {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException
    {
        albums = new ArrayList<>();
        JSONObject albumList = json.getJSONObject(ALBUM_LIST2);
        parseAlbums(albumList);
    }
}
