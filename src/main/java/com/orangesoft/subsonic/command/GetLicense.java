package com.orangesoft.subsonic.command;

import com.orangesoft.subsonic.License;
import com.orangesoft.subsonic.system.Connection;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

/**
 * Copyright 2015 Orangesoft
 */
public class GetLicense extends Command
{
    final static String COMMAND = "getLicense.view";
    final static String LICENSE = "license";

    private License license;

    public GetLicense(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public String getCommand()
    {
        return COMMAND;
    }

    @Override
    public void parseObjects(JSONObject json) throws JSONException
    {
        JSONObject licenseObject = json.getJSONObject(LICENSE);
        license = new License(licenseObject);
    }

    public License getLicense()
    {
        return license;
    }
}
