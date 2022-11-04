package com.example.testfirebase.RecycleViewAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebase.DetailBasicCourse.Array1;
import com.example.testfirebase.DetailBasicCourse.Array2;
import com.example.testfirebase.DetailBasicCourse.Condition;
import com.example.testfirebase.DetailBasicCourse.DataType;
import com.example.testfirebase.DetailBasicCourse.Function;
import com.example.testfirebase.DetailBasicCourse.InputOutput;
import com.example.testfirebase.DetailBasicCourse.Loop1;
import com.example.testfirebase.DetailBasicCourse.Loop2;
import com.example.testfirebase.DetailBasicCourse.Operator;
import com.example.testfirebase.DetailBasicCourse.String;
import com.example.testfirebase.FragmentStudy.BasicFragment;
import com.example.testfirebase.Object.BasicCourse;
import com.example.testfirebase.R;

import java.util.List;

public class BasicCourseAdapter extends RecyclerView.Adapter<BasicCourseAdapter.basicCourseViewHolder> {

    private final BasicFragment mContext;
    private List<BasicCourse> BCList;

    public BasicCourseAdapter(BasicFragment mContext) {
        this.mContext = mContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<BasicCourse> list) {
        this.BCList = list;
        notifyDataSetChanged();
    }

    //khởi tạo view
    @NonNull
    @Override
    public basicCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_study, parent, false);
        return new basicCourseViewHolder(view);
    }

    //đổ dữ liệu vào obj trên grid recycle
    @Override
    public void onBindViewHolder(@NonNull basicCourseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BasicCourse basicCourse = BCList.get(position);
        if (basicCourse == null) {
            return;
        }

        holder.imgBC.setImageResource(basicCourse.getReSourceImg());
        holder.tvBC.setText(basicCourse.getNameOfBasicCourse());

        holder.itemView.setOnClickListener(view -> {
            switch (position) {
                case 0:
                    startNewIntent(InputOutput.class);
                    break;
                case 1:
                    startNewIntent(DataType.class);
                    break;
                case 2:
                    startNewIntent(Operator.class);
                    break;
                case 3:
                    startNewIntent(Condition.class);
                    break;
                case 4:
                    startNewIntent(Loop1.class);
                    break;
                case 5:
                    startNewIntent(Loop2.class);
                    break;
                case 6:
                    startNewIntent(Function.class);
                    break;
                case 7:
                    startNewIntent(Array1.class);
                    break;
                case 8:
                    startNewIntent(Array2.class);
                    break;
                case 9:
                    startNewIntent(String.class);
                    break;
            }
        });
    }

    //khởi tạo activity mới
    public void startNewIntent(Class<?> cls)
    {
        Intent intent = new Intent(mContext.getActivity(), cls);
        mContext.startActivity(intent);
    }

    //trả về số lượng item của grid recycle
    @Override
    public int getItemCount() {
        if (BCList != null)
            return BCList.size();
        return 0;
    }

    //mapping xml
    public class basicCourseViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBC;
        private TextView tvBC;

        public basicCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBC = itemView.findViewById(R.id.imgFunction);
            tvBC = itemView.findViewById(R.id.tvFunction);
        }
    }


}
