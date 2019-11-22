package jp.co.ienter.bottomnavigation.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.co.ienter.bottomnavigation.R;
import jp.co.ienter.bottomnavigation.adapters.EmployeeDataAdapter;
import jp.co.ienter.bottomnavigation.models.Employee;
import jp.co.ienter.bottomnavigation.viewmodels.dashboard.DashboardViewModel;

public class DashboardFragment extends Fragment {
    private EmployeeDataAdapter employeeDataAdapter;
    private DashboardViewModel dashboardViewModel;
    private ArrayList<Employee> employeesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        // bind RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.viewEmployees);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        employeesList = new ArrayList<>();
        employeeDataAdapter = new EmployeeDataAdapter(getContext(), employeesList);
        recyclerView.setAdapter(employeeDataAdapter);

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        dashboardViewModel.getAllEmployee().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                employeeDataAdapter.setEmployeeList((ArrayList<Employee>) employees);
            }
        });
        return root;
    }
}