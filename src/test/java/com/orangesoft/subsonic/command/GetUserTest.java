package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.User;
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
public class GetUserTest
{
    private Connection connection;
    private Map<String, String> params;
    private GetUser getUser;

    @Before
    public void setup()
    {
        connection = new FakeConnection("user1");
        params = new HashMap<>();
        getUser = new GetUser(connection, params);
    }

    @Test
    public void getUserForgotParams()
    {
        getUser.execute();
        assert(!getUser.getStatus());
        assertEquals("Required string parameter 'username' is not present", getUser.getFailureMessage());
    }

    @Test
    public void cannotSpyOnOtherUsers()
    {
        params.put("username", "user2");
        getUser.execute();
        assert(!getUser.getStatus());
        assertEquals("user1 is not authorized to get details for other users.", getUser.getFailureMessage());
    }

    @Test
    public void validUser()
    {
        params.put("username", "user1");
        getUser.execute();
        assert(getUser.getStatus());
        User user = getUser.getUser();
        assertEquals("user1", user.getUsername());
        assertEquals("me@here.com", user.getEmail());
        assertEquals(false, user.isScrobblingEnabled());
        assertEquals(false, user.isAdminRole());
        assertEquals(true, user.isSettingsRole());
        assertEquals(true, user.isDownloadRole());
        assertEquals(false, user.isUploadRole());
        assertEquals(true, user.isPlaylistRole());
        assertEquals(false, user.isCoverArtRole());
        assertEquals(false, user.isCommentRole());
        assertEquals(true, user.isPodcastRole());
        assertEquals(true, user.isStreamRole());
        assertEquals(true, user.isJukeboxRole());
        assertEquals(false, user.isShareRole());
        List<Integer> folders = user.getFolders();
        assert(0 == folders.get(0));
        assert(1 == folders.get(1));
    }

    private class FakeConnection implements Connection
    {
        private String user;

        final static String failedResponse = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"failed\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"error\" : {\n" +
                "         \"code\" : 10,\n" +
                "         \"message\" : \"Required string parameter 'username' is not present\"\n" +
                "      }\n" +
                "   }\n" +
                "}";

        final static String noSpying = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"failed\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"error\" : {\n" +
                "         \"code\" : 50,\n" +
                "         \"message\" : \"user1 is not authorized to get details for other users.\"\n" +
                "      }\n" +
                "   }\n" +
                "}";

        final static String response = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"ok\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"user\" : {\n" +
                "         \"username\" : \"user1\",\n" +
                "         \"email\" : \"me@here.com\",\n" +
                "         \"scrobblingEnabled\" : false,\n" +
                "         \"adminRole\" : false,\n" +
                "         \"settingsRole\" : true,\n" +
                "         \"downloadRole\" : true,\n" +
                "         \"uploadRole\" : false,\n" +
                "         \"playlistRole\" : true,\n" +
                "         \"coverArtRole\" : false,\n" +
                "         \"commentRole\" : false,\n" +
                "         \"podcastRole\" : true,\n" +
                "         \"streamRole\" : true,\n" +
                "         \"jukeboxRole\" : true,\n" +
                "         \"shareRole\" : false,\n" +
                "         \"folder\" : [ 0, 1 ]\n" +
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
            Map<String, String> params = command.getParams();
            JSONObject json;

            if (params.isEmpty())
                json = new JSONObject(failedResponse);
            else if (params.get("username").equals("user2"))
                json = new JSONObject(noSpying);
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
