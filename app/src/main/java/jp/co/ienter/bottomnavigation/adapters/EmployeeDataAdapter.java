package jp.co.ienter.bottomnavigation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import jp.co.ienter.bottomnavigation.R;
import jp.co.ienter.bottomnavigation.models.Employee;

public class EmployeeDataAdapter extends RecyclerView.Adapter<EmployeeDataAdapter.EmployeeViewHolder> {
    private ArrayList<Employee> employees;
    private Context mContext;
    private LayoutInflater mInflater;

    public EmployeeDataAdapter(Context context, ArrayList<Employee> employees) {
        this.mInflater = LayoutInflater.from(context);
        this.employees = employees;
        this.mContext = context;
    }

    public void setEmployeeList(ArrayList<Employee> employees) {
        this.employees=employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.employee_list_item, parent, false);
        EmployeeViewHolder viewHolder = new EmployeeViewHolder(rowView);
        return viewHolder;
//        View view = mInflater.inflate(R.layout.employee_list_item, parent, false);
//        return new EmployeeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.tvFullName.setText(employees.get(position).getLastName());
        holder.tvEmail.setText(employees.get(position).getEmail());
        Glide.with(holder.imgView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(employees.get(position).getAvatar())
                .into(holder.imgView);
    }


    @Override
    public int getItemCount() {
        return employees.size();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class EmployeeViewHolder extends ViewHolder {
        public TextView tvFullName, tvEmail;
        public ImageView imgView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            imgView=itemView.findViewById(R.id.ivPic);

        }
    }
}
