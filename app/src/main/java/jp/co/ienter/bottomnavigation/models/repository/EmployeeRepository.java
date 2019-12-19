package jp.co.ienter.bottomnavigation.models.repository;

import android.util.Log;

import java.util.List;

import androidx.annotation.Nullable;
import jp.co.ienter.bottomnavigation.models.Employee;
import jp.co.ienter.bottomnavigation.models.EmployeeDBResponse;
import jp.co.ienter.bottomnavigation.models.interfaces.EmployeeDataService;
import jp.co.ienter.bottomnavigation.viewmodels.Callback;
import retrofit2.Call;

import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

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
            @EverythingIsNonNull
            public void onResponse(Call<EmployeeDBResponse> call, Response<EmployeeDBResponse> response) {
                if (!response.isSuccessful()) {
                    Throwable er = new Throwable(response.toString());
                    Log.e(TAG, "onResponse: ", er);
                    callback.onFailure(er);
                    return;
                }

                if (response.body() == null || response.body().getEmployee() == null) {
                    Throwable er = new Throwable(response.toString());
                    Log.e(TAG, "onResponse: ", er);
                    callback.onFailure(er);
                    return;
                }

                List<Employee> employees = response.body().getEmployee();
                callback.onSuccess(employees);
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<EmployeeDBResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                callback.onFailure(t);
            }
        });
    }
}
