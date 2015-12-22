package com.orangesoft.subsonic;

import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class Child extends DataObject
{
    final static String IS_DIR = "isDir";
    final static String TITLE = "title";
    final static String SIZE = "size";
    final static String CONTENT_TYPE = "contentType";
    final static String SUFFIX = "suffix";
    final static String DURATION = "duration";
    final static String BITRATE = "bitRate";
    final static String PATH = "path";
    final static String IS_VIDEO = "isVideo";
    final static String CREATED = "created";
    final static String TYPE = "type";
    final static String ORIGINAL_WIDTH = "originalWidth";
    final static String ORIGINAL_HEIGHT = "originalHeight";

    private boolean isDir;
    private String title;
    private String contentType;
    private int size;
    private String suffix;
    private int duration;
    private int bitRate;
    private String path;
    private boolean isVideo;
    private String created;
    private String type;
    private int originalWidth;
    private int originalHeight;

    public Child(JSONObject json) throws JSONException
    {
        super(json);
        isDir = json.getBoolean(IS_DIR);
        title = json.getString(TITLE);
        size = json.getInt(SIZE);
        contentType = json.getString(CONTENT_TYPE);
        suffix = json.getString(SUFFIX);
        duration = json.getInt(DURATION);
        bitRate = json.getInt(BITRATE);
        path = json.getString(PATH);
        isVideo = json.getBoolean(IS_VIDEO);
        created = json.getString(CREATED);
        type = json.getString(TYPE);
        originalWidth = json.optInt(ORIGINAL_WIDTH);
        originalHeight = json.optInt(ORIGINAL_HEIGHT);
    }

    public boolean isDir()
    {
        return isDir;
    }

    public String getTitle()
    {
        return title;
    }

    public int getSize()
    {
        return size;
    }

    public String getContentType()
    {
        return contentType;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public int getDuration()
    {
        return duration;
    }

    public int getBitRate()
    {
        return bitRate;
    }

    public String getPath()
    {
        return path;
    }

    public boolean isVideo()
    {
        return isVideo;
    }

    public String getCreated()
    {
        return created;
    }

    public String getType()
    {
        return type;
    }

    public int getOriginalWidth()
    {
        return originalWidth;
    }

    public int getOriginalHeight()
    {
        return originalHeight;
    }
}
