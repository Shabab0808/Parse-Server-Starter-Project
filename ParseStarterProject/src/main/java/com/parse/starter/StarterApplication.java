/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

<<<<<<< HEAD
=======
    // e6uPFYEglpBE

>>>>>>> 8a6c959c551656f4d730a5028d5f8cd0f9c20b2a
    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("myappID")
<<<<<<< HEAD
            .clientKey("2TPMgMZ29q9g")
            .server("http://3.134.98.2/parse/")
            .build()
    );

    ParseObject object = new ParseObject("ExampleObject");
    object.put("myNumber", "123");
    object.put("myString", "rob");

    object.saveInBackground(new SaveCallback () {
      @Override
      public void done(ParseException ex) {
        if (ex == null) {
          Log.i("Parse Result", "Successful!");
        } else {
          Log.i("Parse Result", "Failed" + ex.toString());
        }
      }
    });


    ParseUser.enableAutomaticUser();
=======
            .clientKey("2TPMgMZ29q9g") //Password for admin
            .server("http://18.222.144.36/parse/")
            .build()
    );
//
//    ParseObject object = new ParseObject("ExampleObject");
//    object.put("myNumber", "888");
//    object.put("myString", "shabab");
//
//    object.saveInBackground(new SaveCallback () {
//      @Override
//      public void done(ParseException ex) {
//        if (ex == null) {
//          Log.i("Parse Result", "Successful!");
//        } else {
//          Log.i("Parse Result", "Failed" + ex.toString());
//        }
//      }
//    });




    //ParseUser.enableAutomaticUser();
>>>>>>> 8a6c959c551656f4d730a5028d5f8cd0f9c20b2a

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
