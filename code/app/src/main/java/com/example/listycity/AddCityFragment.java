package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    private static final String KEYCITY = "city";
    private city edit;

    interface AddCityDialogListener {
        void addCity(city c);
        void updateCity(city c);
    }

    private AddCityDialogListener listener;

    public static AddCityFragment newInstance(city c) {
        AddCityFragment f = new AddCityFragment();
        Bundle b = new Bundle();
        b.putSerializable(KEYCITY, c);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            edit = (city) getArguments().getSerializable(KEYCITY);
        }

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);

        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        if (edit != null) {
            editCityName.setText(edit.getName());
            editProvinceName.setText(edit.getProvince());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        String title = (edit == null) ? "Add a city" : "Edit city";
        String okText = (edit == null) ? "Add" : "OK";

        return builder
                .setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", null)
                .setPositiveButton(okText, (dialog, which) -> {
                    String cityName = editCityName.getText().toString().trim();
                    String provinceName = editProvinceName.getText().toString().trim();

                    if (edit == null) {

                        listener.addCity(new city(cityName, provinceName));
                    } else {

                        edit.setName(cityName);
                        edit.setProvince(provinceName);
                        listener.updateCity(edit);
                    }
                })
                .create();
    }
}
