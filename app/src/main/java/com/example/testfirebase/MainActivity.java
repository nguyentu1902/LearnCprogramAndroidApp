package com.example.testfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testfirebase.Object.Course;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mData;
    ArrayAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        ArrayList<String> baiHocs = new ArrayList<String>();
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, baiHocs);
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_white_text, R.id.list_content,baiHocs);


        listView.setAdapter(adapter);

        Course b_kdl = new Course(1,"Biến - Kiểu dữ liệu");
        Course tt = new Course(2,"Toán tử");
        Course vl = new Course(3,"Vòng lặp");
        Course dk_rn = new Course(4,"Điều kiện - rẽ nhánh");
        Course a = new Course(5,"Đệ quy");
        Course b = new Course(6,"Hàm con");
        Course c = new Course(7,"Struct");
        Course d = new Course(8,"Gán");
        Course e = new Course(9,"Con trỏ");
        Course f = new Course(10,"Kiểu dữ liệu có cấu trúc");
        Course g = new Course(4,"Pointer");
        Course h = new Course(4,"So sánh");
        Course i = new Course(4,"Phương trình bậc 1");
        Course j = new Course(4,"Phương trình bậc 2");
        Course k = new Course(4,"Struct tam giác");



        mData = FirebaseDatabase.getInstance().getReference();

//        mData.child("BaiHoc").push().setValue(b);
//        mData.child("BaiHoc").push().setValue(c);
//        mData.child("BaiHoc").push().setValue(d);
//        mData.child("BaiHoc").push().setValue(e);
//        mData.child("BaiHoc").push().setValue(f);
//        mData.child("BaiHoc").push().setValue(g);
//        mData.child("BaiHoc").push().setValue(h);
//        mData.child("BaiHoc").push().setValue(i);
//        mData.child("BaiHoc").push().setValue(j);
//        mData.child("BaiHoc").push().setValue(k);

//        mData.child("BaiHoc").push().setValue(b_kdl);
//        mData.child("BaiHoc").push().setValue(tt);
//        mData.child("BaiHoc").push().setValue(vl);
//        mData.child("BaiHoc").push().setValue(dk_rn);

        mData.child("BaiHoc").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //txt.append(snapshot.getValue().toString() + "\n");
                Course bh = snapshot.getValue(Course.class);
                baiHocs.add(bh.tenBaiHoc);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, listView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}