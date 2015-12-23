package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Album;
import com.orangesoft.subsonic.system.Connection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2015 Orangesoft
 */
public class GetAlbumList2Test
{
    Connection connection;
    Map<String, String> params;
    GetAlbumList2 getAlbumList2;

    @Before
    public void setup()
    {
        connection = new FakeConnection();
        params = new HashMap<>();
        getAlbumList2 = new GetAlbumList2(connection, params);
    }

    @Test
    public void missingRequiredParameters()
    {
        getAlbumList2.execute();
        assert(!getAlbumList2.getStatus());
        assertEquals("Required string parameter 'type' is not present", getAlbumList2.getFailureMessage());
    }

    @Test
    public void getAlbumList2()
    {
        params.put("type", "random");
        getAlbumList2.execute();
        assert(getAlbumList2.getStatus());
        List<Album> albums = getAlbumList2.getList();
        assert(!albums.isEmpty());
        Album album = albums.get(0);
        assertEquals(1070, album.getId());
        album = albums.get(1);
        assertEquals("modern", album.getName());
        album = albums.get(2);
        assertEquals("The Woolies", album.getArtist());
        album = albums.get(3);
        assertEquals(1356, album.getArtistId());
        album = albums.get(4);
        assertEquals("al-342", album.getCoverArt());
        album = albums.get(5);
        assertEquals(18, album.getSongCount());
        album = albums.get(6);
        assertEquals(518, album.getDuration());
        album = albums.get(7);
        assertEquals("2007-11-03T01:47:38.000Z", album.getCreated());
        album = albums.get(8);
        assertEquals(1989, album.getYear());
        album = albums.get(9);
        assertEquals("misc", album.getGenre());
    }

    private class FakeConnection implements Connection
    {
        final static String missingParameters = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"failed\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"error\" : {\n" +
                "         \"code\" : 10,\n" +
                "         \"message\" : \"Required string parameter 'type' is not present\"\n" +
                "      }\n" +
                "   }\n" +
                "}";

