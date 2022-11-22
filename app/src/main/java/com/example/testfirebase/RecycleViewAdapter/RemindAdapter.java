package com.example.testfirebase.RecycleViewAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebase.DetailRemind.CleanCode;
import com.example.testfirebase.DetailRemind.DebugFixbug;
import com.example.testfirebase.DetailRemind.HardSkill;
import com.example.testfirebase.DetailRemind.MainSubject;
import com.example.testfirebase.DetailRemind.SoftSkill;
import com.example.testfirebase.FragmentStudy.RemindFragment;
import com.example.testfirebase.Object.Remind;
import com.example.testfirebase.R;

import java.util.List;

public class RemindAdapter extends RecyclerView.Adapter<RemindAdapter.RemindViewHolder>{

    private RemindFragment mContext;
    private List<Remind> lstReMind;

    public RemindAdapter(RemindFragment mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RemindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_remind, parent, false);
        return new RemindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemindViewHolder holder, int position) {
        Remind rm = lstReMind.get(position);
        if(rm == null)
        {
            return;
        }

        holder.imgRemind.setImageResource(rm.getResourceImg());
        holder.txtRemindName.setText(rm.getNameRemind());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position) {

                    case 0:
                        startNewIntent(HardSkill.class);
                        break;

                    case 1:
                        startNewIntent(SoftSkill.class);
                        break;

                    case 2:
                        startNewIntent(MainSubject.class);
                        break;

                    case 3:
                        startNewIntent(CleanCode.class);
                        break;

                    case 4:
                        startNewIntent(DebugFixbug.class);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(lstReMind != null)
            return lstReMind.size();
        return 0;
    }

    public class RemindViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgRemind;
        private TextView txtRemindName;

        public RemindViewHolder(@NonNull View itemView) {
            super(itemView);

            imgRemind = itemView.findViewById(R.id.imgRemind_study);
            txtRemindName = itemView.findViewById(R.id.txtRemind_study);
        }
    }

    public void setData(List<Remind> lst)
    {
        this.lstReMind = lst;
        notifyDataSetChanged();
    }

    //khởi tạo activity mới
    public void startNewIntent(Class<?> cls)
    {
        Intent intent = new Intent(mContext.getActivity(), cls);
        mContext.startActivity(intent);
    }
}
