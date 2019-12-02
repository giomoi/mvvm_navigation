package jp.co.ienter.bottomnavigation;

import jp.co.ienter.bottomnavigation.models.callapi.RetrofitClient;
import jp.co.ienter.bottomnavigation.models.interfaces.EmployeeDataService;

//
// Created by  on 2019-12-02.
//
public class ScreenComponents {
    public EmployeeDataService getEmployeeService() {
        return RetrofitClient.getService();
    }
}
