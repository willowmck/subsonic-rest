/*
 * Coypright 2015 Orangesoft
 */
package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Album;
import com.orangesoft.subsonic.system.Connection;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;


public class GetAlbumListTest 
{
    Connection connection;
    GetAlbumList getAlbumList;
    Map<String, String> params;
    
    @Before
    public void setup() throws Exception
    {
        connection = new FakeConnection();
        params = new HashMap<>();
        getAlbumList = new GetAlbumList(connection, params);
    }
 
    @Test
    public void missingRequiredParameters()
    {
        getAlbumList.execute();
        assert(!getAlbumList.getStatus());
        assertEquals("Required string parameter 'type' is not present", 
                getAlbumList.getFailureMessage());
    }
    
    @Test
    public void getRandomAlbumList()
    {
        params.put("type", "random");
        getAlbumList.execute();
        assert(getAlbumList.getStatus());
        assertEquals(10, getAlbumList.getList().size());
        assertEquals("11551", getAlbumList.getList().get(0).getId());
        assertEquals(14177, getAlbumList.getList().get(1).getParent());
        assertEquals(true, getAlbumList.getList().get(2).isDir());
        assertEquals("Wish You Were Here", getAlbumList.getList().get(3).getTitle());
        assertEquals("The Dream Of The Blue Turtles", getAlbumList.getList().get(4).getAlbumName());
        assertEquals("Bicycle Face", getAlbumList.getList().get(5).getArtist());
        assertEquals(Album.UNDEFINED_INT, getAlbumList.getList().get(6).getYear());
        assertEquals("17", getAlbumList.getList().get(7).getGenre());
        assertEquals("", getAlbumList.getList().get(8).getCoverArt());
        assertEquals("2013-07-31T12:38:02.000Z", getAlbumList.getList().get(9).getCreated());
        assertEquals("9650", getAlbumList.getList().get(9).getCoverArt());
        assertEquals("", getAlbumList.getList().get(6).getGenre());
        assertEquals(1965, getAlbumList.getList().get(9).getYear());
    }
    
    
    private class FakeConnection implements Connection
    {
        final static String randomAlbums = "{\n" +
    "   \"subsonic-response\" : {\n" +
    "      \"status\" : \"ok\",\n" +
    "      \"version\" : \"1.12.0\",\n" +
    "      \"albumList\" : {\n" +
    "         \"album\" : [ {\n" +
    "            \"id\" : \"11551\",\n" +
    "            \"parent\" : \"11014\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Live At The Lighthouse (Disc 2 of 3)\",\n" +
    "            \"album\" : \"Live At The Lighthouse (Disc 2 of 3)\",\n" +
    "            \"artist\" : \"Lee Morgan\",\n" +
    "            \"year\" : 1971,\n" +
    "            \"genre\" : \"Jazz\",\n" +
    "            \"created\" : \"2012-12-27T02:20:10.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"14312\",\n" +
    "            \"parent\" : \"14177\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Nuggets Original Artyfacts from the First Psychedelic Era 1965-1968  (Vol 3)\",\n" +
    "            \"album\" : \"Nuggets: Original Artyfacts from the First Psychedelic Era 1965-1968  (Vol 3)\",\n" +
    "            \"artist\" : \"Cybelle / Lyme & Cybelle\",\n" +
    "            \"genre\" : \"Rock/Pop\",\n" +
    "            \"coverArt\" : \"14312\",\n" +
    "            \"created\" : \"2013-07-31T12:38:13.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"7731\",\n" +
    "            \"parent\" : \"7266\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Nuggets Original Artyfacts from the First Psychedelic Era 1965-1968 [Box] (4 of 4)\",\n" +
    "            \"album\" : \"Nuggets: Original Artyfacts from the First Psychedelic Era 1965-1968 [Box] (4 of 4)\",\n" +
    "            \"artist\" : \"The Sonics\",\n" +
    "            \"genre\" : \"Rock/Pop\",\n" +
    "            \"coverArt\" : \"7731\",\n" +
    "            \"created\" : \"2013-07-31T12:40:00.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"14043\",\n" +
    "            \"parent\" : \"13480\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Wish You Were Here\",\n" +
    "            \"album\" : \"Wish You Were Here\",\n" +
    "            \"artist\" : \"Pink Floyd\",\n" +
    "            \"year\" : 1979,\n" +
    "            \"genre\" : \"Unknown\",\n" +
    "            \"coverArt\" : \"14043\",\n" +
    "            \"created\" : \"2013-07-31T12:39:38.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"7934\",\n" +
    "            \"parent\" : \"7311\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"The Dream Of The Blue Turtles\",\n" +
    "            \"album\" : \"The Dream Of The Blue Turtles\",\n" +
    "            \"artist\" : \"Sting\",\n" +
    "            \"year\" : 1985,\n" +
    "            \"genre\" : \"Pop\",\n" +
    "            \"coverArt\" : \"7934\",\n" +
    "            \"created\" : \"2013-07-31T12:40:02.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"9370\",\n" +
    "            \"parent\" : \"9175\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Trust & Obey\",\n" +
    "            \"album\" : \"Trust & Obey\",\n" +
    "            \"artist\" : \"Bicycle Face\",\n" +
    "            \"genre\" : \"Rock/Pop\",\n" +
    "            \"coverArt\" : \"9370\",\n" +
    "            \"created\" : \"2013-07-31T12:37:36.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"15592\",\n" +
    "            \"parent\" : \"15581\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Alfred's Kid's Ukelele Course\",\n" +
    "            \"album\" : \"Alfred's Kid's Ukelele Course\",\n" +
    "            \"artist\" : \"Kid's Ukelele Course\",\n" +
    "            \"created\" : \"2014-03-06T01:06:30.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"12743\",\n" +
    "            \"parent\" : \"12358\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Nuggets II Original Artyfacts From The British Empire And Beyond 1964-69 (Disc 2)\",\n" +
    "            \"album\" : \"Nuggets II: Original Artyfacts From The British Empire And Beyond 1964-69 (Disc 2)\",\n" +
    "            \"artist\" : \"Rupert's People\",\n" +
    "            \"year\" : 2001,\n" +
    "            \"genre\" : \"17\",\n" +
    "            \"created\" : \"2012-12-28T14:36:15.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"14176\",\n" +
    "            \"parent\" : \"502\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Baz Luhrmann\",\n" +
    "            \"album\" : \"Something for Everybody\",\n" +
    "            \"artist\" : \"Doris Day\",\n" +
    "            \"year\" : 1999,\n" +
    "            \"genre\" : \"24\",\n" +
    "            \"created\" : \"2013-07-31T12:37:02.000Z\"\n" +
    "         }, {\n" +
    "            \"id\" : \"9650\",\n" +
    "            \"parent\" : \"9192\",\n" +
    "            \"isDir\" : true,\n" +
    "            \"title\" : \"Rubber Soul\",\n" +
    "            \"album\" : \"Rubber Soul\",\n" +
    "            \"artist\" : \"The Beatles\",\n" +
    "            \"year\" : 1965,\n" +
    "            \"genre\" : \"17\",\n" +
    "            \"coverArt\" : \"9650\",\n" +
    "            \"created\" : \"2013-07-31T12:38:02.000Z\"\n" +
    "         } ]\n" +
    "      }\n" +
    "   }\n" +
    "}";

        final static String missingType = "{\n" +
    "   \"subsonic-response\" : {\n" +
    "      \"status\" : \"failed\",\n" +
    "      \"version\" : \"1.12.0\",\n" +
    "      \"error\" : {\n" +
    "         \"code\" : 10,\n" +
    "         \"message\" : \"Required string parameter 'type' is not present\"\n" +
    "      }\n" +
    "   }\n" +
    "}";

        @Override
        public void doCommand(Command command) throws IOException, JSONException
        {
            JSONObject json;
            if (command.getParams().isEmpty())
                json = new JSONObject(missingType);
            else
                json = new JSONObject( randomAlbums );
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
