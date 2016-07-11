package com.slash.simplelife.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.slash.simplelife.R;

public class EditDialogFragment extends DialogFragment {
    private EditText content;

    public interface LoginInputListener {
        void onLoginInputComplete(String content);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_fragment_dialog, null);
        content = (EditText) view.findViewById(R.id.id_txt_username);
        builder.setView(view)
                .setPositiveButton("确认",
                        (dialog, id) -> {
                            LoginInputListener listener = (LoginInputListener) getActivity();
                            listener.onLoginInputComplete(content
                                    .getText().toString());
                        }).setNegativeButton("取消", null);
        return builder.create();
    }
}
