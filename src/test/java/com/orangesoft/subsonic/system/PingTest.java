package com.orangesoft.subsonic.system;

import org.junit.Test;
import org.apache.commons.codec.binary.Base64;
import us.monoid.json.JSONObject;
import com.orangesoft.subsonic.command.Command;
import com.orangesoft.subsonic.command.StreamCommand;
import java.io.IOException;
import us.monoid.json.JSONException;
import static org.junit.Assert.*;

class FakePingConnection implements Connection
{
    static final String pingSuccess = "{\n" +
            "    \"subsonic-response\" : {\n" +
            "      \"status\" : \"ok\",\n" +
            "      \"version\" : \"1.11.0\"\n"+
            "    }\n" +
            "}";
    
    String host;
    String encodedAuth;
    
    FakePingConnection( String host, String encodedAuth )
    {
        this.host = host;
        this.encodedAuth = encodedAuth;
    }
    
    public void doCommand(Command command) throws IOException, JSONException
    {
        JSONObject json;
        
        if (host.equals("test.badhost.org"))
            throw new IOException("bad host");
        if (encodedAuth.equals("ZDAwZDpibGFo"))
            throw new IOException("401");
        
        json = new JSONObject( pingSuccess );
        JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
        command.parse(subsonicResponse);
    }

    @Override
    public void doStreamCommand(StreamCommand command) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/**
 * Tests the Ping command for the Subsonic API.  API level supported is 1.12.
 * Copyright 2015 Orangesoft
 */
public class PingTest
{
    public static String getBase64Auth(String user, String password)
    {
        try
        {
            byte[] data = (user + ":" + password).getBytes("UTF-8");
            return new String(Base64.encodeBase64(data));
        }
        catch (Exception e)
        {
            
        }
        return "";
    }
    
    @Test
    public void testPingSuccess()
    {
        String host = "willowmck.subsonic.org";
        String user = "test1";
        String password = "test1";

        Connection connection = new FakePingConnection(host, getBase64Auth(user, password));

        Ping ping = new Ping(connection);
        ping.execute();
        assert(ping.getStatus());
    }
    
    @Test
    public void testBadHost()
    {
        String host = "test.badhost.org";
        String user = "test1";
        String password = "test1";
        
        Connection connection = new FakePingConnection(host, getBase64Auth(user, password));
        
        Ping ping = new Ping(connection);
        ping.execute();
        assert(!ping.getStatus());
        assertEquals("Unable to connect to host", ping.getFailureMessage() );
    }
    
    @Test
    public void testBadAuth()
    {
        String host = "willowmck.subsonic.org";
        String user = "d00d";
        String password = "blah";
        
        Connection connection = new FakePingConnection(host, getBase64Auth(user, password));
        
        Ping ping = new Ping(connection);
        ping.execute();
        assert(!ping.getStatus());
        assertEquals("Wrong username or password.", ping.getFailureMessage() );
    }
}
