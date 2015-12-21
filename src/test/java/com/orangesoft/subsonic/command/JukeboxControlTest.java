package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.JukeboxPlaylist;
import com.orangesoft.subsonic.system.Connection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2015 Orangesoft
 */
public class JukeboxControlTest
{
    Connection connection;
    Map<String, String> params = new HashMap<>();

    @Before
    public void setup()
    {
        connection = new FakeConnection("authorizedUser");
        params.put("action", "get");
    }

    @Test
    public void doTheGetAction()
    {
        JukeboxControl jukeboxControl = new JukeboxControl(connection, params);
        jukeboxControl.execute();
        assert(jukeboxControl.getStatus());
        JukeboxPlaylist jukeboxPlaylist = jukeboxControl.getJukeboxPlaylist();
        assertEquals(-1, jukeboxPlaylist.getCurrentIndex());
        assertEquals(false, jukeboxPlaylist.isPlaying());
        assertEquals(0.75, jukeboxPlaylist.getGain(), 0.1);
        assertEquals(0, jukeboxPlaylist.getPosition());
    }

    @Test
    public void doTheGetUnauthorizedUser()
    {
        connection = new FakeConnection("unauthorizedUser");
        JukeboxControl jukeboxControl = new JukeboxControl(connection, params);
        jukeboxControl.execute();
        assert(!jukeboxControl.getStatus());
        assertEquals("unauthorizedUser is not authorized to use jukebox.", jukeboxControl.getFailureMessage());
    }

    @Test
    public void iForgotTheParams()
    {
        params.clear();
        JukeboxControl jukeboxControl = new JukeboxControl(connection, params);
        jukeboxControl.execute();
        assert(!jukeboxControl.getStatus());
        assertEquals("Required string parameter 'action' is not present", jukeboxControl.getFailureMessage());
    }

    private class FakeConnection implements Connection
    {
        private String user;

        final static String getAction = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"ok\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"jukeboxPlaylist\" : {\n" +
                "         \"currentIndex\" : -1,\n" +
                "         \"playing\" : false,\n" +
                "         \"gain\" : 0.75,\n" +
                "         \"position\" : 0\n" +
                "      }\n" +
                "   }\n" +
                "}";

        final static String unauthorized = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"failed\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"error\" : {\n" +
                "         \"code\" : 50,\n" +
                "         \"message\" : \"unauthorizedUser is not authorized to use jukebox.\"\n" +
                "      }\n" +
                "   }\n" +
                "}";

        final static String noParams = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"failed\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"error\" : {\n" +
                "         \"code\" : 10,\n" +
                "         \"message\" : \"Required string parameter 'action' is not present\"\n" +
                "      }\n" +
                "   }\n" +
                "}";

        public FakeConnection(String user)
        {
            this.user = user;
        }

        @Override
        public void doCommand(Command command) throws IOException, JSONException
        {
            JSONObject json;
            Map<String, String> params = command.getParams();
            if (user.equals("unauthorizedUser"))
                json = new JSONObject(unauthorized);
            else if (params.isEmpty())
                json = new JSONObject(noParams);
            else
                json = new JSONObject( getAction );
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);

        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {

        }
    }
}
