package com.example.eat_master;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class order_dialog extends AppCompatDialogFragment {
    View view;
    private TextView namer,namef,count,B,L;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.order_dialog,null);
        builder.setView(view)
                .setTitle("ข่าวสาร \n \n")
                .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("โพสต์", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        textPost = edtextpost.getText().toString();
//                        topicPost = topi.getText().toString();
//                        insertpost();
                    }
                });

        namef = view.findViewById(R.id.dialogname);
        namer = view.findViewById(R.id.dialogprice);
        count = view.findViewById(R.id.dialogcount);
        B = view.findViewById(R.id.dialogB);
        L = view.findViewById(R.id.dialogL);
        return builder.create();
    }
}
