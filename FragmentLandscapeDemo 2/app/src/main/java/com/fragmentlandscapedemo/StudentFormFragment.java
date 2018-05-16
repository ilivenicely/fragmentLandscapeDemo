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


public class StudentFormFragment extends Fragment {

    private OnStudentAddedListener mListener;
    private EditText etFirstName,etLastName,etGrade;
    private Button btnAddStudent;

    public StudentFormFragment() {
        // Required empty public constructor
    }

    public static StudentFormFragment newInstance() {
        StudentFormFragment fragment = new StudentFormFragment();
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
        return inflater.inflate(R.layout.fragment_student_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etGrade = view.findViewById(R.id.etGrade);
        btnAddStudent = view.findViewById(R.id.btnAddStudent);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
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

                if (TextUtils.isEmpty(etGrade.getText())) {
                    etGrade.setError("Please enter Grade");
                    return;
                }

                Student student = new Student();
                int id = ((MainActivity)getActivity()).listItemModelArrayList.size()+1;
                student.setId(id);
                student.setName(etFirstName.getText().toString()+" "+etLastName.getText().toString());
                student.setUniversity("Test University");
                student.setGrade(etGrade.getText().toString());
                onStudentAdded(student);
            }
        });
    }

    public void onStudentAdded(Student student) {
        if (mListener != null) {
            mListener.onStudentAdded(student);
        }

        Toast.makeText(getActivity(), "Added Student", Toast.LENGTH_SHORT).show();
        etFirstName.setText("");
        etLastName.setText("");
        etGrade.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStudentAddedListener) {
            mListener = (OnStudentAddedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnStudentAddedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnStudentAddedListener {
        void onStudentAdded(Student student);
    }
}
