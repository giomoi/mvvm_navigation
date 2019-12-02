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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.co.ienter.bottomnavigation.BaseFragment;
import jp.co.ienter.bottomnavigation.R;
import jp.co.ienter.bottomnavigation.adapters.EmployeeDataAdapter;
import jp.co.ienter.bottomnavigation.models.Employee;
import jp.co.ienter.bottomnavigation.viewmodels.dashboard.DashboardViewModel;

public class DashboardFragment extends BaseFragment {
    private EmployeeDataAdapter employeeDataAdapter;
    private DashboardViewModel dashboardViewModel;

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

        employeeDataAdapter = new EmployeeDataAdapter(getContext(), new ArrayList<Employee>(0));
        recyclerView.setAdapter(employeeDataAdapter);

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        LiveData<List<Employee>> employeeLiveData = dashboardViewModel.getEmployees();
        employeeLiveData.observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                if (getBaseActivity() != null) {
                    employeeDataAdapter.setEmployeeList(employees);
                }
            }
        });

        LiveData<Boolean> loadingLiveData = dashboardViewModel.getLoadingData();
        loadingLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (getBaseActivity() != null) {
                    getBaseActivity().setLoading(isLoading);
                }
            }
        });

        dashboardViewModel.loadEmployees(false, true);
        return root;
    }
}