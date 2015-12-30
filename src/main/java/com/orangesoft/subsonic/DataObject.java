/*
 * Coypright 2015 Orangesoft
 */
package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public abstract class DataObject 
{
    public final static String ID = "id";
    final static String COVER_ART = "coverArt";
    final static String IS_DIR = "isDir";
    
    public final static int UNDEFINED_INT = -1;
    
    private final String id;
    String coverArt;
    boolean isDir;
    
    public DataObject(JSONObject json) throws JSONException
    {
        this.id = json.getString(ID);
    }
    
    public String getId()
    {
        return id;
    }
    
    final void parseCoverArt(JSONObject json) throws JSONException
    {
        coverArt = json.optString(COVER_ART);
    }
    
    final void parseIsDir(JSONObject json) throws JSONException
    {
        isDir = json.optBoolean(IS_DIR);
    }
}
