package com.orangesoft.subsonic.data;

import java.io.InputStream;
import us.monoid.web.BinaryResource;

/**
 * Coypright 2015 Orangesoft
 */
public class BinaryResourceContainer
{
    private final BinaryResource resource;
    
    public BinaryResourceContainer(BinaryResource resource)
    {
        this.resource = resource;
    }
    
    public boolean isAudioContent()
    {
        return resource.http().getContentType().contains("mpeg");
    }
    
    public boolean isNullSource()
    {
        return resource.http().getContentType().contains("octet");
    }
    
    public InputStream stream()
    {
        return resource.stream();
    }
}
