/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.system.Connection;
import com.orangesoft.subsonic.MusicFolder;
import java.util.ArrayList;
import java.util.List;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 *
 * @author will
 */
public class GetMusicFolders extends Command
{
    static final String GET_MUSIC_FOLDERS = "getMusicFolders.view";
    static final String MUSIC_FOLDERS = "musicFolders";
    static final String MUSIC_FOLDER = "musicFolder";
    private List<MusicFolder> musicFolders;
    
    public GetMusicFolders(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public String getCommand() {
        return GET_MUSIC_FOLDERS;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException 
    {
        musicFolders = new ArrayList<>();
        JSONObject jsonMusicFolders = json.getJSONObject(MUSIC_FOLDERS);
        JSONArray musicFolderArray = jsonMusicFolders.getJSONArray(MUSIC_FOLDER);
        for (int i=0; i < musicFolderArray.length(); i++)
        {
            JSONObject listObject = musicFolderArray.getJSONObject(i);
            MusicFolder folder = new MusicFolder(listObject);
            musicFolders.add(folder);
        }
    }
    
    public List<MusicFolder> getList()
    {
        return musicFolders;
    }
    
}
