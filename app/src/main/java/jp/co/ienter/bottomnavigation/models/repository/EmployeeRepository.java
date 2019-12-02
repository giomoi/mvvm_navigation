package jp.co.ienter.bottomnavigation.models.repository;

import java.util.List;

import jp.co.ienter.bottomnavigation.models.Employee;
import jp.co.ienter.bottomnavigation.models.EmployeeDBResponse;
import jp.co.ienter.bottomnavigation.models.interfaces.EmployeeDataService;
import jp.co.ienter.bottomnavigation.viewmodels.Callback;
import retrofit2.Call;

import retrofit2.Response;

public class EmployeeRepository {
    private static final String TAG = "EmployeeRepository";

    private final EmployeeDataService mUserDataService;

    public EmployeeRepository(EmployeeDataService employeeDataService) {
        mUserDataService = employeeDataService;
    }

    public void getEmployees(final Callback<List<Employee>> callback) {

        Call<EmployeeDBResponse> call = mUserDataService.getEmployees();

        call.enqueue(new retrofit2.Callback<EmployeeDBResponse>() {
            @Override
            public void onResponse(Call<EmployeeDBResponse> call, Response<EmployeeDBResponse> response) {
                EmployeeDBResponse employeeDBResponse = response.body();
                if (employeeDBResponse != null && employeeDBResponse.getEmployee() != null) {
                    List<Employee> employees = employeeDBResponse.getEmployee();
                    callback.onSuccess(employees);
                }
            }

            @Override
            public void onFailure(Call<EmployeeDBResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
