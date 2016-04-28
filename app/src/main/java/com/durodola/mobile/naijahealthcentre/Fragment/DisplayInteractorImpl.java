package com.durodola.mobile.naijahealthcentre.Fragment;

/**
 * Created by mobile on 2016-04-28.
 */
public class DisplayInteractorImpl implements DisplayInteractor {
    @Override
    public void login(final OnLoginFinishedListener listener) {
        listener.onSuccess();
    }

    @Override
    public void lognHIv(OnLoginFinishedListener listener) {
        listener.onSuccessHiv();
    }

    @Override
    public void loginTuber(OnLoginFinishedListener listener) {
        listener.onSuccessTuber();
    }

    @Override
    public void loginHospital(OnLoginFinishedListener listener) {
        listener.onSuccessHospital();
    }
}
