package com.trulden.friends.activity.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.trulden.friends.activity.EditInteractionActivity;

import static com.trulden.friends.util.Util.*;

// This dialog is created when user enters name of friend who is not in a database yet
public class FriendNotFoundDialog extends DialogFragment {

    private String name;

    public FriendNotFoundDialog(String name){
        this.name = name;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("You don't have friend named «" + name + "»") // TODO check all friends in row, not one by one after clicking save
               .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       ((EditInteractionActivity)getActivity()).createFriendByName(name);
                   }
               })
               .setNeutralButton("I'll edit", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       // There might be a better way of doing this
                       makeToast(getActivity(), "Fix «" + name + "» up"); // TODO show edit form
                   }
               })
               .setNegativeButton("Forget", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       ((EditInteractionActivity)getActivity()).removeFriendName(name);
                   }
               });

        return builder.create();
    }
}
