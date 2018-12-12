package com.rakasettya.daggerstarter.apps.barcode;

import android.content.Context;
import com.rakasettya.daggerstarter.data.model.UserModel;
import com.rakasettya.daggerstarter.data.remote.FirebaseAuthService;
import com.google.firebase.firestore.DocumentSnapshot;

public class BarcodePresenterImpl {
    BarcodeView barcodeView;


    public BarcodePresenterImpl(BarcodeView barcodeView) {
        this.barcodeView = barcodeView;
    }

    public void OpenBarcodeScan(Context context){
        barcodeView.gotoBarcodeScan();
    }
}
