package com.durodola.mobile.naijahealthcentre.Fragment;

/**
 * Created by mobile on 2016-04-28.
 */
public interface DisplayInteractor {

    interface OnLoginFinishedListener {

        void onSuccess();
        void onSuccessHiv();
        void onSuccessTuber();
        void onSuccessHospital();
    }

    void login(OnLoginFinishedListener listener);
    void lognHIv(OnLoginFinishedListener listener);
    void loginTuber(OnLoginFinishedListener listener);
    void loginHospital(OnLoginFinishedListener listener);
}
