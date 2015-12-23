package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.*;
import com.orangesoft.subsonic.system.Connection;
import org.junit.Test;
import static org.junit.Assert.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Copyright 2015 Orangesoft
 */
public class GetIndexesTest
{
    @Test
    public void getIndexes() throws Exception
    {
        Connection connection = new FakeConnection();
        GetIndexes getIndexes = new GetIndexes(connection);
        getIndexes.execute();
        assert(getIndexes.getStatus());
        Indexes indexes = getIndexes.getIndexes();
        assertEquals(1450710068935L, indexes.getLastModified());
        assertEquals("The El La Los Las Le Les", indexes.getIgnoredArticles());
        List<Shortcut> shortcuts = indexes.getShortcuts();
        Shortcut shortcut = shortcuts.get(0);
        assertEquals(1, shortcut.getId());
        assertEquals("Incoming", shortcut.getName());
        List<Index> indices = indexes.getList();
        Index index = indices.get(0);
        assertEquals("A", index.getName());
        List<Artist> artists = index.getArtists();
        Artist artist = artists.get(0);
        assertEquals(16578, artist.getId());
        assertEquals("agile", artist.getName());
        List<Child> children = indexes.getChildren();
        Child child = children.get(0);
        assertEquals(16515, child.getId());
        assertEquals(false, child.isDir());
        assertEquals("gong", child.getTitle());
        assertEquals(128021, child.getSize());
        assertEquals("audio/mpeg", child.getContentType());
        assertEquals("mp3", child.getSuffix());
        assertEquals(5, child.getDuration());
        assertEquals(192, child.getBitRate());
        assertEquals("gong.mp3", child.getPath());
        assertEquals(false, child.isVideo());
        assertEquals("2015-02-02T15:14:24.000Z", child.getCreated());
        assertEquals("music", child.getType());
        child = children.get(1);
        assertEquals(706, child.getOriginalWidth());
        assertEquals(480, child.getOriginalHeight());
    }

    private class FakeConnection implements Connection
    {
        final static String response = "{\n" +
                "   \"subsonic-response\" : {\n" +
                "      \"status\" : \"ok\",\n" +
                "      \"version\" : \"1.13.0\",\n" +
                "      \"indexes\" : {\n" +
                "         \"lastModified\" : 1450710068935,\n" +
                "         \"ignoredArticles\" : \"The El La Los Las Le Les\",\n" +
                "         \"shortcut\" : [ {\n" +
                "            \"id\" : \"1\",\n" +
                "            \"name\" : \"Incoming\"\n" +
                "         } ],\n" +
                "         \"index\" : [ {\n" +
                "            \"name\" : \"A\",\n" +
                "            \"artist\" : [ {\n" +
                "               \"id\" : \"16578\",\n" +
                "               \"name\" : \"agile\"\n" +
                "            }, {\n" +
                "               \"id\" : \"6\",\n" +
                "               \"name\" : \"Artists\"\n" +
                "            }, {\n" +
                "               \"id\" : \"5\",\n" +
                "               \"name\" : \"audiobooks\"\n" +
                "            }, {\n" +
                "               \"id\" : \"14717\",\n" +
                "               \"name\" : \"ava\"\n" +
                "            } ]\n" +
                "         }, {\n" +
                "            \"name\" : \"C\",\n" +
                "            \"artist\" : [ {\n" +
                "               \"id\" : \"3\",\n" +
                "               \"name\" : \"christmas\"\n" +
                "            } ]\n" +
                "         }, {\n" +
                "            \"name\" : \"F\",\n" +
                "            \"artist\" : [ {\n" +
                "               \"id\" : \"16579\",\n" +
                "               \"name\" : \"fitness\"\n" +
                "            } ]\n" +
                "         }, {\n" +
                "            \"name\" : \"H\",\n" +
                "            \"artist\" : [ {\n" +
                "               \"id\" : \"16581\",\n" +
                "               \"name\" : \"home\"\n" +
                "            } ]\n" +
                "         }, {\n" +
                "            \"name\" : \"K\",\n" +
                "            \"artist\" : [ {\n" +
                "               \"id\" : \"15581\",\n" +
                "               \"name\" : \"Kid's Ukelele Course\"\n" +
                "            }, {\n" +
                "               \"id\" : \"15814\",\n" +
                "               \"name\" : \"Kristen Anderson-Lopez, Robert Lopez & Christophe Beck\"\n" +
                "            } ]\n" +
                "         }, {\n" +
                "            \"name\" : \"M\",\n" +
                "            \"artist\" : [ {\n" +
                "               \"id\" : \"16577\",\n" +
                "               \"name\" : \"movies\"\n" +
                "            } ]\n" +
                "         }, {\n" +
                "            \"name\" : \"T\",\n" +
                "            \"artist\" : [ {\n" +
                "               \"id\" : \"16580\",\n" +
                "               \"name\" : \"tv\"\n" +
                "            } ]\n" +
                "         } ],\n" +
                "         \"child\" : [ {\n" +
                "            \"id\" : \"16515\",\n" +
                "            \"isDir\" : false,\n" +
                "            \"title\" : \"gong\",\n" +
                "            \"size\" : 128021,\n" +
                "            \"contentType\" : \"audio/mpeg\",\n" +
                "            \"suffix\" : \"mp3\",\n" +
                "            \"duration\" : 5,\n" +
                "            \"bitRate\" : 192,\n" +
                "            \"path\" : \"gong.mp3\",\n" +
                "            \"isVideo\" : false,\n" +
                "            \"created\" : \"2015-02-02T15:14:24.000Z\",\n" +
                "            \"type\" : \"music\"\n" +
                "         }, {\n" +
                "            \"id\" : \"16582\",\n" +
                "            \"isDir\" : false,\n" +
                "            \"title\" : \"Pilobolus - Last Dance\",\n" +
                "            \"size\" : 1035697995,\n" +
                "            \"contentType\" : \"video/mp4\",\n" +
                "            \"suffix\" : \"mp4\",\n" +
                "            \"transcodedContentType\" : \"video/x-flv\",\n" +
                "            \"transcodedSuffix\" : \"flv\",\n" +
                "            \"duration\" : 5008,\n" +
                "            \"bitRate\" : 1654,\n" +
                "            \"path\" : \"Pilobolus - Last Dance.mp4\",\n" +
                "            \"isVideo\" : true,\n" +
                "            \"created\" : \"2011-04-23T14:26:17.000Z\",\n" +
                "            \"type\" : \"video\",\n" +
                "            \"originalWidth\" : 706,\n" +
                "            \"originalHeight\" : 480\n" +
                "         } ]\n" +
                "      }\n" +
                "   }\n" +
                "}";

        @Override
        public void doCommand(Command command) throws IOException, JSONException
        {
            JSONObject json = new JSONObject(response);
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {

        }
    }
}
