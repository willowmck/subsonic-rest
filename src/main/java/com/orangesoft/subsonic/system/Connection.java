package com.orangesoft.subsonic.system;

import com.orangesoft.subsonic.command.Command;
import com.orangesoft.subsonic.command.StreamCommand;
import us.monoid.json.JSONException;
import java.io.IOException;

/*
 * Copyright 2015 Orangesoft
 */
public interface Connection 
{
    public void doCommand(Command command) throws IOException, JSONException;
    public void doStreamCommand(StreamCommand command) throws IOException, JSONException;
}
