package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Song;
import com.orangesoft.subsonic.system.Connection;
import com.orangesoft.subsonic.system.PingTest;
import com.orangesoft.subsonic.system.RestConnection;
import org.junit.Test;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2016 Orangesoft
 */
public class GetSongTest
{
    @Test
    public void getAKinksSong() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("id", "801");
        Connection connection = new FakeConnection();
        GetSong getSong = new GetSong(connection, params);
        getSong.execute();
        assert(getSong.getStatus());
        Song imSoTired = getSong.getSong();
        assertNotNull(imSoTired);
        assertEquals("Tired of Waiting for You", imSoTired.getTitle());
    }

    private final class FakeConnection implements Connection
    {
        final static String IM_SO_TIRED = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"ok\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"song\" : {\n" +
                "         \"id\" : \"801\",\n" +
                "         \"parent\" : \"795\",\n" +
                "         \"isDir\" : false,\n" +
                "         \"title\" : \"Tired of Waiting for You\",\n" +
                "         \"album\" : \"Greatest Hits\",\n" +
                "         \"artist\" : \"The Kinks\",\n" +
                "         \"track\" : 9,\n" +
                "         \"year\" : 1989,\n" +
                "         \"size\" : 3694701,\n" +
                "         \"contentType\" : \"audio/mpeg\",\n" +
                "         \"suffix\" : \"mp3\",\n" +
                "         \"duration\" : 154,\n" +
                "         \"bitRate\" : 192,\n" +
                "         \"path\" : \"Artists/K/The Kinks/Greatest Hits/9 - Tired of Waiting for You.mp3\",\n" +
                "         \"isVideo\" : false,\n" +
                "         \"created\" : \"2012-12-26T13:06:15.000Z\",\n" +
                "         \"albumId\" : \"123\",\n" +
                "         \"artistId\" : \"87\",\n" +
                "         \"type\" : \"music\"\n" +
                "      }\n" +
                "   }\n" +
                "}";

        @Override
        public void doCommand(Command command) throws IOException, JSONException
        {
            JSONObject json = new JSONObject(IM_SO_TIRED);
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {

        }
    }
}
