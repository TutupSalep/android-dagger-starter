package com.rakasettya.daggerstarter.dagger.module;

import com.rakasettya.daggerstarter.apps.barcode.BarcodeActivity;
import com.rakasettya.daggerstarter.apps.barcode.BarcodePresenterImpl;
import com.rakasettya.daggerstarter.apps.barcode.BarcodeView;
import dagger.Module;
import dagger.Provides;

@Module
public class BarcodeModule {

  @Provides
  BarcodeView barcodeView(BarcodeActivity barcodeActivity) {
    return barcodeActivity;
  }

  @Provides
  BarcodePresenterImpl provideBarcodePresenterImpl(BarcodeView barcodeView) {
    return new BarcodePresenterImpl(barcodeView);
  }


}
