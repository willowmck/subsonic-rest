package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class StarTest 
{
    private Connection connection;
    private Star star;
    private Map<String, String> params;
    
    @Before
    public void setup()
    {
        params = new HashMap<>();
        connection = new FakeConnection();
        star = new Star(connection, params);
    }
    
    @Test
    public void testStarNothing()
    {
        star.execute();
        assert(star.getStatus());
    }
    
    @Test
    public void testStarSomeId()
    {
        params.put("id", "101");
        star.execute();
        assert(star.getStatus());
    }
    
    @Test
    public void testStarBadId()
    {
        params.put("id", "-1");
        star.execute();
        assert(!star.getStatus());
    }
    
    private class FakeConnection implements Connection
    {
        private final static String okResponse = "{\n" +
        "   \"subsonic-response\" : {\n" +
        "      \"status\" : \"ok\",\n" +
        "      \"version\" : \"1.13.0\"\n" +
        "   }\n" +
        "}";
        private final static String badResponse = "{\n" +
        "   \"subsonic-response\" : {\n" +
        "      \"status\" : \"failed\",\n" +
        "      \"version\" : \"1.13.0\",\n" +
        "      \"error\" : {\n" +
        "         \"code\" : 70,\n" +
        "         \"message\" : \"Media file not found: -1\"\n" +
        "      }\n" +
        "   }\n" +
        "}";

        @Override
        public void doCommand(Command command) throws IOException, JSONException 
        {
            JSONObject json;
            
            if (!command.getParams().isEmpty() &&
                    command.getParams().get("id").equals("-1"))
                json = new JSONObject(badResponse);
            else
                json = new JSONObject( okResponse );
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);    
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
