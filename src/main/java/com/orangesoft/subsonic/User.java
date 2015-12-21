package com.orangesoft.subsonic;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 Orangesoft
 */
public class User
{
    final static String USERNAME = "username";
    final static String EMAIL = "email";
    final static String SCROBBLING_ENABLED = "scrobblingEnabled";
    final static String ADMIN_ROLE = "adminRole";
    final static String SETTINGS_ROLE = "settingsRole";
    final static String DOWNLOAD_ROLE = "downloadRole";
    final static String UPLOAD_ROLE = "uploadRole";
    final static String PLAYLIST_ROLE = "playlistRole";
    final static String COVER_ART_ROLE = "coverArtRole";
    final static String COMMENT_ROLE = "commentRole";
    final static String PODCAST_ROLE = "podcastRole";
    final static String STREAM_ROLE = "streamRole";
    final static String JUKEBOX_ROLE = "jukeboxRole";
    final static String SHARE_ROLE = "shareRole";
    final static String FOLDER = "folder";

    private String username;
    private String email;
    private boolean scrobblingEnabled;
    private boolean adminRole;
    private boolean settingsRole;
    private boolean downloadRole;
    private boolean uploadRole;
    private boolean playlistRole;
    private boolean coverArtRole;
    private boolean commentRole;
    private boolean podcastRole;
    private boolean streamRole;
    private boolean jukeboxRole;
    private boolean shareRole;
    private List<Integer> folders;

    public User(JSONObject json) throws JSONException
    {
        username = json.getString(USERNAME);
        email = json.getString(EMAIL);
        scrobblingEnabled = json.getBoolean(SCROBBLING_ENABLED);
        adminRole = json.getBoolean(ADMIN_ROLE);
        settingsRole = json.getBoolean(SETTINGS_ROLE);
        downloadRole = json.getBoolean(DOWNLOAD_ROLE);
        uploadRole = json.getBoolean(UPLOAD_ROLE);
        playlistRole = json.getBoolean(PLAYLIST_ROLE);
        coverArtRole = json.getBoolean(COVER_ART_ROLE);
        commentRole = json.getBoolean(COMMENT_ROLE);
        podcastRole = json.getBoolean(PODCAST_ROLE);
        streamRole = json.getBoolean(STREAM_ROLE);
        jukeboxRole = json.getBoolean(JUKEBOX_ROLE);
        shareRole = json.getBoolean(SHARE_ROLE);
        JSONArray folderArray = json.getJSONArray(FOLDER);
        folders = new ArrayList<>();
        for (int i=0; i<folderArray.length(); i++)
            folders.add(folderArray.getInt(i));
    }

    public String getUsername()
    {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public boolean isScrobblingEnabled()
    {
        return scrobblingEnabled;
    }

    public boolean isAdminRole()
    {
        return adminRole;
    }

    public boolean isSettingsRole()
    {
        return settingsRole;
    }

    public boolean isDownloadRole()
    {
        return downloadRole;
    }

    public boolean isUploadRole()
    {
        return uploadRole;
    }

    public boolean isPlaylistRole()
    {
        return playlistRole;
    }

    public boolean isCoverArtRole()
    {
        return coverArtRole;
    }

    public boolean isCommentRole()
    {
        return commentRole;
    }

    public boolean isPodcastRole()
    {
        return podcastRole;
    }

    public boolean isStreamRole()
    {
        return streamRole;
    }

    public boolean isJukeboxRole()
    {
        return jukeboxRole;
    }

    public boolean isShareRole()
    {
        return shareRole;
    }

    public List<Integer> getFolders()
    {
        return folders;
    }
}
