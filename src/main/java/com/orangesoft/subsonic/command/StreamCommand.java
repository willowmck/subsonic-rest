package com.orangesoft.subsonic.command;

import static com.orangesoft.subsonic.command.Command.AUTH_FAILURE;
import com.orangesoft.subsonic.data.BinaryResourceContainer;
import com.orangesoft.subsonic.system.RestConnection;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Coypright 2015 Orangesoft
 */
public abstract class StreamCommand extends Command
{
    public BinaryResourceContainer resource;
    protected final static String NULL_STREAM = "Null stream";
    
    @Override
    public void execute()
    {
        try
        {
            connection.doStreamCommand(this);
        }
        catch (IOException e)
        {
            if (e.getMessage().contains("401"))
                failureMessage = AUTH_FAILURE;
            else
                failureMessage = BAD_HOST_ERROR;
        }
        catch (JSONException e)
        {
            failureMessage = PARSE_ERROR;
        }
    }
    
    public void setResource(BinaryResourceContainer resource) throws IOException, JSONException
    {
        if (resource.isAudioContent())
        {
            status = true;
            this.resource = resource;
        }
        else
        {
            parseError( resource );
        }
    }

    private void parseError(BinaryResourceContainer resource) throws IOException, JSONException
    {
        if (resource.isNullSource())
            this.failureMessage = NULL_STREAM;
        else
        {
            JSONObject json = new JSONObject(convertStreamToString(resource.stream()));
            JSONObject subsonicResponse = json.getJSONObject(RestConnection.SUBSONIC_RESPONSE);
            parse(subsonicResponse);
        }
            
    }
    
    static String convertStreamToString(InputStream is) 
    {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        String retVal =  s.hasNext() ? s.next() : "";
        s.close();
        return retVal;
    }
}
