package com.rakasettya.daggerstarter.apps.barcode;

import static com.google.ads.AdRequest.LOGTAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.blikoon.qrcodescanner.QrCodeActivity;
import com.rakasettya.daggerstarter.R;
import com.rakasettya.daggerstarter.dagger.base.BaseActivity;
import com.rakasettya.daggerstarter.apps.main.MainActivity;
import com.rakasettya.daggerstarter.apps.signin.SignInActivity;
import javax.inject.Inject;


public class BarcodeActivity extends BaseActivity implements BarcodeView {

  @Inject
  BarcodePresenterImpl presenter;
  @BindView(R.id.cv_barcode_scan)
  CardView cvBarcodeScan;
  @BindView(R.id.cv_barcode_search)
  CardView cvBarcodeSearch;
  private static final int REQUEST_CODE_QR_SCAN = 101;

  public static void start(Context context) {
    Intent starter = new Intent(context, BarcodeActivity.class);
    context.startActivity(starter);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_barcode_menu);
    ButterKnife.bind(this);
    iniUI();
  }

  private void iniUI() {

  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(resultCode != Activity.RESULT_OK)
    {
      Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
      if(data==null)
        return;
      //Getting the passed result
      String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
      if( result!=null)
      {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Scan Error");
        alertDialog.setMessage("QR Code could not be scanned");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              }
            });
        alertDialog.show();
      }
      return;
    }
    if(requestCode == REQUEST_CODE_QR_SCAN)
    {
      if(data==null)
        return;
      //Getting the passed result
      String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
      Log.d(LOGTAG,"Have scan result in your app activity :"+ result);
      AlertDialog alertDialog = new AlertDialog.Builder(this).create();
      alertDialog.setTitle("Scan result");
      alertDialog.setMessage(result);
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
          });
      alertDialog.show();

    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @OnClick({R.id.cv_barcode_scan, R.id.cv_barcode_search})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.cv_barcode_scan:
        presenter.OpenBarcodeScan(this);
        break;
      case R.id.cv_barcode_search:
        break;
    }
  }

  @Override
  public void gotoBarcodeScan() {
    Intent i = new Intent(this,QrCodeActivity.class);
    startActivityForResult( i,REQUEST_CODE_QR_SCAN);
  }
}
