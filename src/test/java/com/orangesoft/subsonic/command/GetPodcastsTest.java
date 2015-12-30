package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.Channel;
import com.orangesoft.subsonic.Episode;
import com.orangesoft.subsonic.system.Connection;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 *
 * Copyright 2015 Orangesoft
 */
public class GetPodcastsTest 
{
 
    @Test
    public void getAllPodcasts()
    {
        Connection connection = new FakeConnection();
        GetPodcasts getPodcasts = new GetPodcasts(connection);
        getPodcasts.execute();
        assert(getPodcasts.getStatus());
        assert(!getPodcasts.getChannels().isEmpty());
        Channel channel = getPodcasts.getChannels().get(0);
        assertEquals("2", channel.getId());
        assertEquals("http://www.npr.org/templates/rss/podcast.php?id=510298", 
                channel.getUrl());
        assertEquals("TED Radio Hour", channel.getTitle());
        assertEquals("The TED Radio Hour is a journey through fascinating ideas: astonishing inventions, fresh approaches to old problems, new ways to think and create. Based on Talks given by riveting speakers on the world-renowned TED stage, each show is centered on a common theme – such as the source of happiness, crowd-sourcing innovation, power shifts, or inexplicable connections. The TED Radio Hour is hosted by Guy Raz, and is a co-production of NPR & TED. Follow the show @TEDRadioHour.", 
                channel.getDescription());
        assertEquals("http://media.npr.org/assets/img/2015/03/18/ted_sq-3426270a541795b78233a698dd7965d407545cf3.jpg?s=1400",
                channel.getOriginalImageUrl());
        assertEquals("completed", channel.getStatus());
        assert(!channel.getEpisodes().isEmpty());
        Episode episode = channel.getEpisodes().get(0);
        assertEquals("0", episode.getId());
        episode = channel.getEpisodes().get(1);
        assert(!episode.isDir());
        episode = channel.getEpisodes().get(2);
        assertEquals("Adaptation", episode.getTitle());
        episode = channel.getEpisodes().get(3);
        assertEquals(2, episode.getChannelId());
        episode = channel.getEpisodes().get(4);
        assertEquals("Some of our most powerful feelings — stress, depression, despair — are the hardest to understand. This hour, TED speakers challenge assumptions about emotion, disquiet and the essence of well-being.",
                episode.getDescription());
        episode = channel.getEpisodes().get(5);
        assertEquals("skipped", episode.getStatus());
        episode = channel.getEpisodes().get(6);
        assertEquals("2015-10-23T04:28:00.000Z", episode.getPublishDate());
    }
    
