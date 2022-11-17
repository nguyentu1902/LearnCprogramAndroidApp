package com.example.testfirebase.RecycleViewAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebase.DetailAdvanceCourse.Array2way;
import com.example.testfirebase.DetailAdvanceCourse.FileIO;
import com.example.testfirebase.DetailAdvanceCourse.LinkedList;
import com.example.testfirebase.DetailAdvanceCourse.Pointers;
import com.example.testfirebase.DetailAdvanceCourse.Queue;
import com.example.testfirebase.DetailAdvanceCourse.Recursion;
import com.example.testfirebase.DetailAdvanceCourse.Search;
import com.example.testfirebase.DetailAdvanceCourse.Sort;
import com.example.testfirebase.DetailAdvanceCourse.Stack;
import com.example.testfirebase.DetailAdvanceCourse.Structures;
import com.example.testfirebase.FragmentStudy.AdvancedFragment;
import com.example.testfirebase.Object.AdvanceCourse;
import com.example.testfirebase.R;

import java.util.List;

public class AdvanceCourseAdapter extends RecyclerView.Adapter<AdvanceCourseAdapter.AdvanceViewHolder>{

    private final AdvancedFragment mContext;
    private List<AdvanceCourse> lstAC;

    public AdvanceCourseAdapter(AdvancedFragment mContext) {
        this.mContext = mContext;
    }

    public void setData(List<AdvanceCourse> lstAC)
    {
        this.lstAC = lstAC;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdvanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_study, parent, false);

        return new AdvanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvanceViewHolder holder, int position) {
        AdvanceCourse ac = lstAC.get(position);
        if (ac == null)
            return;
        else
        {
            holder.imgAC.setImageResource(ac.getImgAD());
            holder.nameAC.setText(ac.getNameAD());
        }

        holder.itemView.setOnClickListener(view ->
        {
            switch (position)
            {
                case 0:
                    startNewIntent(Recursion.class);
                    break;
                case 1:
                    startNewIntent(Pointers.class);
                    break;
                case 2:
                    startNewIntent(Array2way.class);
                    break;
                case 3:
                    startNewIntent(Search.class);
                    break;
                case 4:
                    startNewIntent(Sort.class);
                    break;
                case 5:
                    startNewIntent(Structures.class);
                    break;
                case 6:
                    startNewIntent(LinkedList.class);
                    break;
                case 7:
                    startNewIntent(FileIO.class);
                    break;
                case 8:
                    startNewIntent(Stack.class);
                    break;
                case 9:
                    startNewIntent(Queue.class);
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

    @Override
    public int getItemCount() {
        if(lstAC != null)
            return lstAC.size();
        return 0;
    }

    public class AdvanceViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAC;
        private TextView nameAC;

        public AdvanceViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAC = itemView.findViewById(R.id.imgFunction);
            nameAC = itemView.findViewById(R.id.tvFunction);
        }
    }
}
