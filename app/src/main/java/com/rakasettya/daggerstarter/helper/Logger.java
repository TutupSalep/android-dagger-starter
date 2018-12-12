package com.rakasettya.daggerstarter.helper;

import android.text.TextUtils;
import android.util.Log;

public class Logger {
  private static final boolean ENABLE_STACK_TRACE = true;
  private static final boolean ENABLE_LONG_LOG = false;

  private static String TAG = "Surveyo";
  private static boolean ENABLE = true;

  public static void init(String tag, boolean enable) {
    TAG = tag;
    ENABLE = enable;
  }

  public static void log(int mode, Throwable throwable, Object object) {
    log(mode, throwable.getMessage(), object.getClass().getSimpleName());
  }

  public static void log(int mode, String message, Object object) {
    log(mode, message, object.getClass().getSimpleName());
  }

  public static void log(int mode, String message) {
    log(mode, message, null);
  }

  private static void log(int mode, String message, String tag) {

    if (!ENABLE)
      return;

    if (message == null)
      return;

    if (tag == null)
      tag = "";

    if (message.trim().length() == 0)
      message = "TicketData Empty";

    if (ENABLE_LONG_LOG) {
      longLog(mode, tag, message);
    } else {
      if (mode == Log.DEBUG) {
        Log.d(tag, message);
      } else if (mode == Log.ERROR) {
        Log.e(tag, message);
      } else if (mode == Log.INFO) {
        Log.i(tag, message);
      } else if (mode == Log.WARN) {
        Log.w(tag, message);
      }
    }
  }

  private static void longLog(int mode, String tag, String message) {
    int maxLogSize = 1000;
    for (int i = 0; i <= message.length() / maxLogSize; i++) {
      int start = i * maxLogSize;
      int end = (i + 1) * maxLogSize;
      end = end > message.length() ? message.length() : end;

      if (mode == Log.DEBUG) {
        Log.d(tag, message.substring(start, end));
      } else if (mode == Log.ERROR) {
        Log.e(tag, message.substring(start, end));
      } else if (mode == Log.INFO) {
        Log.i(tag, message.substring(start, end));
      } else if (mode == Log.WARN) {
        Log.w(tag, message.substring(start, end));
      }
    }
  }

  public static void log(Exception e) {
    if (e != null && !TextUtils.isEmpty(e.getMessage()))
      Log.e(TAG, e.getMessage());
  }
}
