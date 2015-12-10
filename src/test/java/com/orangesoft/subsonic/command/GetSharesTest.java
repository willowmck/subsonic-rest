package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import com.orangesoft.subsonic.system.PingTest;
import com.orangesoft.subsonic.system.RestConnection;
import java.io.IOException;
import org.junit.Test;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class GetSharesTest 
{
    @Test
    public void getEmptySharesList()
    {
        Connection connection = new FakeConnection();
        GetShares getShares = new GetShares(connection);
        getShares.execute();
        assert(getShares.getStatus());
        assert(getShares.getShares().isEmpty());
    }
    
    private final class FakeConnection implements Connection
    {
        final static String response = "{\n" +
            "   \"subsonic-response\" : {\n" +
            "      \"status\" : \"ok\",\n" +
            "      \"version\" : \"1.13.0\",\n" +
            "      \"shares\" : {\n" +
            "      }\n" +
            "   }\n" +
            "}";
        
        @Override
        public void doCommand(Command command) throws IOException, JSONException 
        {
            JSONObject json = new JSONObject( response );
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);   
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
