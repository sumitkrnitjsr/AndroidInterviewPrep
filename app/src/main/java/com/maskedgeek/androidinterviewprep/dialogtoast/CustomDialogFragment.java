package com.maskedgeek.androidinterviewprep.dialogtoast;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.maskedgeek.androidinterviewprep.R;

public class CustomDialogFragment extends DialogFragment {

    private DialogActivityCommunicationListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Checking Dialog Working")
                .setPositiveButton(R.string.Agree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.agree(true);
                    }
                })
                .setNegativeButton(R.string.disagree, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.agree(false);
                    }
                });

        return builder.create();

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (DialogActivityCommunicationListener)context;
    }
}
