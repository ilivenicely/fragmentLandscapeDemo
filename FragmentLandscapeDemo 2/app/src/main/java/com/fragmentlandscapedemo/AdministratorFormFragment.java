package com.fragmentlandscapedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnAdminAddListener} interface
 * to handle interaction events.
 * Use the {@link AdministratorFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdministratorFormFragment extends Fragment {

    private OnAdminAddListener mListener;
    private EditText etFirstName,etLastName,etProgram;
    private Button btnAddAdmin;

    public AdministratorFormFragment() {
        // Required empty public constructor
    }

    public static AdministratorFormFragment newInstance() {
        AdministratorFormFragment fragment = new AdministratorFormFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrator_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etProgram = view.findViewById(R.id.etProgram);
        btnAddAdmin = view.findViewById(R.id.btnAddAdmin);

        btnAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etFirstName.getText())) {
                    etFirstName.setError("Please enter First Name");
                    return;
                }

                if (TextUtils.isEmpty(etLastName.getText())) {
                    etLastName.setError("Please enter Last Name");
                    return;
                }

                if (TextUtils.isEmpty(etProgram.getText())) {
                    etProgram.setError("Please enter Program");
                    return;
                }

                Administrator administrator = new Administrator();
                int id = ((MainActivity)getActivity()).listItemModelArrayList.size()+1;
                administrator.setId(id);
                administrator.setName(etFirstName.getText().toString()+" "+etLastName.getText().toString());
                administrator.setUniversity("Test University");
                administrator.setProgram(etProgram.getText().toString());
                onAddAdministrator(administrator);
            }
        });
    }

    public void onAddAdministrator(Administrator administrator) {
        if (mListener != null) {
            mListener.onAdminAdded(administrator);
        }

        Toast.makeText(getActivity(), "Added Administrator", Toast.LENGTH_SHORT).show();
        etFirstName.setText("");
        etLastName.setText("");
        etProgram.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAdminAddListener) {
            mListener = (OnAdminAddListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAdminAddListener {
        void onAdminAdded(Administrator administrator);
    }
}
