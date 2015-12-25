package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.SearchResult2;
import com.orangesoft.subsonic.system.Connection;
import java.util.Map;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class Search2 extends Command
{
    final static String COMMAND = "search2.view";
    final static String SEARCH_RESULT2 = "searchResult2";
    
    private SearchResult2 searchResult;
    
    public Search2(Connection connection, Map<String, String> params)
    {
        this.connection = connection;
        this.params = params;
    }
    
    @Override
    public String getCommand() 
    {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException 
    {
        searchResult = new SearchResult2(json.getJSONObject(SEARCH_RESULT2));
    }
    
    public SearchResult2 getSearchResult()
    {
        return searchResult;
    }
    
}
