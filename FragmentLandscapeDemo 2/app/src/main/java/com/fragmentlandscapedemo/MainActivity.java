package com.fragmentlandscapedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdministratorFormFragment.OnAdminAddListener, StudentFormFragment.OnStudentAddedListener, TeacherFragment.OnTeacherAddedListener{

    private AppCompatSpinner spPeople;
    private Button btnRefresh;
    public ArrayList<ListItemModel> listItemModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spPeople = findViewById(R.id.spPeople);
        btnRefresh = findViewById(R.id.btnRefresh);

        spPeople.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFormContainer, AdministratorFormFragment.newInstance()).commit();
                } else if (position == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFormContainer,TeacherFragment.newInstance()).commit();
                } else if (position == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFormContainer, StudentFormFragment.newInstance()).commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemModelArrayList.clear();
                ((MyListFragment)getSupportFragmentManager().findFragmentById(R.id.flListContainer)).updateList();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.flListContainer, MyListFragment.newInstance()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.flFormContainer, AdministratorFormFragment.newInstance()).commit();
    }

    @Override
    public void onAdminAdded(Administrator administrator) {
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setType("Administrator");
        listItemModel.setAdministrator(administrator);
        listItemModelArrayList.add(listItemModel);

        ((MyListFragment)getSupportFragmentManager().findFragmentById(R.id.flListContainer)).updateList();
    }

    @Override
    public void onTeacherAdded(Teacher teacher) {
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setType("Teacher");
        listItemModel.setTeacher(teacher);
        listItemModelArrayList.add(listItemModel);
        ((MyListFragment)getSupportFragmentManager().findFragmentById(R.id.flListContainer)).updateList();
    }

    @Override
    public void onStudentAdded(Student student) {
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setType("Student");
        listItemModel.setStudent(student);
        listItemModelArrayList.add(listItemModel);
        ((MyListFragment)getSupportFragmentManager().findFragmentById(R.id.flListContainer)).updateList();
    }
}
