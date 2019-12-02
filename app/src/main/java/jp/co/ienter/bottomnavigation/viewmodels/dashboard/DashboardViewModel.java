package jp.co.ienter.bottomnavigation.viewmodels.dashboard;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import jp.co.ienter.bottomnavigation.models.Employee;
import jp.co.ienter.bottomnavigation.models.callapi.RetrofitClient;
import jp.co.ienter.bottomnavigation.models.repository.EmployeeRepository;

import jp.co.ienter.bottomnavigation.viewmodels.Callback;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();

    private MutableLiveData<List<Employee>> mEmployees = new MutableLiveData<>();

    private MutableLiveData<Boolean> mLoadingData = new MutableLiveData<>();


    private EmployeeRepository employeeRepository;

    public DashboardViewModel() {
        // TODO: 2019-12-02 How to use dependency injection with view model
        employeeRepository = new EmployeeRepository(RetrofitClient.getService());
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<Employee>> getEmployees() {
        return mEmployees;
    }

    public MutableLiveData<Boolean> getLoadingData() {
        return mLoadingData;
    }

    public void loadEmployees(boolean forceUpdate, final boolean showLoadingUI) {

        if (showLoadingUI) {
            mLoadingData.setValue(true);
        }

        employeeRepository.getEmployees(new Callback<List<Employee>>() {

            @Override
            public void onSuccess(List<Employee> response) {
                if (showLoadingUI) {
                    mLoadingData.setValue(false);
                }

                if (response != null) {
                    mEmployees.setValue(response);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (showLoadingUI) {
                    mLoadingData.setValue(false);
                }
            }
        });
    }

}