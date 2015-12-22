package com.orangesoft.subsonic;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 Orangesoft
 */
public class Indexes
{
    final static String LAST_MODIFIED = "lastModified";
    final static String IGNORED_ARTICLES = "ignoredArticles";
    final static String SHORTCUT = "shortcut";
    final static String INDEX = "index";
    final static String CHILD = "child";

    private long lastModified;
    private String ignoredArticles;
    private List<Shortcut> shortcuts;
    private List<Index> indices;
    private List<Child> children;

    public Indexes(JSONObject json) throws JSONException
    {
        lastModified = json.getLong(LAST_MODIFIED);
        ignoredArticles = json.getString(IGNORED_ARTICLES);
        shortcuts = new ArrayList<>();
        JSONArray shortcutArray = json.getJSONArray(SHORTCUT);
        for (int i=0; i<shortcutArray.length(); i++)
            shortcuts.add(new Shortcut(shortcutArray.getJSONObject(i)));
        indices = new ArrayList<>();
        JSONArray indexArray = json.getJSONArray(INDEX);
        for (int i=0; i<indexArray.length(); i++)
            indices.add(new Index(indexArray.getJSONObject(i)));
        children = new ArrayList<>();
        JSONArray childArray = json.getJSONArray(CHILD);
        for (int i=0; i<childArray.length(); i++)
            children.add(new Child(childArray.getJSONObject(i)));
    }

    public long getLastModified()
    {
        return lastModified;
    }

    public String getIgnoredArticles()
    {
        return ignoredArticles;
    }

    public List<Shortcut> getShortcuts()
    {
        return shortcuts;
    }

    public List<Index> getList()
    {
        return indices;
    }

    public List<Child> getChildren()
    {
        return children;
    }
}
