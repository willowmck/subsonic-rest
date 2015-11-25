/*
 * Copyright 2015 Orangesoft
 */
package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Playlist;
import com.orangesoft.subsonic.system.Connection;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class GetPlaylistsTest 
{
    @Test
    public void getPlaylists()
    {
        Connection connection = new FakeConnection();
        GetPlaylists getPlaylists = new GetPlaylists(connection);
        getPlaylists.execute();
        assert(getPlaylists.getStatus());
        List<Playlist> playlists = getPlaylists.getList();
        assertEquals(4, playlists.size());
        assertEquals(278, playlists.get(0).getId());
        assertEquals("Alternative Dance", playlists.get(1).getName());
        assertEquals("Auto-imported from Blues.m3u", playlists.get(2).getComment());
        assertEquals("admin", playlists.get(3).getOwner());
        assert(playlists.get(0).isPublic());
        assertEquals(102, playlists.get(1).getSongCount());
        assertEquals(10054, playlists.get(2).getDuration());
        assertEquals("2015-11-03T07:00:14.687Z", playlists.get(3).getCreated());
        assertEquals("2015-11-02T07:00:12.444Z", playlists.get(0).getChanged());
        assertEquals("pl-281", playlists.get(1).getCoverArt());
    }
    
    private class FakeConnection implements Connection
    {
        final static String playlists = "{\n" +
"   \"subsonic-response\" : {\n" +
"      \"status\" : \"ok\",\n" +
"      \"version\" : \"1.13.0\",\n" +
"      \"playlists\" : {\n" +
"         \"playlist\" : [ {\n" +
"            \"id\" : \"278\",\n" +
"            \"name\" : \"Acerbic\",\n" +
"            \"comment\" : \"Auto-imported from Acerbic.m3u\",\n" +
"            \"owner\" : \"admin\",\n" +
"            \"public\" : true,\n" +
"            \"songCount\" : 340,\n" +
"            \"duration\" : 71451,\n" +
"            \"created\" : \"2015-11-02T07:00:12.432Z\",\n" +
"            \"changed\" : \"2015-11-02T07:00:12.444Z\",\n" +
"            \"coverArt\" : \"pl-278\"\n" +
"         }, {\n" +
"            \"id\" : \"281\",\n" +
"            \"name\" : \"Alternative Dance\",\n" +
"            \"comment\" : \"Auto-imported from Alternative Dance.m3u\",\n" +
"            \"owner\" : \"admin\",\n" +
"            \"public\" : true,\n" +
"            \"songCount\" : 102,\n" +
"            \"duration\" : 26146,\n" +
"            \"created\" : \"2015-11-10T07:00:17.164Z\",\n" +
"            \"changed\" : \"2015-11-10T07:00:17.168Z\",\n" +
"            \"coverArt\" : \"pl-281\"\n" +
"         }, {\n" +
"            \"id\" : \"280\",\n" +
"            \"name\" : \"Blues\",\n" +
"            \"comment\" : \"Auto-imported from Blues.m3u\",\n" +
"            \"owner\" : \"admin\",\n" +
"            \"public\" : true,\n" +
"            \"songCount\" : 39,\n" +
"            \"duration\" : 10054,\n" +
"            \"created\" : \"2015-11-04T07:00:14.776Z\",\n" +
"            \"changed\" : \"2015-11-04T07:00:14.778Z\",\n" +
"            \"coverArt\" : \"pl-280\"\n" +
"         }, {\n" +
"            \"id\" : \"279\",\n" +
"            \"name\" : \"Celebration\",\n" +
"            \"comment\" : \"Auto-imported from Celebration.m3u\",\n" +
"            \"owner\" : \"admin\",\n" +
"            \"public\" : true,\n" +
"            \"songCount\" : 84,\n" +
"            \"duration\" : 18105,\n" +
"            \"created\" : \"2015-11-03T07:00:14.687Z\",\n" +
"            \"changed\" : \"2015-11-03T07:00:14.692Z\",\n" +
"            \"coverArt\" : \"pl-279\"\n" +
"         } ]\n" +
"      }\n" +
"   }\n" +
"}";

        @Override
        public void doCommand(Command command) throws IOException, JSONException
        {

            JSONObject json = new JSONObject( playlists );
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
