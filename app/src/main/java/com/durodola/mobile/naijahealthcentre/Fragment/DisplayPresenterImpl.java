package com.durodola.mobile.naijahealthcentre.Fragment;

import android.util.Log;

/**
 * Created by mobile on 2016-04-28.
 */
public class DisplayPresenterImpl implements DisPresenter, DisplayInteractor.OnLoginFinishedListener {
    EbolaFragment ebolaFragment;
    private DisplayView displayView;
    private DisplayInteractor displayInteractor;

    public DisplayPresenterImpl(DisplayView displayView) {
        this.displayView = displayView;
        //  this.displayInteractor = new DisplayPresenterImpl();
        this.displayInteractor = new DisplayInteractorImpl();
    }

    @Override
    public void switchFrag() {

        if (displayView != null) {
            // loginView.showProgress();
            Log.e("nullvali", "nulllvali");
        }
        Log.e("vali", "vali");
        displayInteractor.login(this);
        // displayView.switchFragment();
    }

    @Override
    public void switchFragHiv() {
        if (displayView != null) {
            // loginView.showProgress();
            Log.e("nullvali", "nulllvali");
        }
        Log.e("vali", "vali");
        displayInteractor.lognHIv(this);
        // displayView.switchFragment();

    }

    @Override
    public void switchFragTuber() {
        if (displayView != null) {
            // loginView.showProgress();
            Log.e("nullvali", "nulllvali");
        }
        Log.e("vali", "vali");
        displayInteractor.loginTuber(this);
        // displayView.switchFragment();
    }


    @Override
    public void onSuccess() {
        Log.e("succes", "succes");
        // ebolaFragment = EbolaFragment.newInstance();
        displayView.switchFragmentEbola();
        //  displayInteractor.login(this);
    }

    @Override
    public void onSuccessHiv() {
        displayView.switchFragmentHiv();
    }

    @Override
    public void onSuccessTuber() {
        displayView.switchFragmentTuberculosis();
    }

    @Override
    public void onSuccessHospital() {
        displayView.switchFragment();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void switchFragHospital() {
        if (displayView != null) {

        }

        displayInteractor.loginHospital(this);

    }

}
