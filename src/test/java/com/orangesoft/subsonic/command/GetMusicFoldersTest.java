/*
 * Copyright 2015 Orangesoft
 */
package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

class FakeGetMusicFoldersConnection implements Connection
{
    final static String musicFolders = "{\n" +
"   \"subsonic-response\" : {\n" +
"      \"status\" : \"ok\",\n" +
"      \"version\" : \"1.12.0\",\n" +
"      \"musicFolders\" : {\n" +
"         \"musicFolder\" : [ {\n" +
"            \"id\" : 0,\n" +
"            \"name\" : \"Music\"\n" +
"         }, {\n" +
"            \"id\" : 1,\n" +
"            \"name\" : \"Video\"\n" +
"         } ]\n" +
"      }\n" +
"   }\n" +
"}";
    
    @Override
    public void doCommand(Command command) throws IOException, JSONException 
    {   
        JSONObject json = new JSONObject( musicFolders );
        JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
        command.parse(subsonicResponse);
    }

    @Override
    public void doStreamCommand(StreamCommand command) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
public class GetMusicFoldersTest 
{
    
    @Test
    public void testSuccess()
    {
        Connection connection = new FakeGetMusicFoldersConnection();
        GetMusicFolders getMusicFolders = new GetMusicFolders(connection);
        getMusicFolders.execute();
        assert(getMusicFolders.getStatus());
        assertEquals(getMusicFolders.getList().get(0).getId(), 0);
        assertEquals(getMusicFolders.getList().get(1).getName(), "Video");
        assertEquals(getMusicFolders.getList().size(), 2);
    }
}
