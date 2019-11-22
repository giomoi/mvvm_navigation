package jp.co.ienter.bottomnavigation.viewmodels.dashboard;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import jp.co.ienter.bottomnavigation.models.Employee;
import jp.co.ienter.bottomnavigation.models.repository.EmployeeRepository;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private EmployeeRepository employeeRepository;

    public DashboardViewModel() {
        employeeRepository = new EmployeeRepository();
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Employee>> getAllEmployee() {
        return employeeRepository.getMutableLiveData();
    }
}