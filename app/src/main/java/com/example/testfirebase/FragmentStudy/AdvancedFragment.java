package com.example.testfirebase.FragmentStudy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebase.Object.AdvanceCourse;
import com.example.testfirebase.R;
import com.example.testfirebase.RecycleViewAdapter.AdvanceCourseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdvancedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdvancedFragment extends Fragment {

    private RecyclerView rcvAC;
    private AdvanceCourseAdapter ACAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdvancedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdvancedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdvancedFragment newInstance(String param1, String param2) {
        AdvancedFragment fragment = new AdvancedFragment();
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
        return inflater.inflate(R.layout.fragment_study_advanced, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvAC = view.findViewById(R.id.rcv_advance_study);
        ACAdapter = new AdvanceCourseAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rcvAC.setLayoutManager(gridLayoutManager);

        ACAdapter.setData(getListAC());
        rcvAC.setAdapter(ACAdapter);
    }

    public List<AdvanceCourse> getListAC()
    {
        List<AdvanceCourse> list = new ArrayList<>();
        list.add(new AdvanceCourse(R.drawable.ac_recursion, "Đệ quy"));
        list.add(new AdvanceCourse(R.drawable.ac_pointers, "Con trỏ"));
        list.add(new AdvanceCourse(R.drawable.ac_array2way, "Mảng 2 chiều"));
        list.add(new AdvanceCourse(R.drawable.ac_search, "Thuật toán tìm kiếm"));
        list.add(new AdvanceCourse(R.drawable.ac_sort, "Thuật toán sắp xếp"));
        list.add(new AdvanceCourse(R.drawable.ac_structures, "Kiểu dữ liệu cấu trúc"));
        list.add(new AdvanceCourse(R.drawable.ac_linkedlist, "Danh sách liên kết"));
        list.add(new AdvanceCourse(R.drawable.ac_file_io, "Đọc - ghi file"));
        list.add(new AdvanceCourse(R.drawable.ac_stack, "Ngăn xếp"));
        list.add(new AdvanceCourse(R.drawable.ac_queue, "Hàng đợi"));

        return list;
    }

}