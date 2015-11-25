/*
 * Coypright 2015 Orangesoft
 */
package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public abstract class DataObject 
{
    public final static String ID = "id";
    public final static int UNDEFINED_INT = -1;
    
    private final int id;
    
    public DataObject(JSONObject json) throws JSONException
    {
        this.id = json.getInt(ID);
    }
    
    public int getId()
    {
        return id;
    }
}
