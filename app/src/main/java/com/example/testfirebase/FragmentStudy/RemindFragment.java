package com.example.testfirebase.FragmentStudy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebase.Object.Remind;
import com.example.testfirebase.R;
import com.example.testfirebase.RecycleViewAdapter.RemindAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemindFragment extends Fragment {

    private RecyclerView rcvRemind;
    private RemindAdapter remindAdapter;
    private View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RemindFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrickFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemindFragment newInstance(String param1, String param2) {
        RemindFragment fragment = new RemindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_study_remind, container, false);

        rcvRemind = view.findViewById(R.id.rcv_remind_study);
        remindAdapter = new RemindAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        rcvRemind.setLayoutManager(gridLayoutManager);

        remindAdapter.setData(getListRemind());
        rcvRemind.setAdapter(remindAdapter);

        return view;
    }

    private List<Remind> getListRemind()
    {
        List<Remind> lst = new ArrayList<>();
        lst.add(new Remind(R.drawable.remind_hard_skill, "Kỹ năng cứng của 1 lập trình viên"));
        lst.add(new Remind(R.drawable.remind_soft_skill, "Kỹ năng mềm của 1 lập trình viên"));
        lst.add(new Remind(R.drawable.remind_main_subject, "Các môn học nền tảng"));
        lst.add(new Remind(R.drawable.remind_clean_code, "Kỹ năng code sạch (Clean Code)"));
        lst.add(new Remind(R.drawable.remind_debug_fixbug, "Kỹ năng tìm lỗi - gỡ lỗi (Debug - Fixbug)"));

        return lst;
    }

}