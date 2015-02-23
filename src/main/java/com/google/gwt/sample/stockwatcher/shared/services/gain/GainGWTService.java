package com.google.gwt.sample.stockwatcher.shared.services.gain;

import com.google.gwt.sample.stockwatcher.shared.models.GainGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by Brian on 13/01/2015.
 */
@RemoteServiceRelativePath("GainService")
public interface GainGWTService extends RemoteService{
    GainGWT getGain();
}
