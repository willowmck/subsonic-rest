package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.InternetRadioStation;
import com.orangesoft.subsonic.system.Connection;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2015 Orangesoft
 */
public class GetInternetRadioStations extends Command
{
    final static String COMMAND = "getInternetRadioStations.view";
    final static String INTERNET_RADIO_STATIONS = "internetRadioStations";
    final static String INTERNET_RADIO_STATION = "internetRadioStation";

    private List<InternetRadioStation> internetRadioStations;

    public GetInternetRadioStations(Connection connection)
    {
        this.connection = connection;
        internetRadioStations = new ArrayList<>();
    }

    @Override
    public String getCommand()
    {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException
    {
        JSONObject allStations = json.getJSONObject(INTERNET_RADIO_STATIONS);
        JSONArray stations = allStations.getJSONArray(INTERNET_RADIO_STATION);
        for (int i=0; i < stations.length(); i++)
            internetRadioStations.add(new InternetRadioStation((JSONObject)stations.get(i)));
    }

    List<InternetRadioStation> getList()
    {
        return internetRadioStations;
    }
}