        final static String response = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"ok\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"albumList2\" : {\n" +
                "         \"album\" : [ {\n" +
                "            \"id\" : \"1070\",\n" +
                "            \"name\" : \"Roy Rogers & Dale Evans\",\n" +
                "            \"artist\" : \"Roy Rogers & Dale Evans\",\n" +
                "            \"artistId\" : \"672\",\n" +
                "            \"coverArt\" : \"al-1070\",\n" +
                "            \"songCount\" : 1,\n" +
                "            \"duration\" : 175,\n" +
                "            \"created\" : \"2012-12-24T01:05:24.000Z\",\n" +
                "            \"year\" : 1999,\n" +
                "            \"genre\" : \"2\"\n" +
                "         }, {\n" +
                "            \"id\" : \"27\",\n" +
                "            \"name\" : \"modern\",\n" +
                "            \"artist\" : \"The Pointer Sisters\",\n" +
                "            \"artistId\" : \"24\",\n" +
                "            \"songCount\" : 12,\n" +
                "            \"duration\" : 2742,\n" +
                "            \"created\" : \"2012-12-22T21:59:10.000Z\",\n" +
                "            \"year\" : 1998,\n" +
                "            \"genre\" : \"Other\"\n" +
                "         }, {\n" +
                "            \"id\" : \"1347\",\n" +
                "            \"name\" : \"Nuggets: Original Artyfacts from the First Psychedelic Era 1965-1968 [Box] (2 of 4)\",\n" +
                "            \"artist\" : \"The Woolies\",\n" +
                "            \"artistId\" : \"849\",\n" +
                "            \"coverArt\" : \"al-1347\",\n" +
                "            \"songCount\" : 1,\n" +
                "            \"duration\" : 124,\n" +
                "            \"created\" : \"2007-11-03T02:03:35.000Z\",\n" +
                "            \"genre\" : \"Rock/Pop\"\n" +
                "         }, {\n" +
                "            \"id\" : \"2102\",\n" +
                "            \"name\" : \"Made In America\",\n" +
                "            \"artist\" : \"Yo-Yo Ma\",\n" +
                "            \"artistId\" : \"1356\",\n" +
                "            \"songCount\" : 1,\n" +
                "            \"duration\" : 856,\n" +
                "            \"created\" : \"2012-12-28T14:48:42.000Z\",\n" +
                "            \"year\" : 1993,\n" +
                "            \"genre\" : \"32\"\n" +
                "         }, {\n" +
                "            \"id\" : \"342\",\n" +
                "            \"name\" : \"Kiss Me, Kiss Me, Kiss Me\",\n" +
                "            \"artist\" : \"The Cure\",\n" +
                "            \"artistId\" : \"244\",\n" +
                "            \"coverArt\" : \"al-342\",\n" +
                "            \"songCount\" : 17,\n" +
                "            \"duration\" : 4321,\n" +
                "            \"created\" : \"2012-12-23T15:48:00.000Z\",\n" +
                "            \"year\" : 1987,\n" +
                "            \"genre\" : \"50\"\n" +
                "         }, {\n" +
                "            \"id\" : \"336\",\n" +
                "            \"name\" : \"Compilation\",\n" +
                "            \"artist\" : \"The Clean\",\n" +
                "            \"artistId\" : \"239\",\n" +
                "            \"songCount\" : 18,\n" +
                "            \"duration\" : 3462,\n" +
                "            \"created\" : \"2012-12-23T15:42:02.000Z\",\n" +
                "            \"genre\" : \"17\"\n" +
                "         }, {\n" +
                "            \"id\" : \"295\",\n" +
                "            \"name\" : \"Christian Death\",\n" +
                "            \"artist\" : \"Christian Death\",\n" +
                "            \"artistId\" : \"206\",\n" +
                "            \"songCount\" : 3,\n" +
                "            \"duration\" : 518,\n" +
                "            \"created\" : \"2012-12-23T16:13:17.000Z\",\n" +
                "            \"genre\" : \"Blues\"\n" +
                "         }, {\n" +
                "            \"id\" : \"1382\",\n" +
                "            \"name\" : \"Northern Exposure, Vol. 2: East Coast Edition\",\n" +
                "            \"artist\" : \"Sasha + John Digweed\",\n" +
                "            \"artistId\" : \"867\",\n" +
                "            \"coverArt\" : \"al-1382\",\n" +
                "            \"songCount\" : 1,\n" +
                "            \"duration\" : 343,\n" +
                "            \"created\" : \"2007-11-03T01:47:38.000Z\",\n" +
                "            \"year\" : 1998,\n" +
                "            \"genre\" : \"Electronica\"\n" +
                "         }, {\n" +
                "            \"id\" : \"681\",\n" +
                "            \"name\" : \"It's Only Right and Natural\",\n" +
                "            \"artist\" : \"The Frogs\",\n" +
                "            \"artistId\" : \"459\",\n" +
                "            \"songCount\" : 14,\n" +
                "            \"duration\" : 1887,\n" +
                "            \"created\" : \"2012-12-24T12:57:10.000Z\",\n" +
                "            \"year\" : 1989,\n" +
                "            \"genre\" : \"Indie\"\n" +
                "         }, {\n" +
                "            \"id\" : \"16\",\n" +
                "            \"name\" : \"Time-Life Treasury of Christmas\",\n" +
                "            \"artist\" : \"Choir of King's College, Cambridge\",\n" +
                "            \"artistId\" : \"16\",\n" +
                "            \"songCount\" : 1,\n" +
                "            \"duration\" : 185,\n" +
                "            \"created\" : \"2012-12-22T21:56:06.000Z\",\n" +
                "            \"genre\" : \"misc\"\n" +
                "         } ]\n" +
                "      }\n" +
                "   }\n" +
                "}";

        @Override
        public void doCommand(Command command) throws IOException, JSONException
        {
            Map<String, String> params = command.getParams();
            JSONObject json;
            if (params.isEmpty())
                json = new JSONObject(missingParameters);
            else
                json = new JSONObject(response);
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {

        }
    }
}
