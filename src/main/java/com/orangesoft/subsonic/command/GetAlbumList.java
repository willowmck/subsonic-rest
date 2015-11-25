/*
 * Coypright 2015 Orangesoft.
 */
package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Album;
import com.orangesoft.subsonic.system.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class GetAlbumList extends Command
{
    private final static String GET_ALBUM_LIST = "getAlbumList.view";
    private final static String ALBUM_LIST = "albumList";
    private final static String ALBUM = "album";
    private List<Album> albums;

    public GetAlbumList(Connection connection, Map<String, String> params)
    {
        this.connection = connection;
        this.params = params;
    }
    
    @Override
    public String getCommand() 
    {
        return GET_ALBUM_LIST;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException 
    {
        albums = new ArrayList<>();
        JSONObject albumList = json.getJSONObject(ALBUM_LIST);
        JSONArray albumArray = albumList.getJSONArray(ALBUM);
        for (int i=0; i < albumArray.length(); i++)
        {
            JSONObject albumObject = albumArray.getJSONObject(i);
            Album album = new Album(albumObject);
            albums.add(album);
        }
    }
    
    public List<Album> getList()
    {
        return albums;
    }
    
}