    private class FakeConnection implements Connection
    {
        final static String channelResponse = "{\n" +
"   \"subsonic-response\" : {\n" +
"      \"status\" : \"ok\",\n" +
"      \"version\" : \"1.13.0\",\n" +
"      \"podcasts\" : {\n" +
"         \"channel\" : [ {\n" +
"            \"id\" : \"2\",\n" +
"            \"url\" : \"http://www.npr.org/templates/rss/podcast.php?id=510298\",\n" +
"            \"title\" : \"TED Radio Hour\",\n" +
"            \"description\" : \"The TED Radio Hour is a journey through fascinating ideas: astonishing inventions, fresh approaches to old problems, new ways to think and create. Based on Talks given by riveting speakers on the world-renowned TED stage, each show is centered on a common theme – such as the source of happiness, crowd-sourcing innovation, power shifts, or inexplicable connections. The TED Radio Hour is hosted by Guy Raz, and is a co-production of NPR & TED. Follow the show @TEDRadioHour.\",\n" +
"            \"coverArt\" : \"pod-2\",\n" +
"            \"originalImageUrl\" : \"http://media.npr.org/assets/img/2015/03/18/ted_sq-3426270a541795b78233a698dd7965d407545cf3.jpg?s=1400\",\n" +
"            \"status\" : \"completed\",\n" +
"            \"episode\" : [ {\n" +
"               \"id\" : \"0\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Just A Little Nicer\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Compassion is a universal virtue, but is it innate or taught? Have we lost touch with it? Can we be better at it? In this hour, TED speakers explore compassion: its roots, its meaning and its future. (Original broadcast date: December 19, 2014)\",\n" +
"               \"status\" : \"error\",\n" +
"               \"publishDate\" : \"2015-12-04T05:39:10.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"1\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Disruptive Leadership\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Is leadership only reserved for the extraordinary few? Who has what it takes to disrupt the status quo? In this hour, TED speakers share ideas about what it takes to forge a new path. (Original broadcast date: January 17, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-11-27T05:24:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"2\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Adaptation\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Humans adapt to physical and creative challenges in remarkable ways. How do we do it, and what happens when we can't? In this episode, TED speakers share inspiring stories about our capacity to adapt.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-11-20T05:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"3\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Quiet\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"In this episode, we explore ways to find quiet in our busy lives. How can we step back and make time to reflect in an increasingly distracted and hurried world? (Original broadcast date: November 21, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-11-13T05:18:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"4\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Headspace\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Some of our most powerful feelings — stress, depression, despair — are the hardest to understand. This hour, TED speakers challenge assumptions about emotion, disquiet and the essence of well-being.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-11-06T05:28:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"5\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Playing With Perceptions\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Where do stereotypes come from? Why do some perceptions persist, and is there any truth or value to the assumptions we make? In this hour, TED speakers examine the consequences of stereotypes. (Original broadcast date: November 14, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-10-30T04:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"6\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Open Source World\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"The era of open source has led to countless innovations. When does it work and when is it chaos? In this episode, TED speakers explore how open source is changing how we build, collaborate and govern.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-10-23T04:28:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"7\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Simply Happy\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"We all want to find happiness, but it seems elusive. Can we learn more about happiness through science? Or are there simpler ways to achieve it? In this hour, finding happiness may be simpler than you think. (Original broadcast date: February 14, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-10-16T04:23:45.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"8\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"How It All Began\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"In this hour, TED speakers explore our origins as a species — who we are, where we come from, where we're headed — and how we're connected to everything that came before us. (Original broadcast date: October 24, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-10-09T04:28:46.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"9\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"The Meaning Of Work\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Love it or hate it, most of us have to work for a living. So how can we make work more meaningful? This hour, TED speakers explore our values and motivations when it comes to the workplace.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-10-02T04:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"10\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"The Source Of Creativity\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"We want to be creative, but channeling our creative impulses is no small feat. Is creativity something we are born with, or can we learn it? In this hour TED speakers examine the mystery of creativity. (Original broadcast date: October 3, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-09-25T04:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"11\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Screen Time - Part II\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"When we go online, we present a digital version of ourselves. How do we transform when we interact inside our screens? In this episode, TED speakers explore the expanding role of our \\\"second selves\\\".\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-09-18T04:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"12\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Screen Time - Part I\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"It's normal for us to be constantly glued to our screens. So how are they changing us, and how will they shape our future? This hour, TED speakers explore our ambivalent relationships with our screens.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-09-11T04:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"13\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Unstoppable Learning\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Host Guy Raz talks with TED speakers about the different ways babies and children learn — from the womb, to the playground, to the web. (Original broadcast date: May 3, 2013)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-09-04T04:38:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"14\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Everything Is Connected\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"In this hour, TED speakers explain how everything in nature is connected, and how we can restore its delicate balance. (Original broadcast date: September 27, 2013)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-08-28T04:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"15\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Peering Into Space\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"This hour, we'll hear from TED speakers who share an infectious sense of wonder and curiosity about our place in the universe and what lies beyond our skies. (Original broadcast date: March 8, 2013)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-08-21T04:38:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"16\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Amateur Hour\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"What does it take to survive, even thrive, when you're a complete amateur? This hour, TED speakers share stories of plunging - or being plunged - into new situations and emerging as experts.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-08-14T04:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"17\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"The Hackers\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"In this hour, TED speakers dare to hack the brain, the climate, and the animal kingdom in hopes of creating a better world. (Original broadcast date: August 9, 2013)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-08-07T04:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"18\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Fighting Cancer\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Cancer is merciless. It can strike any time, spread without warning, and return unannounced. In this hour, TED speakers reframe not only how we treat cancer but how we can live with it and survive it.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-07-31T04:33:36.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"19\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Transformation\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Are we simply the sum of our experiences? Or can we choose our own path? In this hour, TED speakers share stories of undergoing remarkable transformations despite extraordinary challenges. (Original broadcast date: September 19, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-07-24T04:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"20\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Finite\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"In a world with limited resources, can we find ways to salvage what's disappearing? Can we innovate our way out of a finite landscape? This hour, TED speakers explore ideas about living with less.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-07-17T04:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"21\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Why We Collaborate\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"In this hour, TED speakers unravel ideas behind the mystery of mass collaborations that build a better world. (Original broadcast date: July 12, 2013)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-07-10T04:28:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"22\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Growing Up\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"What makes us who we are? How do parents mold children into who they are? In this hour, TED speakers reflect on how our upbringing shapes us. (Original broadcast date: August 1, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-07-03T04:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"23\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Do We Need Humans?\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"In this episode, TED speakers consider the promises and perils of our relationship with technology. (Original broadcast date: March 15, 2013)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-06-26T04:34:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"24\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Shifting Time\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"We live our lives by the calendar and the clock, but time is also an abstraction, even an illusion. In this hour, TED speakers explore how our sense of time changes depending on who and where we are.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-06-19T04:43:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"25\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"From Curiosity To Discovery\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Why do some people spend years trying to answer a single question, or even risk their lives to discover something new? In this hour, TED speakers describe how curiosity leads to unexpected places. (Original broadcast date: September 12, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-06-12T04:18:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"26\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"The Act of Listening\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Listening — to loved ones, strangers, faraway places — is an act of generosity and a source of discovery. In this episode, TED speakers describe how we change when we listen deeply.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-06-05T04:41:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"27\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Animals and Us\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Our relationship with animals is complicated: we love and fear them; hunt, consume, and protect them. In this hour, TED speakers explore what happens when humans and animals interact. (Original broadcast date: September 5, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-05-29T04:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"28\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"The Fountain of Youth\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Aging is inevitable. We can slow it down a little, but could we ever bring it to a grinding halt? In this episode, TED speakers explore how we all might live longer and even better lives.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-05-22T04:38:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"29\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Trust and Consequences\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Our lives are fueled by trust: in our loved ones, our colleagues, our leaders. But how do we cultivate it, and restore if it's lost? In this episode, TED speakers explore our relationship with trust.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-05-15T04:21:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"30\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Identities\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Is identity assigned at birth? Shaped by circumstance? Or is it something we choose? This hour, TED speakers describe their journeys to answer the question: who am I? (Original broadcast date: October 11, 2013)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-05-08T04:28:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"31\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Champions\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"From Little League to the Olympics, athletic mastery plays a major role in our sense of achievement. This hour, TED speakers explore the minds and bodies of champions who achieve extraordinary physical feats. (Original broadcast date: July 18, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-05-01T04:38:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"32\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Getting Organized\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Even the most seemingly chaotic systems are organized. On this episode, TED speakers explore the inner architecture of living systems, from ant colonies to corporations to social movements.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-04-24T04:18:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"33\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Maslow's Human Needs\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Humans need food, sleep, safety, love, purpose. Psychologist Abraham Maslow ordered our needs into a hierarchy. This week, TED speakers explore that spectrum of need, from primal to profound.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-04-17T04:43:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"34\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Framing The Story\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Stories ignite our imagination, let us leap over cultural walls and cross the barriers of time. In this hour, TED speakers explore the art of storytelling — and how good stories have the power to transform our perceptions of the world. (Original broadcast date: June 7, 2013)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-04-10T04:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"35\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Why We Lie\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Let's face it: people lie. We lie to each other and to ourselves. Is there a deeper reason why we do it? In this episode, TED speakers deconstruct the hard truths of deception. (Original broadcast date: June 20, 2014)\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-04-03T04:28:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"36\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Press Play\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Does something serious happen when we play? In this episode, TED speakers describe how all forms of amusement — tossing a ball to video games — can make us smarter, saner and more collaborative. Comedian Charlie Todd and his group Improv Everywhere choreograph bizarre, hilarious and unexpected public scenes, creating whimsical opportunities for total strangers to play together. Dr. Stuart Brown says humor, games, roughhousing and fantasy are more than just fun; humans are hard-wired to play. He came to this conclusion after conducting some somber research about the stark childhoods of murderers. Primatologist Isabel Behncke explains how bonobo apes learn by constantly playing. She says play isn't frivolous; it appears to be a critical way to solve problems and avoid conflict. When video game researcher Jane McGonigal was bedridden after a concussion, she gave herself a prescription: play a game. She says games helped her get better; and for many of us, virtual games can improve our real lives.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-03-27T04:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"37\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"What Is Original? (R)\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Even the most original ideas are essentially remixes. When is copying flattery, when is it thievery, and when is it sheer genius? In this hour, TED speakers explore how sampling, borrowing, and riffing make all of us innovators. Sampling music isn't about \\\"hijacking nostalgia wholesale,\\\" says DJ Mark Ronson. It's about inserting yourself into the narrative of a song while also pushing that story forward. Filmmaker Kirby Ferguson says nothing is original and that our most celebrated creators steal ideas — and transform them into something new. Clothing designs aren't protected by copyright --and the industry benefits by being more innovative, says Johanna Blakley. People often credit their ideas to individual \\\"Eureka!\\\" moments. But writer Steven Johnson shows how history tells a different story.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-03-20T04:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"38\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Spoken And Unspoken (R)\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"We communicate with each other in all sorts of ways, spoken and unspoken. But how did the origins of language influence action, and can words actually change human behavior, even alter the course of history? In this hour, TED speakers reflect on how our words and methods of communication affect us, more than you might expect. Linguist John McWhorter says that texting has come of age with such speed and force that it's created an entirely new language within a generation. Biologist Mark Pagel believes our complex language system is a piece of \\\"social technology\\\", simply created to help us get things done. Teacher Phuc Tran tells a personal story of how being caught in a world between the subjunctive and indicative tense — yes, grammar — helped him find his identity. Etymologist Mark Forsyth shares the surprising backstory of the word \\\"president.\\\" Social psychologist Amy Cuddy explains how \\\"power posing\\\" can affect our brains, and might even have an impact on our success.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-03-13T04:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"39\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Solve For X\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Math intimidates a lot of us, but it can deliver surprising answers to life's most pressing questions. In this episode, TED speakers discuss the elegant simplicity, and giddy complexity, of solving for X. Writer Randall Munroe doesn't love math, but has made a career out of solving equations. By answering outlandish hypotheticals, he uses numbers as a playground for the imagination. Polymath Terry Moore wondered why \\\"X\\\" is the universal unknown in Algebra. He dove into the history of numbers to come up with an unexpected answer. Percussionist Clayton Cameron dissects the mathematics of improvisational jazz, discovering how numerical patterns make him a better musician. Entrepreneur and artist Kevin Slavin shows how algorithms can reshape finance, culture and physical environments, with potentially harmful consequences. Mathematician Hannah Fry says math can help you find love. Using mathematical models, she explains how to find an ideal mate and the secret to maintaining a healthy relationship.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-03-06T05:33:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"40\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Success (R)\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Success has become synonymous with financial wealth, influence and status. But can we define success in another way — one that welcomes a broader range of accomplishment? It may not be as obvious as you think. In this hour, TED speakers share ideas for what makes us successful. Life coach Tony Robbins describes why failure should not be an option. Psychologist Angela Lee Duckworth says \\\"grit\\\", not IQ, is the new predictor of success. Mike Rowe encourages us not to follow our passion. Ron Gutman shares some compelling research on the hidden power of smiling. And writer Alain de Botton shares a fascinating view about the American paradigm for success and failure.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-02-27T05:30:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"41\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"The Unknown Brain\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \" The brain can seem as mysterious as a distant galaxy, but scientists are starting to map and manipulate its many regions. In this hour, TED speakers take us on a trip through the human brain. When neuroanatomist Jill Bolte-Taylor felt her brain shut down during a stroke, she was more fascinated than panicked. Even though she spent eight years recovering, she’s grateful for the stroke. Neuroscientist Suzana Herculano-Houzel turns brains into soup, so she can meticulously count the neurons, and determine why human brains are unique. Nancy Kanwisher studies the brain partly by staring at her own. She’s spent countless hours in an fMRI scanner, mapping her own brain to gain insight into what makes us human. Neuroscientist Rebecca Saxe explains how one region in  the brain focuses on other people’s thoughts. Philosopher David Chalmers asks why humans have a sense of self, a constantly-running movie full of sensation and internal chatter. He offers two ideas about the nature of consciousness.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-02-20T05:30:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"42\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"How We Love (R)\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Love is instinctive and essential. We need it to keep our species going, to survive childhood, to create bonds with other people. But what is it that brings certain people together?  In this hour, TED speakers examine the mystery of connection and relationships. Amy Webb was having no luck with online dating, until she effectively hacked the system.  Anthropologist Helen Fisher questions what happens in our brains when we're in love. Therapist Esther Perel says a good relationship draws on both security and surprise. Writer Jeffrey Kluger explores the profound life-long bond between siblings. Angela Patton tells the story of a unique father-daughter dance.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-02-13T05:30:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"43\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Seven Deadly Sins\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Seven TED speakers muse about the seven deadly sins: Psychologist Christopher Ryan says human beings are sexual omnivores, and a more nuanced understanding of fidelity may tamp down our shame about lust.  Oklahoma City Mayor Mick Cornett explains how his city sidestepped gluttony and collectively dropped one million pounds. Activist Dave Meslin says even though we’re apathetic about local politics, we’re hardly sloths. Dr. Gary Slutkin tracks violence, the destructive sibling of wrath. He says if we think of violence as a contagious disease, we can better contain it. Nick Hanauer is a rich guy with at least five houses, but is he greedy? He argues taxing the very rich and increasing the minimum wage would be good for everyone. Editor Parul Sehgal says literature would hardly exist without the “grim thrill” of envy. Jeopardy Know-it-All Ken Jennings reveals how losing to a supercomputer crushed his pride.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-02-06T05:30:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"44\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"What We Fear (R)\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Human beings have a fine-tuned sense of fear. But how do we distinguish between fear and danger? How do we decide which fears are rational and irrational? In this hour, TED speakers explore what it means to be afraid, and how we calm ourselves down — or don’t — when we’re terrified. Astronaut and retired colonel Chris Hadfield discusses how to prepare your mind for the unexpected, and the worst. Through the story of the whaleship Essex, novelist Karen Thompson Walker describes how our most vivid fears are often not the most realistic. Folk singer Joe Kowan talks about the visceral, body-hijacking experience he feels when he’s performing in front of an audience, and how a song helped him cope with stage fright. Illusionist and endurance artist David Blaine reveals how he has made a career out of fearlessly performing death-defying feats. Philosopher Stephen Cave delves into the simple question: Why are human beings afraid to die?\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-01-30T05:30:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"45\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Keeping Secrets\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Who should get to keep secrets, and who should demand to know them? In this hour, TED speakers talk about the damage secrets can do, and the shifting roles we play when we keep, or share them. \\\"Secrets...can be shocking, or silly, or soulful,\\\" says Frank Warren, the founder of PostSecret. He shares a few of the half-million secrets that strangers have sent him on postcards. Equality advocate Ash Beckham offers a fresh story about empathy and openness — and it involves pancakes. Charmian Gooch's mission is to “out” corrupt companies. She details how global corruption trackers follow the money — to some surprisingly familiar places. Journalist Glenn Greenwald makes the case for why you need to care about privacy, even if you’re “not doing anything to hide.\\\"\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-01-23T06:33:23.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"46\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Brand Over Brain (R)\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Brands help us assign value to almost everything we buy. But is there a way to know the difference between real and created value? In this episode, TED speakers explore the seductive power of brands. Filmmaker Morgan Spurlock tells the story of his quest to make a completely sponsored film — about sponsorship. Psychologist Paul Bloom argues that our beliefs about the history of an object change how we perceive it. Consultant Joseph Pine says we’ll pay more for an experience that feels “real.”  Marketer Rory Sutherland explains how rebranding changed the potato forever.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-01-16T06:13:30.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"47\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"In Search Of\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"How far would you go to find something that's just out of reach, or maybe, not even real? In this hour, TED speakers tell stories about searching for elusive sea-creatures and distant aliens. SETI astronomer Seth Shostak says we're likely to find proof of intelligent life in the universe over the next few decades. Oceanographer Edith Widder explains how innovative technology helped her capture the reclusive giant squid on video. Chef Dan Barber talks about his quest to find a delectable, yet sustainable, fish. Finally, humorist John Hodgman looks hard for aliens too, but finds love instead.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-01-09T06:23:00.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"48\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Misconceptions (R)\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"There are some truths that we believe in wholeheartedly — but what if we’re completely wrong? Once we separate fact from fiction, how do our perceptions change?  In this hour, TED speakers move beyond conventional wisdom to reveal complex realities about what we think we know to be true. Author Malcolm Gladwell reveals an alternative account of David and Goliath that flips the story on its head. Reporter Jennifer 8. Lee talks about her hunt for the actual origins of Chinese-American food. Ecologist Allan Savory counters everything conventional wisdom tells us about how grasslands lose their life to desertification. Reporter Leslie T. Chang debunks how we assume Chinese factory workers feel. Psychologist Barry Schwartz says having more options doesn't make us happier — it actually paralyzes us.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2015-01-02T06:23:22.000Z\"\n" +
"            }, {\n" +
"               \"id\" : \"49\",\n" +
"               \"isDir\" : false,\n" +
"               \"title\" : \"Memory Games (R)\",\n" +
"               \"channelId\" : \"2\",\n" +
"               \"description\" : \"Memory is malleable, dynamic and elusive. When we tap into our memories, where is the line between fact and fiction? How does our memory play tricks on us, and how can we train it to be more accurate? In this hour, TED speakers discuss how a nimble memory can improve your life, and how a frail one might ruin someone else's. Forensic psychologist Scott Fraser argues that in a criminal trial, even close-up eyewitnesses can create \\\"memories\\\" they may not have seen. Nobel laureate Daniel Kahneman explains how our experiences and our memories perceive happiness differently. Writer Joshua Foer shows how anyone can achieve amazing feats of memory, including him.\",\n" +
"               \"status\" : \"skipped\",\n" +
"               \"publishDate\" : \"2014-12-26T05:43:23.000Z\"\n" +
"            } ]\n" +
"         } ]\n" +
"      }\n" +
"   }\n" +
"}";
        
        @Override
        public void doCommand(Command command) throws IOException, JSONException 
        {
            JSONObject json = new JSONObject( channelResponse );
            JSONObject subsonicResponse = json.getJSONObject("subsonic-response");
            command.parse(subsonicResponse);   
        }

        @Override
        public void doStreamCommand(StreamCommand command) throws IOException, JSONException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
