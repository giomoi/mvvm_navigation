package jp.co.ienter.bottomnavigation.models.interfaces;

import jp.co.ienter.bottomnavigation.models.EmployeeDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeDataService {
    @GET("users/?per_page=12&amp;page=1")
    Call<EmployeeDBResponse> getEmployees();
}
