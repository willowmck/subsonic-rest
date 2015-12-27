package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Entry;
import com.orangesoft.subsonic.Playlist;
import com.orangesoft.subsonic.system.Connection;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class GetPlaylistTest 
{
    Connection connection;
    Map<String, String> params;
    GetPlaylist getPlaylist;
    
    @Before
    public void setup()
    {
        connection = new FakeConnection();
        params = new HashMap<>();
        getPlaylist = new GetPlaylist(connection, params);
    }
    
    @Test
    public void missingRequiredParameters()
    {
        getPlaylist.execute();
        assert(!getPlaylist.getStatus());
        assertEquals("Required int parameter 'id' is not present", getPlaylist.getFailureMessage());
    }
    
    @Test
    public void badId()
    {
        params.put("id", "0");
        getPlaylist.execute();
        assert(!getPlaylist.getStatus());
        assertEquals("Playlist not found: 0", getPlaylist.getFailureMessage());
    }
    
    @Test
    public void getPlaylist()
    {
        params.put("id", "286");
        getPlaylist.execute();
        assert(getPlaylist.getStatus());
        Playlist playlist = getPlaylist.getPlaylist();
        assertEquals(286, playlist.getId());
        assertEquals("Carefree", playlist.getName());
        assertEquals("Auto-imported from Carefree.m3u", playlist.getComment());
        assertEquals("admin", playlist.getOwner());
        assert(playlist.isPublic());
        assertEquals(24, playlist.getSongCount());
        assertEquals(64117, playlist.getDuration());
        assertEquals("2015-12-15T08:00:13.095Z", playlist.getCreated());
        assertEquals("2015-12-15T08:00:13.107Z", playlist.getChanged());
        assertEquals("pl-286", playlist.getCoverArt());
        List<Entry> entries = playlist.getEntries();
        Entry entry = entries.get(0);
        assertEquals(10654, entry.getId());
        assertEquals(10635, entry.getParent());
        assert(!entry.isDir());
        assertEquals("Don't Lie", entry.getTitle());
        assertEquals("Monkey Business", entry.getAlbum());
        assertEquals(4, entry.getTrack());
        assertEquals(2005, entry.getYear());
        assertEquals("17", entry.getGenre());
        assertEquals("10635", entry.getCoverArt());
        assertEquals(4807824, entry.getSize());
        assertEquals("audio/mpeg", entry.getContentType());
        assertEquals("mp3", entry.getSuffix());
        assertEquals(219, entry.getDuration());
        assertEquals(174, entry.getBitRate());
        assertEquals("Artists/B/Black Eyed Peas/Monkey Business/04 - The Black Eyed Peas - Don't Lie.mp3",
                entry.getPath());
        assert(!entry.isVideo());
        assertEquals("2012-12-23T15:32:46.000Z", entry.getCreated());
        assertEquals("1866", entry.getAlbumId());
        assertEquals("326", entry.getArtistId());
        assertEquals("music", entry.getType());
        assertEquals(0, entry.getDiscNumber());
    }

    private static class FakeConnection implements Connection 
    {
        final static String missingParams = "{\n" +
            "   \"subsonic-response\" : {\n" +
            "      \"status\" : \"failed\",\n" +
            "      \"version\" : \"1.13.0\",\n" +
            "      \"error\" : {\n" +
            "         \"code\" : 10,\n" +
            "         \"message\" : \"Required int parameter 'id' is not present\"\n" +
            "      }\n" +
            "   }\n" +
            "}";
        
        final static String playlistNotFound = "{\n" +
            "   \"subsonic-response\" : {\n" +
            "      \"status\" : \"failed\",\n" +
            "      \"version\" : \"1.13.0\",\n" +
            "      \"error\" : {\n" +
            "         \"code\" : 70,\n" +
            "         \"message\" : \"Playlist not found: 0\"\n" +
            "      }\n" +
            "   }\n" +
            "}";
        
        final static String response = "{\n" +
            "   \"subsonic-response\" : {\n" +
            "      \"status\" : \"ok\",\n" +
            "      \"version\" : \"1.13.0\",\n" +
            "      \"playlist\" : {\n" +
            "         \"id\" : \"286\",\n" +
            "         \"name\" : \"Carefree\",\n" +
            "         \"comment\" : \"Auto-imported from Carefree.m3u\",\n" +
            "         \"owner\" : \"admin\",\n" +
            "         \"public\" : true,\n" +
            "         \"songCount\" : 24,\n" +
            "         \"duration\" : 64117,\n" +
            "         \"created\" : \"2015-12-15T08:00:13.095Z\",\n" +
            "         \"changed\" : \"2015-12-15T08:00:13.107Z\",\n" +
            "         \"coverArt\" : \"pl-286\",\n" +
            "         \"entry\" : [ {\n" +
            "            \"id\" : \"10654\",\n" +
            "            \"parent\" : \"10635\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Don't Lie\",\n" +
            "            \"album\" : \"Monkey Business\",\n" +
            "            \"artist\" : \"The Black Eyed Peas\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 2005,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"10635\",\n" +
            "            \"size\" : 4807824,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 219,\n" +
            "            \"bitRate\" : 174,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Monkey Business/04 - The Black Eyed Peas - Don't Lie.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:46.000Z\",\n" +
            "            \"albumId\" : \"1866\",\n" +
            "            \"artistId\" : \"326\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15341\",\n" +
            "            \"parent\" : \"15328\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Diver\",\n" +
            "            \"album\" : \"Body Music\",\n" +
            "            \"artist\" : \"AlunaGeorge\",\n" +
            "            \"track\" : 7,\n" +
            "            \"year\" : 2013,\n" +
            "            \"genre\" : \"Alternative Rock\",\n" +
            "            \"coverArt\" : \"15328\",\n" +
            "            \"size\" : 6542307,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 197,\n" +
            "            \"bitRate\" : 255,\n" +
            "            \"path\" : \"Artists/A/AlunaGeorge/Body Music/07. Diver.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2013-12-26T13:34:23.000Z\",\n" +
            "            \"albumId\" : \"2520\",\n" +
            "            \"artistId\" : \"1615\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8797\",\n" +
            "            \"parent\" : \"8793\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Dizzy's Business (2005 Digital Remaster)\",\n" +
            "            \"album\" : \"Cannonball In Europe\",\n" +
            "            \"artist\" : \"Cannonball Adderley Sextet\",\n" +
            "            \"track\" : 7,\n" +
            "            \"year\" : 2005,\n" +
            "            \"genre\" : \"Jazz\",\n" +
            "            \"coverArt\" : \"8793\",\n" +
            "            \"size\" : 17357166,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 453,\n" +
            "            \"bitRate\" : 301,\n" +
            "            \"path\" : \"Artists/A/Cannonball Adderley/Cannonball in Europe/07 - Dizzy's Business (2005 Digital Remaster).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-22T22:17:42.000Z\",\n" +
            "            \"albumId\" : \"1645\",\n" +
            "            \"artistId\" : \"1049\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9146\",\n" +
            "            \"parent\" : \"9132\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Dreams\",\n" +
            "            \"album\" : \"The Allman Brothers Band\",\n" +
            "            \"artist\" : \"The Allman Brothers Band\",\n" +
            "            \"track\" : 6,\n" +
            "            \"year\" : 1969,\n" +
            "            \"size\" : 10569307,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 440,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/A/The Allman Brothers Band/The Allman Brothers Band/6 - Dreams.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:15:17.000Z\",\n" +
            "            \"albumId\" : \"1705\",\n" +
            "            \"artistId\" : \"1077\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10651\",\n" +
            "            \"parent\" : \"10635\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Pump It\",\n" +
            "            \"album\" : \"Monkey Business\",\n" +
            "            \"artist\" : \"The Black Eyed Peas\",\n" +
            "            \"track\" : 1,\n" +
            "            \"year\" : 2005,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"10635\",\n" +
            "            \"size\" : 4987104,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 213,\n" +
            "            \"bitRate\" : 186,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Monkey Business/01 - The Black Eyed Peas - Pump It.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:08.000Z\",\n" +
            "            \"albumId\" : \"1866\",\n" +
            "            \"artistId\" : \"326\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10709\",\n" +
            "            \"parent\" : \"10710\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"It's the New Style (live)\",\n" +
            "            \"album\" : \"No Alternative\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 16,\n" +
            "            \"year\" : 1993,\n" +
            "            \"genre\" : \"Unknown\",\n" +
            "            \"size\" : 2585515,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 140,\n" +
            "            \"bitRate\" : 147,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/No Alternative/16 - It's the New Style (live).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:13:16.000Z\",\n" +
            "            \"albumId\" : \"1879\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8751\",\n" +
            "            \"parent\" : \"8746\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Sunburn\",\n" +
            "            \"album\" : \"Movin' Melodies\",\n" +
            "            \"artist\" : \"ATB\",\n" +
            "            \"year\" : 1999,\n" +
            "            \"genre\" : \"13\",\n" +
            "            \"coverArt\" : \"8746\",\n" +
            "            \"size\" : 5590673,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 232,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/A/ATB/cd2/ATB - Sunburn.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:32:21.000Z\",\n" +
            "            \"albumId\" : \"1631\",\n" +
            "            \"artistId\" : \"1037\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10785\",\n" +
            "            \"parent\" : \"9279\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Sleep Tight\",\n" +
            "            \"album\" : \"Big Bad Voodoo Daddy\",\n" +
            "            \"artist\" : \"Big Bad Voodoo Daddy\",\n" +
            "            \"genre\" : \"0\",\n" +
            "            \"coverArt\" : \"9279\",\n" +
            "            \"size\" : 4391487,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 271,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Big Bad Voodoo Daddy/Big Bad Voodoo Daddy - Sleep Tight.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T14:38:01.000Z\",\n" +
            "            \"albumId\" : \"1883\",\n" +
            "            \"artistId\" : \"1195\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10128\",\n" +
            "            \"parent\" : \"10114\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"I'm Gonna Love You Too\",\n" +
            "            \"album\" : \"Parallel Lines [Bonus Tracks]\",\n" +
            "            \"artist\" : \"Blondie\",\n" +
            "            \"track\" : 11,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"10114\",\n" +
            "            \"size\" : 3054132,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 126,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Blondie/Parallel Lines [Bonus Tracks]/11 - Blondie - I'm Gonna Love You Too.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:22:37.000Z\",\n" +
            "            \"albumId\" : \"1807\",\n" +
            "            \"artistId\" : \"1146\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9909\",\n" +
            "            \"parent\" : \"9906\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Life Is White\",\n" +
            "            \"album\" : \"#1 Record/Radio City\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 14,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"9906\",\n" +
            "            \"size\" : 4809749,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 199,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Big Star/#1 Record-Radio City/14 - Big Star - Life Is White.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:20:28.000Z\",\n" +
            "            \"albumId\" : \"1772\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8729\",\n" +
            "            \"parent\" : \"8631\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Time For Bed\",\n" +
            "            \"album\" : \"Apples In Stereo\",\n" +
            "            \"artist\" : \"Apples In Stereo\",\n" +
            "            \"coverArt\" : \"8631\",\n" +
            "            \"size\" : 4999909,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 310,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/A/Apples In Stereo/Apples In Stereo - Time For Bed.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:34:25.000Z\",\n" +
            "            \"albumId\" : \"1622\",\n" +
            "            \"artistId\" : \"1036\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9910\",\n" +
            "            \"parent\" : \"9906\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"What's Going Ahn\",\n" +
            "            \"album\" : \"#1 Record/Radio City\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 16,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"9906\",\n" +
            "            \"size\" : 3870228,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 160,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Big Star/#1 Record-Radio City/16 - Big Star - What's Going Ahn.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:20:43.000Z\",\n" +
            "            \"albumId\" : \"1772\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15340\",\n" +
            "            \"parent\" : \"15328\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Kaleidoscope Love\",\n" +
            "            \"album\" : \"Body Music\",\n" +
            "            \"artist\" : \"AlunaGeorge\",\n" +
            "            \"track\" : 5,\n" +
            "            \"year\" : 2013,\n" +
            "            \"genre\" : \"Alternative Rock\",\n" +
            "            \"coverArt\" : \"15328\",\n" +
            "            \"size\" : 7705065,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 233,\n" +
            "            \"bitRate\" : 256,\n" +
            "            \"path\" : \"Artists/A/AlunaGeorge/Body Music/05. Kaleidoscope Love.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2013-12-26T13:34:17.000Z\",\n" +
            "            \"albumId\" : \"2520\",\n" +
            "            \"artistId\" : \"1615\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8734\",\n" +
            "            \"parent\" : \"8632\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Let U Go (Clubb mix)\",\n" +
            "            \"album\" : \"Let U Go\",\n" +
            "            \"artist\" : \"ATB\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 2001,\n" +
            "            \"genre\" : \"52\",\n" +
            "            \"coverArt\" : \"8632\",\n" +
            "            \"size\" : 9990771,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 499,\n" +
            "            \"bitRate\" : 160,\n" +
            "            \"path\" : \"Artists/A/ATB/ATB - Let You Go (Club Mix)(1).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:33:46.000Z\",\n" +
            "            \"albumId\" : \"1626\",\n" +
            "            \"artistId\" : \"1037\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10644\",\n" +
            "            \"parent\" : \"10634\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Fly Away\",\n" +
            "            \"album\" : \"Elephunk [Bonus Track]\",\n" +
            "            \"artist\" : \"Black Eyed Peas\",\n" +
            "            \"track\" : 9,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10634\",\n" +
            "            \"size\" : 5209840,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 215,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Elephunk [Bonus Track]/09 - Black Eyed Peas - Fly Away.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:31:38.000Z\",\n" +
            "            \"albumId\" : \"1865\",\n" +
            "            \"artistId\" : \"1186\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8752\",\n" +
            "            \"parent\" : \"8746\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Heart Beats like a Drum\",\n" +
            "            \"album\" : \"cd2\",\n" +
            "            \"artist\" : \"ATC\",\n" +
            "            \"genre\" : \"12\",\n" +
            "            \"coverArt\" : \"8746\",\n" +
            "            \"size\" : 4434296,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 220,\n" +
            "            \"bitRate\" : 160,\n" +
            "            \"path\" : \"Artists/A/ATB/cd2/ATB - My Heart Beats Like A Drum.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:32:24.000Z\",\n" +
            "            \"albumId\" : \"1636\",\n" +
            "            \"artistId\" : \"1038\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9907\",\n" +
            "            \"parent\" : \"9906\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"When My Baby's Beside Me\",\n" +
            "            \"album\" : \"#1 Record/Radio City\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 7,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"9906\",\n" +
            "            \"size\" : 4907404,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 203,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Big Star/#1 Record-Radio City/07 - Big Star - When My Baby's Beside Me.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:20:42.000Z\",\n" +
            "            \"albumId\" : \"1772\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10742\",\n" +
            "            \"parent\" : \"10707\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Alright Hear This\",\n" +
            "            \"album\" : \"Ill Communication\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 11,\n" +
            "            \"genre\" : \"misc\",\n" +
            "            \"coverArt\" : \"10707\",\n" +
            "            \"size\" : 3021781,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 187,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Ill Communication/Beastie Boys-Ill Communication-11-Alright Hear This.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:38.000Z\",\n" +
            "            \"albumId\" : \"1877\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10747\",\n" +
            "            \"parent\" : \"10707\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Get It Together\",\n" +
            "            \"album\" : \"Ill Communication\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 7,\n" +
            "            \"genre\" : \"misc\",\n" +
            "            \"coverArt\" : \"10707\",\n" +
            "            \"size\" : 3960904,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 246,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Ill Communication/Beastie Boys-Ill Communication-07-Get It Together.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:16.000Z\",\n" +
            "            \"albumId\" : \"1877\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10652\",\n" +
            "            \"parent\" : \"10635\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Union (feat. Sting)\",\n" +
            "            \"album\" : \"Monkey Business\",\n" +
            "            \"artist\" : \"The Black Eyed Peas\",\n" +
            "            \"track\" : 15,\n" +
            "            \"year\" : 2005,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"10635\",\n" +
            "            \"size\" : 7213129,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 304,\n" +
            "            \"bitRate\" : 189,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Monkey Business/15 - The Black Eyed Peas - Union (feat. Sting).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:31.000Z\",\n" +
            "            \"albumId\" : \"1866\",\n" +
            "            \"artistId\" : \"326\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10744\",\n" +
            "            \"parent\" : \"10707\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Bobo on the Corner\",\n" +
            "            \"album\" : \"Ill Communication\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 4,\n" +
            "            \"genre\" : \"misc\",\n" +
            "            \"coverArt\" : \"10707\",\n" +
            "            \"size\" : 1203241,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 73,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Ill Communication/Beastie Boys-Ill Communication-04-Bobo On The Corner.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:35.000Z\",\n" +
            "            \"albumId\" : \"1877\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10640\",\n" +
            "            \"parent\" : \"10634\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Anxiety\",\n" +
            "            \"album\" : \"Elephunk [Bonus Track]\",\n" +
            "            \"artist\" : \"Black Eyed Peas\",\n" +
            "            \"track\" : 12,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10634\",\n" +
            "            \"size\" : 5278688,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 218,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Elephunk [Bonus Track]/12 - Black Eyed Peas - Anxiety.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:03.000Z\",\n" +
            "            \"albumId\" : \"1865\",\n" +
            "            \"artistId\" : \"1186\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8742\",\n" +
            "            \"parent\" : \"8632\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"The Matrix Theme\",\n" +
            "            \"album\" : \"New Project\",\n" +
            "            \"artist\" : \"ATB vs. Alice DJ\",\n" +
            "            \"track\" : 13,\n" +
            "            \"coverArt\" : \"8632\",\n" +
            "            \"size\" : 2974259,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 186,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/A/ATB/ATB vs Alice DJ - The Matrix Theme (remix).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:33:19.000Z\",\n" +
            "            \"albumId\" : \"1632\",\n" +
            "            \"artistId\" : \"1040\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10725\",\n" +
            "            \"parent\" : \"10706\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Song for the Man\",\n" +
            "            \"album\" : \"Hello Nasty\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 4,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10706\",\n" +
            "            \"size\" : 4671111,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 193,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Hello Nasty [Japan]/04 - Beastie Boys - Song for the Man.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:13:49.000Z\",\n" +
            "            \"albumId\" : \"1876\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9141\",\n" +
            "            \"parent\" : \"9130\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Mountain Jam\",\n" +
            "            \"album\" : \"Eat A Peach\",\n" +
            "            \"artist\" : \"The Allman Brothers Band\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 1972,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"9130\",\n" +
            "            \"size\" : 55705584,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 2021,\n" +
            "            \"bitRate\" : 220,\n" +
            "            \"path\" : \"Artists/A/The Allman Brothers Band/Eat A Peach/04 - The Allman Brothers Band - Mountain Jam.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:16:00.000Z\",\n" +
            "            \"albumId\" : \"1704\",\n" +
            "            \"artistId\" : \"1077\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8803\",\n" +
            "            \"parent\" : \"8794\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Games\",\n" +
            "            \"album\" : \"Mercy, Mercy, Mercy! Live at \\\"The Club\\\"\",\n" +
            "            \"artist\" : \"Cannonball Adderley\",\n" +
            "            \"track\" : 2,\n" +
            "            \"year\" : 1966,\n" +
            "            \"genre\" : \"8\",\n" +
            "            \"coverArt\" : \"8794\",\n" +
            "            \"size\" : 11634084,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 439,\n" +
            "            \"bitRate\" : 211,\n" +
            "            \"path\" : \"Artists/A/Cannonball Adderley/Mercy Mercy Mercy Live at The Club/02 - Games.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:16:35.000Z\",\n" +
            "            \"albumId\" : \"1646\",\n" +
            "            \"artistId\" : \"1050\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10664\",\n" +
            "            \"parent\" : \"10635\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Dum Diddly (feat. Dante Santiago)\",\n" +
            "            \"album\" : \"Monkey Business\",\n" +
            "            \"artist\" : \"The Black Eyed Peas\",\n" +
            "            \"track\" : 7,\n" +
            "            \"year\" : 2005,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"10635\",\n" +
            "            \"size\" : 6788040,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 259,\n" +
            "            \"bitRate\" : 208,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Monkey Business/07 - The Black Eyed Peas - Dum Diddly (feat. Dante Santiago).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:43.000Z\",\n" +
            "            \"albumId\" : \"1866\",\n" +
            "            \"artistId\" : \"326\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10646\",\n" +
            "            \"parent\" : \"10634\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Labor Day (It's a Holiday)\",\n" +
            "            \"album\" : \"Elephunk [Bonus Track]\",\n" +
            "            \"artist\" : \"Black Eyed Peas\",\n" +
            "            \"track\" : 2,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10634\",\n" +
            "            \"size\" : 5771629,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 238,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Elephunk [Bonus Track]/02 - Black Eyed Peas - Labor Day (It's a Holiday).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:05.000Z\",\n" +
            "            \"albumId\" : \"1865\",\n" +
            "            \"artistId\" : \"1186\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9930\",\n" +
            "            \"parent\" : \"9906\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"September Gurls\",\n" +
            "            \"album\" : \"#1 Record/Radio City\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 22,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"9906\",\n" +
            "            \"size\" : 4096355,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 169,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Big Star/#1 Record-Radio City/22 - Big Star - September Gurls.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:20:55.000Z\",\n" +
            "            \"albumId\" : \"1772\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9893\",\n" +
            "            \"parent\" : \"9892\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Spread Your Love\",\n" +
            "            \"album\" : \"B.R.M.C.\",\n" +
            "            \"artist\" : \"Black Rebel Motorcycle Club\",\n" +
            "            \"track\" : 9,\n" +
            "            \"year\" : 2001,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"9892\",\n" +
            "            \"size\" : 5455195,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 225,\n" +
            "            \"bitRate\" : 193,\n" +
            "            \"path\" : \"Artists/B/Black Rebel Motorcycle Club/BF6TTZ~Z/09 - Black Rebel Motorcycle Club - Spread Your Love.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T14:48:53.000Z\",\n" +
            "            \"albumId\" : \"1770\",\n" +
            "            \"artistId\" : \"1119\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8814\",\n" +
            "            \"parent\" : \"8809\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Book-Ends\",\n" +
            "            \"album\" : \"Pyramid\",\n" +
            "            \"artist\" : \"Cannonball Adderley Quintet\",\n" +
            "            \"track\" : 3,\n" +
            "            \"year\" : 2006,\n" +
            "            \"genre\" : \"Jazz\",\n" +
            "            \"coverArt\" : \"8809\",\n" +
            "            \"size\" : 9800013,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 336,\n" +
            "            \"bitRate\" : 231,\n" +
            "            \"path\" : \"Artists/A/Cannonball Adderley/Cannonball Adderley Quintet/Pyramid/03. Book-Ends.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-22T22:16:57.000Z\",\n" +
            "            \"albumId\" : \"1647\",\n" +
            "            \"artistId\" : \"1051\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9147\",\n" +
            "            \"parent\" : \"9132\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Black Hearted Woman\",\n" +
            "            \"album\" : \"The Allman Brothers Band\",\n" +
            "            \"artist\" : \"The Allman Brothers Band\",\n" +
            "            \"track\" : 3,\n" +
            "            \"year\" : 1969,\n" +
            "            \"size\" : 7458436,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 311,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/A/The Allman Brothers Band/The Allman Brothers Band/3 - Black Hearted Woman.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:15:04.000Z\",\n" +
            "            \"albumId\" : \"1705\",\n" +
            "            \"artistId\" : \"1077\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9138\",\n" +
            "            \"parent\" : \"9130\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Little Martha\",\n" +
            "            \"album\" : \"Eat A Peach\",\n" +
            "            \"artist\" : \"The Allman Brothers Band\",\n" +
            "            \"track\" : 9,\n" +
            "            \"year\" : 1972,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"9130\",\n" +
            "            \"size\" : 3090752,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 127,\n" +
            "            \"bitRate\" : 193,\n" +
            "            \"path\" : \"Artists/A/The Allman Brothers Band/Eat A Peach/09 - The Allman Brothers Band - Little Martha.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:16:13.000Z\",\n" +
            "            \"albumId\" : \"1704\",\n" +
            "            \"artistId\" : \"1077\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8805\",\n" +
            "            \"parent\" : \"8794\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Hippodelphia\",\n" +
            "            \"album\" : \"Mercy, Mercy, Mercy! Live at \\\"The Club\\\"\",\n" +
            "            \"artist\" : \"Cannonball Adderley\",\n" +
            "            \"track\" : 5,\n" +
            "            \"year\" : 1966,\n" +
            "            \"genre\" : \"8\",\n" +
            "            \"coverArt\" : \"8794\",\n" +
            "            \"size\" : 7909526,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 349,\n" +
            "            \"bitRate\" : 180,\n" +
            "            \"path\" : \"Artists/A/Cannonball Adderley/Mercy Mercy Mercy Live at The Club/05 - Hippodelphia.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:16:29.000Z\",\n" +
            "            \"albumId\" : \"1646\",\n" +
            "            \"artistId\" : \"1050\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9903\",\n" +
            "            \"parent\" : \"9892\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Love Burns\",\n" +
            "            \"album\" : \"B.R.M.C.\",\n" +
            "            \"artist\" : \"Black Rebel Motorcycle Club\",\n" +
            "            \"track\" : 1,\n" +
            "            \"year\" : 2001,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"9892\",\n" +
            "            \"size\" : 5916257,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 245,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Black Rebel Motorcycle Club/BF6TTZ~Z/01 - Black Rebel Motorcycle Club - Love Burns.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T14:48:47.000Z\",\n" +
            "            \"albumId\" : \"1770\",\n" +
            "            \"artistId\" : \"1119\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10738\",\n" +
            "            \"parent\" : \"10707\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Bodhisattva Vow\",\n" +
            "            \"album\" : \"Ill Communication\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 19,\n" +
            "            \"genre\" : \"misc\",\n" +
            "            \"coverArt\" : \"10707\",\n" +
            "            \"size\" : 3044319,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 188,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Ill Communication/Beastie Boys-Ill Communication-19-Bodhisattva Vow.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:19.000Z\",\n" +
            "            \"albumId\" : \"1877\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10645\",\n" +
            "            \"parent\" : \"10634\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Where Is the Love\",\n" +
            "            \"album\" : \"Elephunk [Bonus Track]\",\n" +
            "            \"artist\" : \"Black Eyed Peas\",\n" +
            "            \"track\" : 13,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10634\",\n" +
            "            \"size\" : 6633493,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 274,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Elephunk [Bonus Track]/13 - Black Eyed Peas - Where Is the Love.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:31:35.000Z\",\n" +
            "            \"albumId\" : \"1865\",\n" +
            "            \"artistId\" : \"1186\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15334\",\n" +
            "            \"parent\" : \"15328\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Outlines\",\n" +
            "            \"album\" : \"Body Music\",\n" +
            "            \"artist\" : \"AlunaGeorge\",\n" +
            "            \"track\" : 1,\n" +
            "            \"year\" : 2013,\n" +
            "            \"genre\" : \"Alternative Rock\",\n" +
            "            \"coverArt\" : \"15328\",\n" +
            "            \"size\" : 7239626,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 227,\n" +
            "            \"bitRate\" : 246,\n" +
            "            \"path\" : \"Artists/A/AlunaGeorge/Body Music/01. Outlines.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2013-12-26T13:34:07.000Z\",\n" +
            "            \"albumId\" : \"2520\",\n" +
            "            \"artistId\" : \"1615\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10585\",\n" +
            "            \"parent\" : \"10576\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Can't Wait (Live)\",\n" +
            "            \"album\" : \"The Globe\",\n" +
            "            \"artist\" : \"Big Audio Dynamite II\",\n" +
            "            \"track\" : 2,\n" +
            "            \"year\" : 1991,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"size\" : 11109134,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 277,\n" +
            "            \"bitRate\" : 320,\n" +
            "            \"path\" : \"Artists/B/Big Audio Dynamite II/The Globe/Can't Wait (Live).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-23T14:53:02.000Z\",\n" +
            "            \"albumId\" : \"1860\",\n" +
            "            \"artistId\" : \"1181\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10648\",\n" +
            "            \"parent\" : \"10634\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Smells Like Funk\",\n" +
            "            \"album\" : \"Elephunk [Bonus Track]\",\n" +
            "            \"artist\" : \"Black Eyed Peas\",\n" +
            "            \"track\" : 6,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10634\",\n" +
            "            \"size\" : 7361421,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 304,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Elephunk [Bonus Track]/06 - Black Eyed Peas - Smells Like Funk.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:31:32.000Z\",\n" +
            "            \"albumId\" : \"1865\",\n" +
            "            \"artistId\" : \"1186\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10581\",\n" +
            "            \"parent\" : \"10576\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Rush\",\n" +
            "            \"album\" : \"The Globe\",\n" +
            "            \"artist\" : \"Big Audio Dynamite II\",\n" +
            "            \"track\" : 1,\n" +
            "            \"year\" : 1991,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"size\" : 10370359,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 259,\n" +
            "            \"bitRate\" : 320,\n" +
            "            \"path\" : \"Artists/B/Big Audio Dynamite II/The Globe/Rush.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-23T14:52:47.000Z\",\n" +
            "            \"albumId\" : \"1860\",\n" +
            "            \"artistId\" : \"1181\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10643\",\n" +
            "            \"parent\" : \"10634\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Shut Up\",\n" +
            "            \"album\" : \"Elephunk [Bonus Track]\",\n" +
            "            \"artist\" : \"Black Eyed Peas\",\n" +
            "            \"track\" : 5,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10634\",\n" +
            "            \"size\" : 7155273,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 296,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Elephunk [Bonus Track]/05 - Black Eyed Peas - Shut Up.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:31:57.000Z\",\n" +
            "            \"albumId\" : \"1865\",\n" +
            "            \"artistId\" : \"1186\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15947\",\n" +
            "            \"parent\" : \"15939\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Till The End Of The Day\",\n" +
            "            \"album\" : \"Third/Sister Lovers\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 16,\n" +
            "            \"year\" : 2006,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"coverArt\" : \"15939\",\n" +
            "            \"size\" : 4633701,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 135,\n" +
            "            \"bitRate\" : 256,\n" +
            "            \"path\" : \"Artists/B/Big Star/Third Sister Lovers/16 - Till The End Of The Day.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2014-07-21T10:15:14.000Z\",\n" +
            "            \"albumId\" : \"2557\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9926\",\n" +
            "            \"parent\" : \"9906\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Give Me Another Chance\",\n" +
            "            \"album\" : \"#1 Record/Radio City\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 9,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"9906\",\n" +
            "            \"size\" : 5002699,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 207,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Big Star/#1 Record-Radio City/09 - Big Star - Give Me Another Chance.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:21:02.000Z\",\n" +
            "            \"albumId\" : \"1772\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15938\",\n" +
            "            \"parent\" : \"15939\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"For You\",\n" +
            "            \"album\" : \"Third/Sister Lovers\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 10,\n" +
            "            \"year\" : 2006,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"coverArt\" : \"15939\",\n" +
            "            \"size\" : 5732779,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 163,\n" +
            "            \"bitRate\" : 266,\n" +
            "            \"path\" : \"Artists/B/Big Star/Third Sister Lovers/10 - For You.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2014-07-21T10:14:36.000Z\",\n" +
            "            \"albumId\" : \"2557\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10728\",\n" +
            "            \"parent\" : \"10706\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Just a Test\",\n" +
            "            \"album\" : \"Hello Nasty\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 5,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10706\",\n" +
            "            \"size\" : 3206080,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 132,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Hello Nasty [Japan]/05 - Beastie Boys - Just a Test.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:13:35.000Z\",\n" +
            "            \"albumId\" : \"1876\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10637\",\n" +
            "            \"parent\" : \"10634\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"The Apl Song\",\n" +
            "            \"album\" : \"Elephunk [Bonus Track]\",\n" +
            "            \"artist\" : \"Black Eyed Peas\",\n" +
            "            \"track\" : 11,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10634\",\n" +
            "            \"size\" : 4232735,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 174,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Elephunk [Bonus Track]/11 - Black Eyed Peas - The Apl Song.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:31:24.000Z\",\n" +
            "            \"albumId\" : \"1865\",\n" +
            "            \"artistId\" : \"1186\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15329\",\n" +
            "            \"parent\" : \"15328\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Lost & Found\",\n" +
            "            \"album\" : \"Body Music\",\n" +
            "            \"artist\" : \"AlunaGeorge\",\n" +
            "            \"track\" : 8,\n" +
            "            \"year\" : 2013,\n" +
            "            \"genre\" : \"Alternative Rock\",\n" +
            "            \"coverArt\" : \"15328\",\n" +
            "            \"size\" : 8772430,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 253,\n" +
            "            \"bitRate\" : 269,\n" +
            "            \"path\" : \"Artists/A/AlunaGeorge/Body Music/08. Lost & Found.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2013-12-26T13:34:25.000Z\",\n" +
            "            \"albumId\" : \"2520\",\n" +
            "            \"artistId\" : \"1615\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10123\",\n" +
            "            \"parent\" : \"10114\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Just Go Away\",\n" +
            "            \"album\" : \"Parallel Lines [Bonus Tracks]\",\n" +
            "            \"artist\" : \"Blondie\",\n" +
            "            \"track\" : 12,\n" +
            "            \"year\" : 2001,\n" +
            "            \"genre\" : \"Pop/Rock\",\n" +
            "            \"coverArt\" : \"10114\",\n" +
            "            \"size\" : 5143719,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 213,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Blondie/Parallel Lines [Bonus Tracks]/12 - Blondie - Just Go Away.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:22:49.000Z\",\n" +
            "            \"albumId\" : \"1807\",\n" +
            "            \"artistId\" : \"1146\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9898\",\n" +
            "            \"parent\" : \"9892\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Awake\",\n" +
            "            \"album\" : \"B.R.M.C.\",\n" +
            "            \"artist\" : \"Black Rebel Motorcycle Club\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 2001,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"9892\",\n" +
            "            \"size\" : 9516478,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 372,\n" +
            "            \"bitRate\" : 204,\n" +
            "            \"path\" : \"Artists/B/Black Rebel Motorcycle Club/BF6TTZ~Z/04 - Black Rebel Motorcycle Club - Awake.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T14:49:17.000Z\",\n" +
            "            \"albumId\" : \"1770\",\n" +
            "            \"artistId\" : \"1119\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10117\",\n" +
            "            \"parent\" : \"10114\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Once I Had a Love (AKA the Disco Song) [1978 Version] [Version]\",\n" +
            "            \"album\" : \"Parallel Lines [Bonus Tracks]\",\n" +
            "            \"artist\" : \"Blondie\",\n" +
            "            \"track\" : 13,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"10114\",\n" +
            "            \"size\" : 4764421,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 198,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Blondie/Parallel Lines [Bonus Tracks]/13 - Blondie - Once I Had a Love (AKA the Disco Song) [1978 Version] [Version].mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:22:26.000Z\",\n" +
            "            \"albumId\" : \"1807\",\n" +
            "            \"artistId\" : \"1146\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10720\",\n" +
            "            \"parent\" : \"10706\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Intergalactic\",\n" +
            "            \"album\" : \"Hello Nasty\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 7,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10706\",\n" +
            "            \"size\" : 5590057,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 231,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Hello Nasty [Japan]/07 - Beastie Boys - Intergalactic.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:13:21.000Z\",\n" +
            "            \"albumId\" : \"1876\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15942\",\n" +
            "            \"parent\" : \"15939\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Big Black Car\",\n" +
            "            \"album\" : \"Third/Sister Lovers\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 3,\n" +
            "            \"year\" : 2006,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"coverArt\" : \"15939\",\n" +
            "            \"size\" : 7515357,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 216,\n" +
            "            \"bitRate\" : 266,\n" +
            "            \"path\" : \"Artists/B/Big Star/Third Sister Lovers/03 - Big Black Car.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2014-07-21T10:13:49.000Z\",\n" +
            "            \"albumId\" : \"2557\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9121\",\n" +
            "            \"parent\" : \"9120\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Olerê Camará\",\n" +
            "            \"album\" : \"Brazil Classics 2: O Samba\",\n" +
            "            \"artist\" : \"Alcione\",\n" +
            "            \"track\" : 6,\n" +
            "            \"year\" : 2000,\n" +
            "            \"genre\" : \"International\",\n" +
            "            \"coverArt\" : \"9120\",\n" +
            "            \"size\" : 5945593,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 185,\n" +
            "            \"bitRate\" : 245,\n" +
            "            \"path\" : \"Artists/A/Alcione/Brazil Classics 2: O Samba/06. Olerê Camará.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-22T22:36:25.000Z\",\n" +
            "            \"albumId\" : \"156\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10758\",\n" +
            "            \"parent\" : \"10708\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Johnny Ryall\",\n" +
            "            \"album\" : \"Paul's Boutique\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 3,\n" +
            "            \"year\" : 1989,\n" +
            "            \"genre\" : \"Rap & Hip Hop\",\n" +
            "            \"coverArt\" : \"10708\",\n" +
            "            \"size\" : 2902553,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 180,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Paul's Boutique/03 Johnny Ryall.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:51.000Z\",\n" +
            "            \"albumId\" : \"1878\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15332\",\n" +
            "            \"parent\" : \"15328\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Just A Touch\",\n" +
            "            \"album\" : \"Body Music\",\n" +
            "            \"artist\" : \"AlunaGeorge\",\n" +
            "            \"track\" : 11,\n" +
            "            \"year\" : 2013,\n" +
            "            \"genre\" : \"Alternative Rock\",\n" +
            "            \"coverArt\" : \"15328\",\n" +
            "            \"size\" : 6769847,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 193,\n" +
            "            \"bitRate\" : 270,\n" +
            "            \"path\" : \"Artists/A/AlunaGeorge/Body Music/11. Just A Touch.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2013-12-26T13:34:33.000Z\",\n" +
            "            \"albumId\" : \"2520\",\n" +
            "            \"artistId\" : \"1615\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9144\",\n" +
            "            \"parent\" : \"9132\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Trouble No More\",\n" +
            "            \"album\" : \"The Allman Brothers Band\",\n" +
            "            \"artist\" : \"The Allman Brothers Band\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 1969,\n" +
            "            \"size\" : 5469787,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 228,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/A/The Allman Brothers Band/The Allman Brothers Band/4 - Trouble No More.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:15:12.000Z\",\n" +
            "            \"albumId\" : \"1705\",\n" +
            "            \"artistId\" : \"1077\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10741\",\n" +
            "            \"parent\" : \"10707\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Eugene's Lament\",\n" +
            "            \"album\" : \"Ill Communication\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 12,\n" +
            "            \"genre\" : \"misc\",\n" +
            "            \"coverArt\" : \"10707\",\n" +
            "            \"size\" : 2150336,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 132,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Ill Communication/Beastie Boys-Ill Communication-12-Eugene's Lament.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:07.000Z\",\n" +
            "            \"albumId\" : \"1877\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10658\",\n" +
            "            \"parent\" : \"10635\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Disco Club\",\n" +
            "            \"album\" : \"Monkey Business\",\n" +
            "            \"artist\" : \"The Black Eyed Peas\",\n" +
            "            \"track\" : 11,\n" +
            "            \"year\" : 2005,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"10635\",\n" +
            "            \"size\" : 5450975,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 228,\n" +
            "            \"bitRate\" : 190,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Monkey Business/11 - The Black Eyed Peas - Disco Club.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:24.000Z\",\n" +
            "            \"albumId\" : \"1866\",\n" +
            "            \"artistId\" : \"326\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9140\",\n" +
            "            \"parent\" : \"9130\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Les Brers In A Minor\",\n" +
            "            \"album\" : \"Eat A Peach\",\n" +
            "            \"artist\" : \"The Allman Brothers Band\",\n" +
            "            \"track\" : 2,\n" +
            "            \"year\" : 1972,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"9130\",\n" +
            "            \"size\" : 13268575,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 547,\n" +
            "            \"bitRate\" : 193,\n" +
            "            \"path\" : \"Artists/A/The Allman Brothers Band/Eat A Peach/02 - The Allman Brothers Band - Les Brers In A Minor.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:15:30.000Z\",\n" +
            "            \"albumId\" : \"1704\",\n" +
            "            \"artistId\" : \"1077\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10119\",\n" +
            "            \"parent\" : \"10114\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"11:59\",\n" +
            "            \"album\" : \"Parallel Lines [Bonus Tracks]\",\n" +
            "            \"artist\" : \"Blondie\",\n" +
            "            \"track\" : 7,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"10114\",\n" +
            "            \"size\" : 4827742,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 200,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Blondie/Parallel Lines [Bonus Tracks]/07 - Blondie - 11-59.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:22:57.000Z\",\n" +
            "            \"albumId\" : \"1807\",\n" +
            "            \"artistId\" : \"1146\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9902\",\n" +
            "            \"parent\" : \"9892\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"As Sure as the Sun\",\n" +
            "            \"album\" : \"B.R.M.C.\",\n" +
            "            \"artist\" : \"Black Rebel Motorcycle Club\",\n" +
            "            \"track\" : 6,\n" +
            "            \"year\" : 2001,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"9892\",\n" +
            "            \"size\" : 10676440,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 447,\n" +
            "            \"bitRate\" : 190,\n" +
            "            \"path\" : \"Artists/B/Black Rebel Motorcycle Club/BF6TTZ~Z/06 - Black Rebel Motorcycle Club - As Sure as the Sun.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T14:49:13.000Z\",\n" +
            "            \"albumId\" : \"1770\",\n" +
            "            \"artistId\" : \"1119\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10126\",\n" +
            "            \"parent\" : \"10114\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Fade Away and Radiate\",\n" +
            "            \"album\" : \"Parallel Lines [Bonus Tracks]\",\n" +
            "            \"artist\" : \"Blondie\",\n" +
            "            \"track\" : 4,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"10114\",\n" +
            "            \"size\" : 5830812,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 242,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Blondie/Parallel Lines [Bonus Tracks]/04 - Blondie - Fade Away and Radiate.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:22:40.000Z\",\n" +
            "            \"albumId\" : \"1807\",\n" +
            "            \"artistId\" : \"1146\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10760\",\n" +
            "            \"parent\" : \"10708\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"What Comes Around\",\n" +
            "            \"album\" : \"Paul's Boutique\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 12,\n" +
            "            \"year\" : 1989,\n" +
            "            \"genre\" : \"Rap & Hip Hop\",\n" +
            "            \"coverArt\" : \"10708\",\n" +
            "            \"size\" : 3024734,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 187,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Paul's Boutique/12 What Comes Around.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:53.000Z\",\n" +
            "            \"albumId\" : \"1878\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10739\",\n" +
            "            \"parent\" : \"10707\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"The Update\",\n" +
            "            \"album\" : \"Ill Communication\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 9,\n" +
            "            \"genre\" : \"misc\",\n" +
            "            \"coverArt\" : \"10707\",\n" +
            "            \"size\" : 3158840,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 195,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Ill Communication/Beastie Boys-Ill Communication-09-The Update.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:09.000Z\",\n" +
            "            \"albumId\" : \"1877\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9921\",\n" +
            "            \"parent\" : \"9906\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Mod Lang\",\n" +
            "            \"album\" : \"#1 Record/Radio City\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 18,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"9906\",\n" +
            "            \"size\" : 3989231,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 164,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Big Star/#1 Record-Radio City/18 - Big Star - Mod Lang.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:20:19.000Z\",\n" +
            "            \"albumId\" : \"1772\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15941\",\n" +
            "            \"parent\" : \"15939\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"You Can't Have Me\",\n" +
            "            \"album\" : \"Third/Sister Lovers\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 11,\n" +
            "            \"year\" : 2006,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"coverArt\" : \"15939\",\n" +
            "            \"size\" : 6688372,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 191,\n" +
            "            \"bitRate\" : 267,\n" +
            "            \"path\" : \"Artists/B/Big Star/Third Sister Lovers/11 - You Can't Have Me.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2014-07-21T10:14:42.000Z\",\n" +
            "            \"albumId\" : \"2557\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10763\",\n" +
            "            \"parent\" : \"10708\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Egg Man\",\n" +
            "            \"album\" : \"Paul's Boutique\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 1989,\n" +
            "            \"genre\" : \"Rap & Hip Hop\",\n" +
            "            \"coverArt\" : \"10708\",\n" +
            "            \"size\" : 2861270,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 177,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Paul's Boutique/04 Egg Man.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:13:02.000Z\",\n" +
            "            \"albumId\" : \"1878\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8815\",\n" +
            "            \"parent\" : \"8809\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Pyramid\",\n" +
            "            \"album\" : \"Pyramid\",\n" +
            "            \"artist\" : \"Cannonball Adderley Quintet\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 2006,\n" +
            "            \"genre\" : \"Jazz\",\n" +
            "            \"coverArt\" : \"8809\",\n" +
            "            \"size\" : 6542749,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 221,\n" +
            "            \"bitRate\" : 233,\n" +
            "            \"path\" : \"Artists/A/Cannonball Adderley/Cannonball Adderley Quintet/Pyramid/04. Pyramid.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-22T22:17:02.000Z\",\n" +
            "            \"albumId\" : \"1647\",\n" +
            "            \"artistId\" : \"1051\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10732\",\n" +
            "            \"parent\" : \"10707\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Ricky's Theme\",\n" +
            "            \"album\" : \"Ill Communication\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 15,\n" +
            "            \"genre\" : \"misc\",\n" +
            "            \"coverArt\" : \"10707\",\n" +
            "            \"size\" : 3609014,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 224,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Ill Communication/Beastie Boys-Ill Communication-15-Ricky's Theme.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:12:40.000Z\",\n" +
            "            \"albumId\" : \"1877\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8811\",\n" +
            "            \"parent\" : \"8809\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Oh Bess, Oh Where's My Bess\",\n" +
            "            \"album\" : \"Pyramid\",\n" +
            "            \"artist\" : \"Cannonball Adderley Quintet\",\n" +
            "            \"track\" : 8,\n" +
            "            \"year\" : 2006,\n" +
            "            \"genre\" : \"Jazz\",\n" +
            "            \"coverArt\" : \"8809\",\n" +
            "            \"size\" : 5443319,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 218,\n" +
            "            \"bitRate\" : 196,\n" +
            "            \"path\" : \"Artists/A/Cannonball Adderley/Cannonball Adderley Quintet/Pyramid/08. Oh Bess, Oh Where's My Bess.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-22T22:17:04.000Z\",\n" +
            "            \"albumId\" : \"1647\",\n" +
            "            \"artistId\" : \"1051\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9145\",\n" +
            "            \"parent\" : \"9132\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Whipping Post\",\n" +
            "            \"album\" : \"The Allman Brothers Band\",\n" +
            "            \"artist\" : \"The Allman Brothers Band\",\n" +
            "            \"track\" : 7,\n" +
            "            \"year\" : 1969,\n" +
            "            \"size\" : 7608870,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 317,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/A/The Allman Brothers Band/The Allman Brothers Band/7 - Whipping Post.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:15:09.000Z\",\n" +
            "            \"albumId\" : \"1705\",\n" +
            "            \"artistId\" : \"1077\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10115\",\n" +
            "            \"parent\" : \"9224\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Rip her to shreds\",\n" +
            "            \"album\" : \"Blondie\",\n" +
            "            \"artist\" : \"Blondie\",\n" +
            "            \"genre\" : \"0\",\n" +
            "            \"coverArt\" : \"9224\",\n" +
            "            \"size\" : 3303466,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 203,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/B/Blondie/Blondie - Rip her to shreds.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:22:18.000Z\",\n" +
            "            \"albumId\" : \"1806\",\n" +
            "            \"artistId\" : \"1146\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9908\",\n" +
            "            \"parent\" : \"9906\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"In the Street\",\n" +
            "            \"album\" : \"#1 Record/Radio City\",\n" +
            "            \"artist\" : \"Big Star\",\n" +
            "            \"track\" : 3,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"9906\",\n" +
            "            \"size\" : 4232881,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 175,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Big Star/#1 Record-Radio City/03 - Big Star - In the Street.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:20:13.000Z\",\n" +
            "            \"albumId\" : \"1772\",\n" +
            "            \"artistId\" : \"1121\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8735\",\n" +
            "            \"parent\" : \"8632\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Bring It Back\",\n" +
            "            \"album\" : \"Two Worlds\",\n" +
            "            \"artist\" : \"ATB\",\n" +
            "            \"track\" : 8,\n" +
            "            \"coverArt\" : \"8632\",\n" +
            "            \"size\" : 7014948,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 292,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/A/ATB/ATB - Two Worlds - CD1 - 08 - Bring It Back(1).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:32:55.000Z\",\n" +
            "            \"albumId\" : \"1627\",\n" +
            "            \"artistId\" : \"1037\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10714\",\n" +
            "            \"parent\" : \"10706\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Sneakin' Out the Hospital\",\n" +
            "            \"album\" : \"Hello Nasty\",\n" +
            "            \"artist\" : \"Beastie Boys\",\n" +
            "            \"track\" : 8,\n" +
            "            \"genre\" : \"Rap/R&B\",\n" +
            "            \"coverArt\" : \"10706\",\n" +
            "            \"size\" : 3994080,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 165,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/B/Beastie Boys/Hello Nasty [Japan]/08 - Beastie Boys - Sneakin' Out The Hospital.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:13:32.000Z\",\n" +
            "            \"albumId\" : \"1876\",\n" +
            "            \"artistId\" : \"1191\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10580\",\n" +
            "            \"parent\" : \"10576\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"The Globe\",\n" +
            "            \"album\" : \"The Globe\",\n" +
            "            \"artist\" : \"Big Audio Dynamite II\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 1991,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"size\" : 14590702,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 364,\n" +
            "            \"bitRate\" : 320,\n" +
            "            \"path\" : \"Artists/B/Big Audio Dynamite II/The Globe/The Globe.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-23T14:53:22.000Z\",\n" +
            "            \"albumId\" : \"1860\",\n" +
            "            \"artistId\" : \"1181\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8804\",\n" +
            "            \"parent\" : \"8794\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Mercy, Mercy, Mercy\",\n" +
            "            \"album\" : \"Mercy, Mercy, Mercy! Live at \\\"The Club\\\"\",\n" +
            "            \"artist\" : \"Cannonball Adderley\",\n" +
            "            \"track\" : 3,\n" +
            "            \"year\" : 1966,\n" +
            "            \"genre\" : \"8\",\n" +
            "            \"coverArt\" : \"8794\",\n" +
            "            \"size\" : 7817006,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 310,\n" +
            "            \"bitRate\" : 200,\n" +
            "            \"path\" : \"Artists/A/Cannonball Adderley/Mercy Mercy Mercy Live at The Club/03 - Mercy, Mercy, Mercy.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:16:38.000Z\",\n" +
            "            \"albumId\" : \"1646\",\n" +
            "            \"artistId\" : \"1050\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10583\",\n" +
            "            \"parent\" : \"10576\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"When The Time Comes\",\n" +
            "            \"album\" : \"The Globe\",\n" +
            "            \"artist\" : \"Big Audio Dynamite II\",\n" +
            "            \"track\" : 9,\n" +
            "            \"year\" : 1991,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"size\" : 15713999,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 392,\n" +
            "            \"bitRate\" : 320,\n" +
            "            \"path\" : \"Artists/B/Big Audio Dynamite II/The Globe/When The Time Comes.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2012-12-23T14:53:07.000Z\",\n" +
            "            \"albumId\" : \"1860\",\n" +
            "            \"artistId\" : \"1181\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9134\",\n" +
            "            \"parent\" : \"9130\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Trouble No More\",\n" +
            "            \"album\" : \"Eat A Peach\",\n" +
            "            \"artist\" : \"The Allman Brothers Band\",\n" +
            "            \"track\" : 6,\n" +
            "            \"year\" : 1972,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"9130\",\n" +
            "            \"size\" : 6193964,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 225,\n" +
            "            \"bitRate\" : 219,\n" +
            "            \"path\" : \"Artists/A/The Allman Brothers Band/Eat A Peach/06 - The Allman Brothers Band - Trouble No More.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T22:16:08.000Z\",\n" +
            "            \"albumId\" : \"1704\",\n" +
            "            \"artistId\" : \"1077\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10663\",\n" +
            "            \"parent\" : \"10635\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Gone Going\",\n" +
            "            \"album\" : \"Monkey Business\",\n" +
            "            \"artist\" : \"The Black Eyed Peas\",\n" +
            "            \"track\" : 9,\n" +
            "            \"year\" : 2005,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"10635\",\n" +
            "            \"size\" : 4796585,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 193,\n" +
            "            \"bitRate\" : 196,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Monkey Business/09 - The Black Eyed Peas - Gone Going.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:13.000Z\",\n" +
            "            \"albumId\" : \"1866\",\n" +
            "            \"artistId\" : \"326\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10653\",\n" +
            "            \"parent\" : \"10635\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Bebot\",\n" +
            "            \"album\" : \"Monkey Business\",\n" +
            "            \"artist\" : \"The Black Eyed Peas\",\n" +
            "            \"track\" : 12,\n" +
            "            \"year\" : 2005,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"coverArt\" : \"10635\",\n" +
            "            \"size\" : 5180744,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 210,\n" +
            "            \"bitRate\" : 196,\n" +
            "            \"path\" : \"Artists/B/Black Eyed Peas/Monkey Business/12 - The Black Eyed Peas - Bebot.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T15:32:40.000Z\",\n" +
            "            \"albumId\" : \"1866\",\n" +
            "            \"artistId\" : \"326\",\n" +
            "            \"type\" : \"music\"\n" +
            "         } ]\n" +
            "      }\n" +
            "   }\n" +
            "}";

        @Override
        public void doCommand(Command command) throws IOException, JSONException 
        {
            JSONObject json;
            if ( command.getParams().isEmpty() )
                json = new JSONObject( missingParams );
            else if (command.getParams().get("id").equals("0"))
                json = new JSONObject( playlistNotFound );
            else
                json = new JSONObject( response );
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
