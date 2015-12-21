package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.License;
import com.orangesoft.subsonic.system.Connection;
import org.junit.Test;
import static org.junit.Assert.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.io.IOException;

/**
 * Copyright 2015 Orangesoft
 */
public class GetLicenseTest
{
    @Test
    public void getLicense()
    {
        Connection connection = new FakeConnection();
        GetLicense getLicense = new GetLicense(connection);
        getLicense.execute();
        assert(getLicense.getStatus());
        License license = getLicense.getLicense();
        assertEquals(true, license.isValid());
        assertEquals("me@here.com", license.getEmail());
        assertEquals("2013-08-30T15:28:13.367Z", license.getTrialExpires());
    }

    private class FakeConnection implements Connection
    {
        final static String response = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"ok\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"license\" : {\n" +
                "         \"valid\" : true,\n" +
                "         \"email\" : \"me@here.com\",\n" +
                "         \"trialExpires\" : \"2013-08-30T15:28:13.367Z\"\n" +
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
