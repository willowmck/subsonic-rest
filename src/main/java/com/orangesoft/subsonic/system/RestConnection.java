/*
 * Coypright 2015 Orangesoft
 */
package com.orangesoft.subsonic.system;

import com.orangesoft.subsonic.command.Command;
import com.orangesoft.subsonic.command.StreamCommand;
import com.orangesoft.subsonic.data.BinaryResourceContainer;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;

import java.io.IOException;
import java.util.Map;
import us.monoid.web.BinaryResource;

public class RestConnection implements Connection
{
    static final String HTTP = "http://";
    public static final String SUBSONIC_RESPONSE = "subsonic-response";
    static final String VERSION = "1.12.0";
    static final String CLIENT = "jook";
    static final String PROTOCOL = "/rest/";
    private final String host;
    private final String base64Auth;
    private final Resty resty;
    private boolean isInitialized = false;
    
    public RestConnection(String host, String encodedAuth)
    {
        this.host = host;
        this.base64Auth = encodedAuth;
        resty = new Resty();
    }
    
    private synchronized void initializeConnection()
    {
        if (!isInitialized)
        {
            try
            {
                resty.withHeader("Authorization", "Basic " + base64Auth);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            isInitialized = true;
        }
    }
    
    @Override
    public void doCommand(Command command) throws IOException, JSONException
    {
        initializeConnection();
        JSONObject parsedJson = resty.json(buildUri(command)).toObject();
        JSONObject subsonicResponse = parsedJson.getJSONObject(SUBSONIC_RESPONSE);
        command.parse(subsonicResponse);
    }
    
    @Override
    public void doStreamCommand(StreamCommand command) throws IOException, JSONException
    {
        initializeConnection();
        String uri = buildUri(command);
        BinaryResource resource = resty.bytes(uri);
        BinaryResourceContainer container = new BinaryResourceContainer(resource);
        command.setResource(container);
    }
    
    private String buildUri(Command command)
    {
        return HTTP + host + PROTOCOL + command.getCommand() + buildParams(command);
    }
    
    private String buildParams(Command command)
    {
        String params = "";
        params += "?";
        params += "v=" + VERSION;
        params += "&c=" + CLIENT;
        params += "&f=json";
        Map<String, String> extraParams = command.getParams();
        if (null != extraParams)
        {
            for (String key : extraParams.keySet())
            {
                params += "&" + key;
                String value = extraParams.get(key);
                if (null != value)
                    params += "=" + value;
            }
        }
        return params;
    }
    
}
