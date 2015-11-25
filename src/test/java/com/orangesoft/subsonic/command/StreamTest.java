package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.data.BinaryResourceContainer;
import com.orangesoft.subsonic.system.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import us.monoid.json.JSONException;
import us.monoid.web.BinaryResource;

/**
 *
 * Copyright 2015 Orangesoft.
 */
public class StreamTest
{
    Connection connection;
    Map<String, String> params;
    
    @Before
    public void setup() throws Exception
    {
        connection = new FakeConnection();
        params = new HashMap<>();
    }
    @Test
    public void nullStream() throws Exception
    {
        Stream stream = new Stream(connection, params);
        stream.execute();
        assert(!stream.getStatus());
        assertEquals("Null stream", stream.getFailureMessage());
    }
    
    @Test
    public void streamId8() throws Exception
    {
        params.put("id", "8");
        Stream stream = new Stream(connection, params);
        stream.execute();
        assert(stream.getStatus());
        assertNotNull(stream.getStream());
    }
    
    @Test
    public void streamNotFound() throws Exception
    {
        params.put("id", "0");
        Stream stream = new Stream(connection, params);
        stream.execute();
        assert(!stream.getStatus());
        assertEquals("NullPointerException", stream.getFailureMessage());
    }
    
    private class FakeConnection implements Connection
    {

        @Override
        public void doCommand(Command command) throws IOException, JSONException
        {
            command.parse(null);
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException
        {
            BinaryResource resource = new BinaryResource();
            FakeBinaryResourceContainer container = new FakeBinaryResourceContainer(resource, command.getParams());
            command.setResource(container);
        }
        
    }
    
    private class FakeBinaryResourceContainer extends BinaryResourceContainer
    {
        private final Map<String, String> params;
        private final String errorJson = "{\n" +
"   \"subsonic-response\" : {\n" +
"      \"status\" : \"failed\",\n" +
"      \"version\" : \"1.13.0\",\n" +
"      \"error\" : {\n" +
"         \"code\" : 0,\n" +
"         \"message\" : \"NullPointerException\"\n" +
"      }\n" +
"   }\n" +
"}";

        public FakeBinaryResourceContainer(BinaryResource resource, Map<String, String> params)
        {
            super(resource);
            this.params = params;
        }

        @Override
        public InputStream stream()
        {
            if (params.get("id") != null )
                if ( params.get("id").equals("8"))
                    return new StringBufferInputStream("hello");
                else
                    return new StringBufferInputStream(errorJson);
            return super.stream(); 
        }

        @Override
        public boolean isNullSource()
        {
            return params.isEmpty();
        }

        @Override
        public boolean isAudioContent()
        {
            return params.get("id") != null && params.get("id").equals("8");
        }
        
    }
}
