package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 *
 * @author wmckinle
 */
public class DescriptionObject extends TitledObject
{
    final static String DESCRIPTION = "description";
    final static String STATUS = "status";
    
    private final String description;
    private final String status;
    
    public DescriptionObject(JSONObject json) throws JSONException 
    {
        super(json);
        description = json.getString(DESCRIPTION);
        status = json.getString(STATUS);
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getStatus()
    {
        return status;
    }
    
}
