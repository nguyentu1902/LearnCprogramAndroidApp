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

import com.example.testfirebase.Object.BasicCourse;
import com.example.testfirebase.R;
import com.example.testfirebase.RecycleViewAdapter.BasicCourseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicFragment extends Fragment {

    private RecyclerView rcvBC;
    private BasicCourseAdapter BCAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BasicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasicFragment newInstance(String param1, String param2) {
        BasicFragment fragment = new BasicFragment();
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
        return inflater.inflate(R.layout.fragment_study_basic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvBC = view.findViewById(R.id.rcv_basic_study);
        BCAdapter = new BasicCourseAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rcvBC.setLayoutManager(gridLayoutManager);

        BCAdapter.setData(getListBC());
        rcvBC.setAdapter(BCAdapter);
    }

    public List<BasicCourse> getListBC()
    {
        List<BasicCourse> list = new ArrayList<>();
        list.add(new BasicCourse(R.drawable.basic_course_input_output, "Nhập - xuất"));
        list.add(new BasicCourse(R.drawable.basic_course_datatype, "Kiểu dữ liệu"));
        list.add(new BasicCourse(R.drawable.basic_course_operator, "Toán tử"));
        list.add(new BasicCourse(R.drawable.basic_course_condition, "Cấu trúc rẽ nhánh"));
        list.add(new BasicCourse(R.drawable.basic_course_loop1, "Vòng lặp - 1"));
        list.add(new BasicCourse(R.drawable.basic_course_loop2, "Vòng lặp - 2"));
        list.add(new BasicCourse(R.drawable.basic_course_function, "Hàm con"));
        list.add(new BasicCourse(R.drawable.basic_course_array1, "Mảng 1 chiều - 1"));
        list.add(new BasicCourse(R.drawable.basic_course_array2, "Mảng 1 chiều - 2"));
        list.add(new BasicCourse(R.drawable.basic_course_strings, "Chuỗi"));

        return list;
    }
}