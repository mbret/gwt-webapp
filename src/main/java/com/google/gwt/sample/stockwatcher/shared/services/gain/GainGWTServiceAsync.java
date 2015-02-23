package com.google.gwt.sample.stockwatcher.shared.services.gain;

import com.google.gwt.sample.stockwatcher.shared.models.GainGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GainGWTServiceAsync {
    void getGain(AsyncCallback<GainGWT> async);
}
