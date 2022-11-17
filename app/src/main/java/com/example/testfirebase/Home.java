package com.example.testfirebase;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testfirebase.FragmentNavigationHome.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    public Toast mToast;
    public long backPressedTime;
    private Toolbar toolbar;
    private BottomNavigationView navBottom;
    private ViewPager viewPager_Home;
    private LinearLayout bottomSheetContact;
    private BottomSheetBehavior bottomSheetBehavior;
    private Button btnCloseBSContact, btnCoppyEmail, btnPhoneCall;
    private TextView txtEmailContact, txtPhoneContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //full man hinh
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        mappingId();
        setSupportActionBar(toolbar);

        setUpViewPager();
        //click item navigation
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_nav_home:
                        viewPager_Home.setCurrentItem(0);
                        break;
                    case R.id.action_nav_study:
                        viewPager_Home.setCurrentItem(1);
                        break;
                    case R.id.action_nav_profile:
                        viewPager_Home.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        //close bottom sheet contact
        btnCloseBSContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //coppy email
        btnCoppyEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("", txtEmailContact.getText().toString().trim());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(Home.this, "Coppy email thành công", Toast.LENGTH_SHORT).show();

            }
        });

        //goi
        btnPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneCall();
            }
        });
    }

    private void phoneCall()
    {
        String number = txtPhoneContact.getText().toString().trim();
        if(ContextCompat.checkSelfPermission(Home.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Home.this, new String[] {Manifest.permission.CALL_PHONE}, 1);
        }
        else
        {
            String dial = "tel:" + number;
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(dial));
            startActivity(intent);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                phoneCall();
            } else
                Toast.makeText(Home.this, "Từ chối truy cập cuộc gọi!", Toast.LENGTH_SHORT).show();
        }
    }

    //anh xa id xml
    public void mappingId()
    {
        toolbar = findViewById(R.id.toolbarHome);
        navBottom = findViewById(R.id.nav_bottom);
        viewPager_Home = findViewById(R.id.view_pager_Home);
        bottomSheetContact = findViewById(R.id.bottomSheetContact_Home);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetContact);
        btnCloseBSContact = findViewById(R.id.btnCloseBSContact);
        btnCoppyEmail = findViewById(R.id.btnCoppyEmail_contact);
        btnPhoneCall = findViewById(R.id.btnCall_contact);
        txtEmailContact = findViewById(R.id.txtEmail_contact);
        txtPhoneContact = findViewById(R.id.txtPhoneNum_contact);
    }

    //setup 3 trang home-study-profile
    public void setUpViewPager()
    {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager_Home.setAdapter(viewPagerAdapter);

        viewPager_Home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        navBottom.getMenu().findItem(R.id.action_nav_home).setChecked(true);
                        break;
                    case 1:
                        navBottom.getMenu().findItem(R.id.action_nav_study).setChecked(true);
                        break;
                    case 2:
                        navBottom.getMenu().findItem(R.id.action_nav_profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //back 2 lan de thoat ung dung
    public void onBackPressed()
    {
        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
            mToast.cancel();
            finish();
        }
        else
        {
            mToast = Toast.makeText(Home.this, "Back 1 lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    //set toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //xác nhận đăng xuất hay không
    public void showMessageToLogout()
    {
        AlertDialog.Builder alertLogout = new AlertDialog.Builder(Home.this);
        alertLogout.setTitle("Đăng xuất");
        alertLogout.setMessage("Bạn có chắc chắn muốn đăng xuất?");
        alertLogout.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });

        alertLogout.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertLogout.show();
    }

    //menu toolbar
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_profile:
                viewPager_Home.setCurrentItem(2);
                break;
            case R.id.action_contact:
                //Toast.makeText(Home.this, "Liên hệ", Toast.LENGTH_SHORT).show();
                bottomSheetContact();
                break;
            case R.id.action_about:
                Toast.makeText(Home.this, "Thông tin ứng dụng", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_logout:
                showMessageToLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bottomSheetContact()
    {
        if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED)
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}