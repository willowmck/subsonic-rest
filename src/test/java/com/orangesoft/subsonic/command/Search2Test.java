package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Album;
import com.orangesoft.subsonic.Artist;
import com.orangesoft.subsonic.SearchResult2;
import com.orangesoft.subsonic.Song;
import com.orangesoft.subsonic.system.Connection;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class Search2Test 
{
    Connection connection;
    Map<String, String> params;
    Search2 search2;
    
    @Before
    public void setup()
    {
        connection = new FakeConnection();
        params = new HashMap<>();
        search2 = new Search2(connection, params);
    }
    
    @Test
    public void doSearch2WithoutParameters()
    {
        search2.execute();
        assert(search2.getStatus());
    }
    
    @Test
    public void queryForMA()
    {
        params.put("query", "ma");
        search2.execute();
        assert(search2.getStatus());
        SearchResult2 searchResult2 = search2.getSearchResult();
        
        List<Artist> artists = searchResult2.getArtists();
        Artist artist = artists.get(0);
        assertEquals("14662", artist.getId());
        assertEquals("Betty MacDonald", artist.getName());
        
        List<Album> albums = searchResult2.getAlbums();
        Album album = albums.get(0);
        assertEquals("523", album.getId());
        album = albums.get(1);
        assertEquals(475, album.getParent());
        album = albums.get(2);
        assertEquals(true, album.isDir());
        album = albums.get(3);
        assertEquals("Orchestral Manoeuvres In The Dark", album.getTitle());
        album = albums.get(4);
        assertEquals("Osvaldo Golijov: La Pasion Segun San Marcos (1 of 2)", album.getAlbumName());
        album = albums.get(5);
        assertEquals("Marvin Gaye", album.getArtist());
        album = albums.get(6);
        assertEquals(-1, album.getYear());
        album = albums.get(7);
        assertEquals("Classical", album.getGenre());
        album = albums.get(8);
        assertEquals("8962", album.getCoverArt());
        album = albums.get(9);
        assertEquals("2014-10-06T20:00:45.000Z", album.getCreated());
        
        List<Song> songs = searchResult2.getSongs();
        Song song = songs.get(0);
        assertEquals("183", song.getId());
        song = songs.get(1);
        assertEquals(25, song.getParent());
        song = songs.get(2);
        assert(!song.isDir());
        song = songs.get(3);
        assertEquals("La marea", song.getTitle());
        song = songs.get(4);
        assertEquals("Tin Machine", song.getAlbum());
        song = songs.get(5);
        assertEquals("Big Mama Thornton", song.getArtist());
        song = songs.get(6);
        assertEquals(2, song.getTrack());
        song = songs.get(7);
        assertEquals(1992, song.getYear());
        song = songs.get(8);
        assertEquals("Other", song.getGenre());
        song = songs.get(9);
        assertEquals("4179", song.getCoverArt());
        song = songs.get(10);
        assertEquals(5776668, song.getSize());
        song = songs.get(11);
        assertEquals("audio/mpeg", song.getContentType());
        song = songs.get(12);
        assertEquals("mp3", song.getSuffix());
        song = songs.get(13);
        assertEquals(120, song.getDuration());
        song = songs.get(14);
        assertEquals(210, song.getBitRate());
        song = songs.get(15);
        assertEquals("Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/14. 3 Pieces, Op. 49_ No. 2. Prelude in F major.mp3",
                song.getPath());
        song = songs.get(16);
        assert(!song.isVideo());
        song = songs.get(17);
        assertEquals(6, song.getDiscNumber());
        song = songs.get(18);
        assertEquals("2013-01-01T15:18:18.000Z", song.getCreated());
        song = songs.get(19);
        assertEquals("1602", song.getAlbumId());
        assertEquals("1022", song.getArtistId());
        assertEquals("music", song.getType());
    }

    private static class FakeConnection implements Connection 
    {
        final static String emptyResponse = "{\n" +
            "   \"subsonic-response\" : {\n" +
            "      \"status\" : \"ok\",\n" +
            "      \"version\" : \"1.13.0\",\n" +
            "      \"searchResult2\" : {\n" +
            "      }\n" +
            "   }\n" +
            "}";
        
        final static String maResponse = "{\n" +
            "   \"subsonic-response\" : {\n" +
            "      \"status\" : \"ok\",\n" +
            "      \"version\" : \"1.13.0\",\n" +
            "      \"searchResult2\" : {\n" +
            "         \"artist\" : [ {\n" +
            "            \"id\" : \"14662\",\n" +
            "            \"name\" : \"Betty MacDonald\"\n" +
            "         }, {\n" +
            "            \"id\" : \"860\",\n" +
            "            \"name\" : \"Martinho Da Silva\"\n" +
            "         }, {\n" +
            "            \"id\" : \"1616\",\n" +
            "            \"name\" : \"Mario Castelnuovo-Tedesco\"\n" +
            "         }, {\n" +
            "            \"id\" : \"1680\",\n" +
            "            \"name\" : \"Captain Beefheart & His Magic Band\"\n" +
            "         }, {\n" +
            "            \"id\" : \"1697\",\n" +
            "            \"name\" : \"Marshall Chapman\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15219\",\n" +
            "            \"name\" : \"Manu Chao\"\n" +
            "         }, {\n" +
            "            \"id\" : \"2611\",\n" +
            "            \"name\" : \"Tin Machine\"\n" +
            "         }, {\n" +
            "            \"id\" : \"2620\",\n" +
            "            \"name\" : \"Mayo Thompson\"\n" +
            "         }, {\n" +
            "            \"id\" : \"3537\",\n" +
            "            \"name\" : \"Manuel de Falla\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15238\",\n" +
            "            \"name\" : \"Fleetwood Mac\"\n" +
            "         }, {\n" +
            "            \"id\" : \"4485\",\n" +
            "            \"name\" : \"Maria Guinand\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15204\",\n" +
            "            \"name\" : \"Matt Eakle\"\n" +
            "         }, {\n" +
            "            \"id\" : \"7275\",\n" +
            "            \"name\" : \"Spiritualized Electric Mainline\"\n" +
            "         }, {\n" +
            "            \"id\" : \"7319\",\n" +
            "            \"name\" : \"Matthew Sweet\"\n" +
            "         }, {\n" +
            "            \"id\" : \"7368\",\n" +
            "            \"name\" : \"The Sound Magics\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8407\",\n" +
            "            \"name\" : \"Maria Lettberg\"\n" +
            "         }, {\n" +
            "            \"id\" : \"7385\",\n" +
            "            \"name\" : \"Marta Sebestyen\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15767\",\n" +
            "            \"name\" : \"Soft Machine\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8623\",\n" +
            "            \"name\" : \"Augie March\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8655\",\n" +
            "            \"name\" : \"Maryanne Amacher\"\n" +
            "         } ],\n" +
            "         \"album\" : [ {\n" +
            "            \"id\" : \"523\",\n" +
            "            \"parent\" : \"475\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Mark Kozelek\",\n" +
            "            \"album\" : \"Mark Kozelek\",\n" +
            "            \"artist\" : \"Mark Kozelek\",\n" +
            "            \"created\" : \"2012-12-26T13:12:57.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"551\",\n" +
            "            \"parent\" : \"475\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Kingsbury Manx\",\n" +
            "            \"album\" : \"Kingsbury Manx\",\n" +
            "            \"artist\" : \"Kingsbury Manx\",\n" +
            "            \"created\" : \"2007-11-02T19:18:04.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"2797\",\n" +
            "            \"parent\" : \"2611\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Tin Machine\",\n" +
            "            \"album\" : \"Tin Machine\",\n" +
            "            \"artist\" : \"Tin MacHine\",\n" +
            "            \"year\" : 2003,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"coverArt\" : \"2797\",\n" +
            "            \"created\" : \"2013-07-31T12:38:08.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"3190\",\n" +
            "            \"parent\" : \"481\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Orchestral Manoeuvres In The Dark\",\n" +
            "            \"album\" : \"Orchestral Manoeuvres In The Dark\",\n" +
            "            \"artist\" : \"Orchestral Manoeuvres In The D\",\n" +
            "            \"genre\" : \"0\",\n" +
            "            \"created\" : \"2012-12-28T12:20:16.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"4487\",\n" +
            "            \"parent\" : \"4485\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Osvaldo Golijov_ La Pasion Segun San Marcos (1 of 2)\",\n" +
            "            \"album\" : \"Osvaldo Golijov: La Pasion Segun San Marcos (1 of 2)\",\n" +
            "            \"artist\" : \"Maria Guinand\",\n" +
            "            \"year\" : 1999,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"4487\",\n" +
            "            \"created\" : \"2013-07-31T12:40:42.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"4331\",\n" +
            "            \"parent\" : \"486\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Marvin Gaye\",\n" +
            "            \"album\" : \"Marvin Gaye\",\n" +
            "            \"artist\" : \"Marvin Gaye\",\n" +
            "            \"created\" : \"2012-12-25T15:08:01.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"5258\",\n" +
            "            \"parent\" : \"488\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Mark Eitzel\",\n" +
            "            \"album\" : \"Mark Eitzel\",\n" +
            "            \"artist\" : \"Mark Eitzel\",\n" +
            "            \"created\" : \"2012-12-24T01:01:07.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"7537\",\n" +
            "            \"parent\" : \"7244\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Schubert, F._ Mass No. 6 in E Flat Major, D. 950 _ Mozart, W.A._ Vesperae Solennes De Confessore, K. 339\",\n" +
            "            \"album\" : \"Schubert, F.: Mass No. 6 in E Flat Major, D. 950 / Mozart, W.A.: Vesperae Solennes De Confessore, K. 339\",\n" +
            "            \"artist\" : \"Charles Mackerras\",\n" +
            "            \"year\" : 2009,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"7537\",\n" +
            "            \"created\" : \"2013-07-31T12:40:01.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8962\",\n" +
            "            \"parent\" : \"8655\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Sound Characters (Making the Third Ear)\",\n" +
            "            \"album\" : \"Sound Characters (Making the Third Ear)\",\n" +
            "            \"artist\" : \"Maryanne Amacher\",\n" +
            "            \"year\" : 1999,\n" +
            "            \"genre\" : \"Avantgarde\",\n" +
            "            \"coverArt\" : \"8962\",\n" +
            "            \"created\" : \"2013-07-31T12:39:46.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"16132\",\n" +
            "            \"parent\" : \"16130\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"The Year of Magical Drinking\",\n" +
            "            \"album\" : \"The Year Of Magical Drinking\",\n" +
            "            \"artist\" : \"Apex Manor\",\n" +
            "            \"year\" : 2011,\n" +
            "            \"genre\" : \"Alternative Rock\",\n" +
            "            \"coverArt\" : \"16132\",\n" +
            "            \"created\" : \"2014-10-06T20:00:45.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"9252\",\n" +
            "            \"parent\" : \"496\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Matson Belle\",\n" +
            "            \"album\" : \"Matson Belle\",\n" +
            "            \"artist\" : \"Matson Belle\",\n" +
            "            \"genre\" : \"0\",\n" +
            "            \"coverArt\" : \"9252\",\n" +
            "            \"created\" : \"2012-12-23T15:24:19.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10940\",\n" +
            "            \"parent\" : \"497\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Craig Marshall\",\n" +
            "            \"album\" : \"Craig Marshall\",\n" +
            "            \"artist\" : \"Craig Marshall\",\n" +
            "            \"created\" : \"2012-12-27T02:18:47.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10944\",\n" +
            "            \"parent\" : \"497\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Montana Mandolin Society\",\n" +
            "            \"album\" : \"Montana Mandolin Society\",\n" +
            "            \"artist\" : \"Montana Mandolin Society\",\n" +
            "            \"genre\" : \"0\",\n" +
            "            \"created\" : \"2007-11-02T19:38:34.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10950\",\n" +
            "            \"parent\" : \"497\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Magnetic Fields\",\n" +
            "            \"album\" : \"Magnetic Fields\",\n" +
            "            \"artist\" : \"Magnetic Fields\",\n" +
            "            \"created\" : \"2012-12-27T01:58:37.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"11159\",\n" +
            "            \"parent\" : \"10954\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Love Her Madly\",\n" +
            "            \"album\" : \"Love Her Madly\",\n" +
            "            \"artist\" : \"Ray Manzarek\",\n" +
            "            \"year\" : 2007,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"coverArt\" : \"11159\",\n" +
            "            \"created\" : \"2013-07-31T12:38:38.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"11171\",\n" +
            "            \"parent\" : \"10955\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Magnapop\",\n" +
            "            \"album\" : \"Magnapop\",\n" +
            "            \"artist\" : \"Magnapop\",\n" +
            "            \"year\" : 1992,\n" +
            "            \"genre\" : \"17\",\n" +
            "            \"created\" : \"2012-12-27T02:13:42.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10959\",\n" +
            "            \"parent\" : \"497\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Aimee Mann\",\n" +
            "            \"album\" : \"Aimee Mann\",\n" +
            "            \"artist\" : \"Aimee Mann\",\n" +
            "            \"genre\" : \"0\",\n" +
            "            \"created\" : \"2012-12-27T02:00:49.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"11188\",\n" +
            "            \"parent\" : \"10963\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Matchmaker\",\n" +
            "            \"album\" : \"Matchmaker\",\n" +
            "            \"artist\" : \"Magneto\",\n" +
            "            \"year\" : 1992,\n" +
            "            \"genre\" : \"Indie\",\n" +
            "            \"created\" : \"2012-12-27T02:16:53.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"10967\",\n" +
            "            \"parent\" : \"497\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Mates of State\",\n" +
            "            \"album\" : \"Mates of State\",\n" +
            "            \"artist\" : \"Mates Of State\",\n" +
            "            \"created\" : \"2007-11-02T19:38:35.000Z\"\n" +
            "         }, {\n" +
            "            \"id\" : \"11024\",\n" +
            "            \"parent\" : \"497\",\n" +
            "            \"isDir\" : true,\n" +
            "            \"title\" : \"Kirsty MacColl\",\n" +
            "            \"album\" : \"Kirsty MacColl\",\n" +
            "            \"artist\" : \"Kirsty MacColl\",\n" +
            "            \"genre\" : \"0\",\n" +
            "            \"created\" : \"2012-12-27T02:00:52.000Z\"\n" +
            "         } ],\n" +
            "         \"song\" : [ {\n" +
            "            \"id\" : \"183\",\n" +
            "            \"parent\" : \"23\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Rudolf, the Red-Nosed Reindeer Mambo\",\n" +
            "            \"album\" : \"Ultra-Lounge: Christmas Cocktails\",\n" +
            "            \"artist\" : \"Billy May\",\n" +
            "            \"track\" : 1,\n" +
            "            \"genre\" : \"98\",\n" +
            "            \"coverArt\" : \"23\",\n" +
            "            \"size\" : 2592015,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 160,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"christmas/Ultra-Lounge Christmas Cocktails/Billy May - Rudolph, The Red-Nosed Reindeer Mambo.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T21:51:04.000Z\",\n" +
            "            \"albumId\" : \"11\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"206\",\n" +
            "            \"parent\" : \"25\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Marys Boy Child\",\n" +
            "            \"album\" : \"Merry Christmas CD2\",\n" +
            "            \"artist\" : \"Matt Monro\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 2001,\n" +
            "            \"genre\" : \"Christmas Music\",\n" +
            "            \"size\" : 3775562,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 199,\n" +
            "            \"bitRate\" : 151,\n" +
            "            \"path\" : \"christmas/Merry Christmas CD2/04 - Marys Boy Child.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-22T21:51:03.000Z\",\n" +
            "            \"albumId\" : \"12\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"1356\",\n" +
            "            \"parent\" : \"1317\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Cello Concerto in B minor, B. 191 (Op. 104): Adagio ma non troppo\",\n" +
            "            \"album\" : \"Dvorák: Concerto For Cello & Orchestra In B/Klid/Rondo For Cello & Orchestra\",\n" +
            "            \"artist\" : \"Ma, Yo-Yo\",\n" +
            "            \"track\" : 2,\n" +
            "            \"genre\" : \"Concerto\",\n" +
            "            \"coverArt\" : \"1317\",\n" +
            "            \"size\" : 18556708,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 769,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/D/Antonin Dvorak/Dvorák- Concerto For Cello & Orchestra In B-Klid-Rondo For Cello & Orchestra/02 - Ma, Yo-Yo - Cello Concerto in B minor, B. 191 (Op. 104)- Adagio ma non troppo.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-23T22:44:20.000Z\",\n" +
            "            \"albumId\" : \"194\",\n" +
            "            \"artistId\" : \"140\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15225\",\n" +
            "            \"parent\" : \"15221\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"La marea\",\n" +
            "            \"album\" : \"Proxima Estacion: Esperanza\",\n" +
            "            \"artist\" : \"Manu Chao\",\n" +
            "            \"track\" : 14,\n" +
            "            \"year\" : 2008,\n" +
            "            \"genre\" : \"Pop\",\n" +
            "            \"coverArt\" : \"15221\",\n" +
            "            \"size\" : 4713854,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 136,\n" +
            "            \"bitRate\" : 256,\n" +
            "            \"path\" : \"Artists/C/Manu Chao/Proxima Estacion_ Esperanza/14. La marea.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2013-10-16T11:14:33.000Z\",\n" +
            "            \"albumId\" : \"2513\",\n" +
            "            \"artistId\" : \"1609\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"2806\",\n" +
            "            \"parent\" : \"2797\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Tin Machine (1999 Digital Remaster)\",\n" +
            "            \"album\" : \"Tin Machine\",\n" +
            "            \"artist\" : \"Tin MacHine\",\n" +
            "            \"track\" : 2,\n" +
            "            \"year\" : 2003,\n" +
            "            \"genre\" : \"Rock\",\n" +
            "            \"coverArt\" : \"2797\",\n" +
            "            \"size\" : 7194822,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 215,\n" +
            "            \"bitRate\" : 262,\n" +
            "            \"path\" : \"Artists/T/Tin Machine/Tin Machine/02 - Tin Machine (1999 Digital Remaster).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2013-05-24T12:42:26.000Z\",\n" +
            "            \"albumId\" : \"440\",\n" +
            "            \"artistId\" : \"310\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"2650\",\n" +
            "            \"parent\" : \"2651\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"They Call Me Big Mama\",\n" +
            "            \"album\" : \"The Original Hound Dog\",\n" +
            "            \"artist\" : \"Big Mama Thornton\",\n" +
            "            \"track\" : 9,\n" +
            "            \"year\" : 1990,\n" +
            "            \"genre\" : \"0\",\n" +
            "            \"coverArt\" : \"2651\",\n" +
            "            \"size\" : 1976239,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 122,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/T/Big Mama Thornton/big mama thornton - they call me big mama.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2013-01-01T22:12:36.000Z\",\n" +
            "            \"albumId\" : \"504\",\n" +
            "            \"artistId\" : \"349\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"3323\",\n" +
            "            \"parent\" : \"3171\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Truck Driving Man\",\n" +
            "            \"album\" : \"Jimmy Martin's 20 Greatest Hits\",\n" +
            "            \"artist\" : \"Jimmy Martin\",\n" +
            "            \"track\" : 2,\n" +
            "            \"coverArt\" : \"3171\",\n" +
            "            \"size\" : 2429554,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 151,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/O/Buck Owens/Buck Owens - Truck Driving Man (1).mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-28T12:25:26.000Z\",\n" +
            "            \"albumId\" : \"571\",\n" +
            "            \"artistId\" : \"396\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"4155\",\n" +
            "            \"parent\" : \"4122\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"I’m Not the Man\",\n" +
            "            \"album\" : \"Our Time in Eden\",\n" +
            "            \"artist\" : \"10,000 Maniacs\",\n" +
            "            \"track\" : 13,\n" +
            "            \"year\" : 1992,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"4122\",\n" +
            "            \"size\" : 3635219,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 226,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/Other/10,000 maniacs/10,000 Maniacs - I'm Not The Man.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-28T13:18:54.000Z\",\n" +
            "            \"albumId\" : \"755\",\n" +
            "            \"artistId\" : \"512\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"4176\",\n" +
            "            \"parent\" : \"4122\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"What’s the Matter Here?\",\n" +
            "            \"album\" : \"MTV Unplugged\",\n" +
            "            \"artist\" : \"10,000 Maniacs\",\n" +
            "            \"track\" : 7,\n" +
            "            \"year\" : 1993,\n" +
            "            \"genre\" : \"Other\",\n" +
            "            \"coverArt\" : \"4122\",\n" +
            "            \"size\" : 5808962,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 290,\n" +
            "            \"bitRate\" : 160,\n" +
            "            \"path\" : \"Artists/Other/10,000 maniacs/10,000 Maniacs - MTV Unplugged - What's The Matter Here.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-28T13:19:50.000Z\",\n" +
            "            \"albumId\" : \"753\",\n" +
            "            \"artistId\" : \"512\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"4183\",\n" +
            "            \"parent\" : \"4179\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"What's the Matter Here?\",\n" +
            "            \"album\" : \"In My Tribe\",\n" +
            "            \"artist\" : \"10,000 Maniacs\",\n" +
            "            \"track\" : 1,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"4179\",\n" +
            "            \"size\" : 4672407,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 291,\n" +
            "            \"bitRate\" : 128,\n" +
            "            \"path\" : \"Artists/Other/10,000 maniacs/In My Tribe/10,000 Maniacs - What's the Matter Here-.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2012-12-28T13:19:17.000Z\",\n" +
            "            \"albumId\" : \"758\",\n" +
            "            \"artistId\" : \"512\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"15217\",\n" +
            "            \"parent\" : \"15206\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"A Simple Matter\",\n" +
            "            \"album\" : \"Flute Jazz\",\n" +
            "            \"artist\" : \"Matt Eakle\",\n" +
            "            \"track\" : 4,\n" +
            "            \"year\" : 1998,\n" +
            "            \"genre\" : \"Jazz\",\n" +
            "            \"coverArt\" : \"15206\",\n" +
            "            \"size\" : 5776668,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 197,\n" +
            "            \"bitRate\" : 227,\n" +
            "            \"path\" : \"Artists/E/Matt Eakle/Flute Jazz/04. A Simple Matter.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 1,\n" +
            "            \"created\" : \"2013-10-15T14:59:24.000Z\",\n" +
            "            \"albumId\" : \"2512\",\n" +
            "            \"artistId\" : \"1608\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"7812\",\n" +
            "            \"parent\" : \"7813\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Electric Mainline\",\n" +
            "            \"album\" : \"Pure Phase\",\n" +
            "            \"artist\" : \"Spiritualized Electric Mainline\",\n" +
            "            \"track\" : 9,\n" +
            "            \"genre\" : \"Rock/Pop\",\n" +
            "            \"coverArt\" : \"7813\",\n" +
            "            \"size\" : 11116750,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 460,\n" +
            "            \"bitRate\" : 192,\n" +
            "            \"path\" : \"Artists/S/Spiritualized Electric Mainline/Pure Phase/09 - Spiritualized Electric Mainline - Electric Mainline.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"created\" : \"2013-01-01T15:32:10.000Z\",\n" +
            "            \"albumId\" : \"1495\",\n" +
            "            \"artistId\" : \"945\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8408\",\n" +
            "            \"parent\" : \"8409\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"6 Preludes, Op. 13: No. 3 in G major\",\n" +
            "            \"album\" : \"Scriabin: The Solo Piano Works\",\n" +
            "            \"artist\" : \"Maria Lettberg\",\n" +
            "            \"track\" : 27,\n" +
            "            \"year\" : 2010,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"8409\",\n" +
            "            \"size\" : 2114159,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 75,\n" +
            "            \"bitRate\" : 213,\n" +
            "            \"path\" : \"Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/27. 6 Preludes, Op. 13_ No. 3 in G major.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 3,\n" +
            "            \"created\" : \"2013-01-01T15:21:02.000Z\",\n" +
            "            \"albumId\" : \"1602\",\n" +
            "            \"artistId\" : \"1022\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8410\",\n" +
            "            \"parent\" : \"8409\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"9 Mazurkas, Op. 25 : No. 3 in E minor\",\n" +
            "            \"album\" : \"Scriabin: The Solo Piano Works\",\n" +
            "            \"artist\" : \"Maria Lettberg\",\n" +
            "            \"track\" : 14,\n" +
            "            \"year\" : 2010,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"8409\",\n" +
            "            \"size\" : 2960858,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 120,\n" +
            "            \"bitRate\" : 190,\n" +
            "            \"path\" : \"Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/14. 9 Mazurkas, Op. 25 _ No. 3 in E minor.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 6,\n" +
            "            \"created\" : \"2013-01-01T15:17:08.000Z\",\n" +
            "            \"albumId\" : \"1602\",\n" +
            "            \"artistId\" : \"1022\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8411\",\n" +
            "            \"parent\" : \"8409\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Piano Sonata No. 7, Op. 64, \\\"White Mass\\\"\",\n" +
            "            \"album\" : \"Scriabin: The Solo Piano Works\",\n" +
            "            \"artist\" : \"Maria Lettberg\",\n" +
            "            \"track\" : 3,\n" +
            "            \"year\" : 2010,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"8409\",\n" +
            "            \"size\" : 19771731,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 748,\n" +
            "            \"bitRate\" : 210,\n" +
            "            \"path\" : \"Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/03. Piano Sonata No. 7, Op. 64, _White Mass_.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 2,\n" +
            "            \"created\" : \"2013-01-01T15:18:42.000Z\",\n" +
            "            \"albumId\" : \"1602\",\n" +
            "            \"artistId\" : \"1022\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8413\",\n" +
            "            \"parent\" : \"8409\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"3 Pieces, Op. 49: No. 2. Prelude in F major\",\n" +
            "            \"album\" : \"Scriabin: The Solo Piano Works\",\n" +
            "            \"artist\" : \"Maria Lettberg\",\n" +
            "            \"track\" : 14,\n" +
            "            \"year\" : 2010,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"8409\",\n" +
            "            \"size\" : 1338966,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 49,\n" +
            "            \"bitRate\" : 200,\n" +
            "            \"path\" : \"Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/14. 3 Pieces, Op. 49_ No. 2. Prelude in F major.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 8,\n" +
            "            \"created\" : \"2013-01-01T15:16:12.000Z\",\n" +
            "            \"albumId\" : \"1602\",\n" +
            "            \"artistId\" : \"1022\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8415\",\n" +
            "            \"parent\" : \"8409\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"Valse in A flat major, Op. 38\",\n" +
            "            \"album\" : \"Scriabin: The Solo Piano Works\",\n" +
            "            \"artist\" : \"Maria Lettberg\",\n" +
            "            \"track\" : 21,\n" +
            "            \"year\" : 2010,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"8409\",\n" +
            "            \"size\" : 8280006,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 296,\n" +
            "            \"bitRate\" : 221,\n" +
            "            \"path\" : \"Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/21. Valse in A flat major, Op. 38.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 6,\n" +
            "            \"created\" : \"2013-01-01T15:19:48.000Z\",\n" +
            "            \"albumId\" : \"1602\",\n" +
            "            \"artistId\" : \"1022\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8416\",\n" +
            "            \"parent\" : \"8409\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"10 Mazurkas, Op. 3 : No. 5 in D sharp minor\",\n" +
            "            \"album\" : \"Scriabin: The Solo Piano Works\",\n" +
            "            \"artist\" : \"Maria Lettberg\",\n" +
            "            \"track\" : 6,\n" +
            "            \"year\" : 2010,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"8409\",\n" +
            "            \"size\" : 6444185,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 273,\n" +
            "            \"bitRate\" : 185,\n" +
            "            \"path\" : \"Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/06. 10 Mazurkas, Op. 3 _ No. 5 in D sharp minor.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 6,\n" +
            "            \"created\" : \"2013-01-01T15:20:29.000Z\",\n" +
            "            \"albumId\" : \"1602\",\n" +
            "            \"artistId\" : \"1022\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8417\",\n" +
            "            \"parent\" : \"8409\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"4 Preludes, Op. 33: No. 2 in F sharp major\",\n" +
            "            \"album\" : \"Scriabin: The Solo Piano Works\",\n" +
            "            \"artist\" : \"Maria Lettberg\",\n" +
            "            \"track\" : 19,\n" +
            "            \"year\" : 2010,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"8409\",\n" +
            "            \"size\" : 2043669,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 75,\n" +
            "            \"bitRate\" : 207,\n" +
            "            \"path\" : \"Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/19. 4 Preludes, Op. 33_ No. 2 in F sharp major.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 4,\n" +
            "            \"created\" : \"2013-01-01T15:18:18.000Z\",\n" +
            "            \"albumId\" : \"1602\",\n" +
            "            \"artistId\" : \"1022\",\n" +
            "            \"type\" : \"music\"\n" +
            "         }, {\n" +
            "            \"id\" : \"8418\",\n" +
            "            \"parent\" : \"8409\",\n" +
            "            \"isDir\" : false,\n" +
            "            \"title\" : \"2 Poemes, Op. 44: No. 2 in C major\",\n" +
            "            \"album\" : \"Scriabin: The Solo Piano Works\",\n" +
            "            \"artist\" : \"Maria Lettberg\",\n" +
            "            \"track\" : 15,\n" +
            "            \"year\" : 2010,\n" +
            "            \"genre\" : \"Classical\",\n" +
            "            \"coverArt\" : \"8409\",\n" +
            "            \"size\" : 1627462,\n" +
            "            \"contentType\" : \"audio/mpeg\",\n" +
            "            \"suffix\" : \"mp3\",\n" +
            "            \"duration\" : 63,\n" +
            "            \"bitRate\" : 194,\n" +
            "            \"path\" : \"Artists/S/Alexander Scriabin/Maria Lettberg/Scriabin_ The Solo Piano Works/15. 2 Poemes, Op. 44_ No. 2 in C major.mp3\",\n" +
            "            \"isVideo\" : false,\n" +
            "            \"discNumber\" : 7,\n" +
            "            \"created\" : \"2013-01-01T15:18:03.000Z\",\n" +
            "            \"albumId\" : \"1602\",\n" +
            "            \"artistId\" : \"1022\",\n" +
            "            \"type\" : \"music\"\n" +
            "         } ]\n" +
            "      }\n" +
            "   }\n" +
            "}";
        
        @Override
        public void doCommand(Command command) throws IOException, JSONException 
        {
            Map<String, String> params = command.getParams();
            JSONObject json;
            
            if (params.isEmpty())
                json = new JSONObject(emptyResponse);
            else
                json = new JSONObject(maResponse);
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
