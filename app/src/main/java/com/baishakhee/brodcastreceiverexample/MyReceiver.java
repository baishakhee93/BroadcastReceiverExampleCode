package com.baishakhee.brodcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MyReceiver extends BroadcastReceiver {
      private static final String  TAG="MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String actionString=intent.getAction();

        Toast.makeText(context,actionString,Toast.LENGTH_LONG).show();

        String timeZone=intent.getStringExtra("timeZone");
        Log.d(TAG,"onReceiver "+timeZone);



        boolean isOn=intent.getBooleanExtra("state",false);
        Log.d(TAG,"onReceiver : Aroplain Mode is On "+isOn);

        PendingResult pendingResult=goAsync();

        Log.d(TAG,"onReceiver : BOOT ACTION ");

        new Task(pendingResult,intent).execute();

    }

    public static class  Task extends AsyncTask<Void,Void,Void>{

        PendingResult pendingResult;
         Intent intent;

        public Task(PendingResult pendingResult, Intent intent) {
            this.pendingResult = pendingResult;
            this.intent = intent;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d(TAG,"doInBackground :Work is Started ");

                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override


        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Log.d(TAG,"onPostExecute : Work is Finish");

            pendingResult.finish();

        }

    }
}