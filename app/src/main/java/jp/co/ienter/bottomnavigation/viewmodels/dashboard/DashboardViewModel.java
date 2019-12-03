package jp.co.ienter.bottomnavigation.viewmodels.dashboard;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import jp.co.ienter.bottomnavigation.models.Employee;
import jp.co.ienter.bottomnavigation.models.callapi.RetrofitClient;
import jp.co.ienter.bottomnavigation.models.repository.EmployeeRepository;
import jp.co.ienter.bottomnavigation.viewmodels.Callback;
import jp.co.ienter.bottomnavigation.views.loadings.LoadingIndicator;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();

    private MutableLiveData<List<Employee>> mEmployees = new MutableLiveData<>();

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

    public void loadEmployees(boolean forceUpdate, final boolean showLoadingUI) {

        if (showLoadingUI) {
            LoadingIndicator.getInstance().show();
        }

        employeeRepository.getEmployees(new Callback<List<Employee>>() {

            @Override
            public void onSuccess(List<Employee> response) {
                if (showLoadingUI) {
                    LoadingIndicator.getInstance().hide();
                }

                if (response != null) {
                    mEmployees.setValue(response);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (showLoadingUI) {
                    LoadingIndicator.getInstance().hide();
                }
            }
        });
    }

}