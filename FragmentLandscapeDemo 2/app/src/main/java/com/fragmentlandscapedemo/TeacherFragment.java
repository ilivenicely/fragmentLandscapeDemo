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


public class TeacherFragment extends Fragment {

    private OnTeacherAddedListener mListener;
    private EditText etFirstName,etLastName,etCourse;
    private Button btnAddStudent;

    public TeacherFragment() {
        // Required empty public constructor
    }


    public static TeacherFragment newInstance() {
        TeacherFragment fragment = new TeacherFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etCourse = view.findViewById(R.id.etCourse);
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

                if (TextUtils.isEmpty(etCourse.getText())) {
                    etCourse.setError("Please enter Course");
                    return;
                }

                Teacher teacher = new Teacher();
                int id = ((MainActivity)getActivity()).listItemModelArrayList.size()+1;
                teacher.setId(id);
                teacher.setName(etFirstName.getText().toString()+" "+etLastName.getText().toString());
                teacher.setUniversity("Test University");
                teacher.setCourse(etCourse.getText().toString());
                onTeacherAdded(teacher);
            }
        });
    }

    public void onTeacherAdded(Teacher teacher) {
        if (mListener != null) {
            mListener.onTeacherAdded(teacher);
        }
        Toast.makeText(getActivity(), "Added Teacher", Toast.LENGTH_SHORT).show();
        etFirstName.setText("");
        etLastName.setText("");
        etCourse.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTeacherAddedListener) {
            mListener = (OnTeacherAddedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnTeacherAddedListener {
        void onTeacherAdded(Teacher teacher);
    }
}
