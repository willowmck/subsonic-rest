package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import org.junit.Test;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.io.IOException;

/**
 * Copyright 2015 Orangesoft
 */
public class GetBookmarksTest
{
    @Test
    public void getBookmarks()
    {
        Connection connection = new FakeConnection();
        GetBookmarks getBookmarks = new GetBookmarks(connection);
        getBookmarks.execute();
        assert(getBookmarks.getStatus());
    }

    private class FakeConnection implements Connection
    {
        final static String response = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"ok\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"bookmarks\" : {\n" +
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
