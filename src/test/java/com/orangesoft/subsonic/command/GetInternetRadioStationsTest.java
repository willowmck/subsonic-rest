package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.InternetRadioStation;
import com.orangesoft.subsonic.system.Connection;
import org.junit.Test;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Copyright 2015 Orangesoft
 */
public class GetInternetRadioStationsTest
{
    @Test
    public void getAllStations()
    {
        Connection connection = new FakeConnection();
        GetInternetRadioStations getInternetRadioStations = new GetInternetRadioStations(connection);
        getInternetRadioStations.execute();
        assert(getInternetRadioStations.getStatus());
        List<InternetRadioStation> internetRadioStations = getInternetRadioStations.getList();
        InternetRadioStation station = internetRadioStations.get(0);
        assertEquals(1, station.getId());
        assertEquals("Christmas Standards", station.getName());
        assertEquals("https://www.internet-radio.com/servers/tools/playlistgenerator/?u=http://108.61.73.117:8118/listen.pls&t=.m3u",
                station.getStreamUrl());
        assertEquals("http://www.181.fm/", station.getHomePageUrl());
    }

    private class FakeConnection implements Connection
    {
        final static String response = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"ok\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"internetRadioStations\" : {\n" +
                "         \"internetRadioStation\" : [ {\n" +
                "            \"id\" : \"1\",\n" +
                "            \"name\" : \"Christmas Standards\",\n" +
                "            \"streamUrl\" : \"https://www.internet-radio.com/servers/tools/playlistgenerator/?u=http://108.61.73.117:8118/listen.pls&t=.m3u\",\n" +
                "            \"homePageUrl\" : \"http://www.181.fm/\"\n" +
                "         } ]\n" +
                "      }\n" +
                "   }\n" +
                "}";

        @Override
        public void doCommand(Command command) throws IOException, JSONException
        {
            JSONObject json = new JSONObject(response);
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {

        }
    }
}
