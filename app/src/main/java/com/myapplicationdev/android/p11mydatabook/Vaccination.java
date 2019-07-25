package com.myapplicationdev.android.p11mydatabook;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Vaccination extends Fragment {

    Button btnEdit;
    TextView tvData;
    SharedPreferences sp;
    public static final String USER_PREF = "USER_PREF";


    public Vaccination() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vaccination, container, false);
        btnEdit = v.findViewById(R.id.btnEdit);
        tvData = v.findViewById(R.id.tvData);
        sp = getActivity().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.dialog_edit, null);
                final EditText etInput = view1.findViewById(R.id.etInput);
                etInput.setText(tvData.getText().toString());
                builder.setTitle("Edit Bio").setView(view1).setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = etInput.getText().toString();
                                tvData.setText(text);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return v;
    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("vac_data", tvData.getText().toString());
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        String data = sp.getString("vac_data", "Default Text");
        tvData.setText(data);
    }

}
