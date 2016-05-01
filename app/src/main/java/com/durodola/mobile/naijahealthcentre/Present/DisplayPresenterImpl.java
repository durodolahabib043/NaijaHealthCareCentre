package com.durodola.mobile.naijahealthcentre.Present;

import com.durodola.mobile.naijahealthcentre.Fragment.DisplayInteractor;
import com.durodola.mobile.naijahealthcentre.Fragment.DisplayInteractorImpl;
import com.durodola.mobile.naijahealthcentre.Fragment.DisplayView;
import com.durodola.mobile.naijahealthcentre.Fragment.EbolaFragment;

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
        }
        displayInteractor.login(this);
    }

    @Override
    public void switchFragHiv() {
        if (displayView != null) {
        }
        displayInteractor.lognHIv(this);


    }

    @Override
    public void switchFragTuber() {
        if (displayView != null) {
        }
        displayInteractor.loginTuber(this);
    }


    @Override
    public void onSuccess() {
        displayView.switchFragmentEbola();
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
