package com.rakasettya.daggerstarter.apps.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rakasettya.daggerstarter.R;
import com.rakasettya.daggerstarter.apps.main.MainActivity;
import com.rakasettya.daggerstarter.dagger.base.BaseActivity;
import javax.inject.Inject;

public class SignInActivity extends BaseActivity implements SigninView {

  @Inject
  SignInPresenterImp presenterImp;

  String Username;
  String Password;
  @BindView(R.id.usrusr)
  EditText usrusr;
  @BindView(R.id.pswrdd)
  EditText pswrdd;
  @BindView(R.id.lin)
  TextView lin;

  public static void start(Context context) {
    Intent starter = new Intent(context, SignInActivity.class);
    starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(starter);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
    ButterKnife.bind(this);
  }

  @Override
  public void onLoadSingin(String response) {
    Log.e("onLoadSingin", "SignInActivity" + response);
  }

  @Override
  public void gotoMain() {
    MainActivity.start(this);
    this.finish();
  }

  @OnClick(R.id.lin)
  public void onViewClicked() {
    Username = usrusr.getText().toString();
    Password = pswrdd.getText().toString();
    presenterImp.singinUser(Username, Password);
  }
}
