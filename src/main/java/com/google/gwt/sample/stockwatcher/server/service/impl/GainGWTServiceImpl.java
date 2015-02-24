package com.google.gwt.sample.stockwatcher.server.service.impl;

import com.google.gwt.sample.stockwatcher.shared.services.gain.GainGWTService;
import com.google.gwt.sample.stockwatcher.shared.models.GainGWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by Maxime on 13/01/2015.
 */
public class GainGWTServiceImpl extends RemoteServiceServlet implements GainGWTService {
    
    @Override
    public GainGWT getGain() {
        return null;
    }
}
