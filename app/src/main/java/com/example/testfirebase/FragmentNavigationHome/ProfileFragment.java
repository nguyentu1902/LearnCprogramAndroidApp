package com.example.testfirebase.FragmentNavigationHome;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.testfirebase.FragmentOnBoard.OnBoard;
import com.example.testfirebase.Object.UserAccount;
import com.example.testfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private View viewProfile;
    private EditText edtMail, edtName, edtAge, edtNewpw, edtNewEmail;
    private Button btnUpdateInfor, btnSaveInfor, btnChooseImg, btnSaveImg, btnResetPass,
            btnDeleteAccount, btnExitnewpw, btnConfirmnewpw, btnExitnewEmail, btnConfirmNewEmail, btnResetEmail;
    private ImageView imgProfile;
    private TextView txtWelcome;
    private ProgressDialog progressDialog;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private StorageReference storageReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);
        mapping();
        disableEditText();
        disableBtnSaveImg();
        disableBtnSaveInfor();

        progressDialog = new ProgressDialog(getActivity());

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        storageReference = FirebaseStorage.getInstance().getReference();

        DatabaseReference getImg = reference.child(userID).child("img");
        getImg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.getValue(String.class);
                Picasso.get().load(link).into(imgProfile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Kh??ng th??? load ???nh!", Toast.LENGTH_SHORT).show();
            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserAccount userAccount = snapshot.getValue(UserAccount.class);

                if (userAccount != null) {
                    String email = userAccount.getEmail();
                    String name = userAccount.getName();
                    String age = userAccount.getAge();
                    String welcome = userAccount.getName();

                    edtMail.setText(email);
                    edtName.setText(name);
                    edtAge.setText(age);

                    txtWelcome.setText("Xin ch??o " + welcome + " !!!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Kh??ng th??? truy c???p th??ng tin c?? nh??n!", Toast.LENGTH_LONG).show();
            }
        });

        //sua thong tin
        btnUpdateInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
                enableBtnsaveInfor();
            }
        });

        //luu thong tin
        btnSaveInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (updateProfile() == true) {
                    Toast.makeText(getActivity(), "C???p nh???t th??ng tin th??nh c??ng!", Toast.LENGTH_SHORT).show();
                    disableEditText();
                    disableBtnSaveInfor();
                } else
                    Toast.makeText(getActivity(), "C???p nh???t th??ng tin th???t b???i!", Toast.LENGTH_SHORT).show();
            }
        });

        //chon anh tu thu vien
        btnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableBtnSaveImg();
                imgChooser();
            }
        });

        //luu anh
        btnSaveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableBtnSaveImg();
                Uri uri = Uri.parse(selectedImagePath);
                upLoadImgtoFireBase(uri);
            }
        });

        //xoa tai khoan
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessageToDeleteAccount();
            }
        });

        //reset password
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openDialogConfirmNewpw(Gravity.CENTER);
            }
        });

        //reset email
        btnResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogConfirmNewEmail(Gravity.CENTER);
            }
        });


        return viewProfile;
    }

    public void mapping() {
        edtMail = viewProfile.findViewById(R.id.edtEmail_Profile);
        edtName = viewProfile.findViewById(R.id.edtName_Profile);
        edtAge = viewProfile.findViewById(R.id.edtAge_Profile);
        edtNewpw = viewProfile.findViewById(R.id.edtNewpassWord_Profile);
        edtNewEmail = viewProfile.findViewById(R.id.edtNewEmail_Profile);
        btnUpdateInfor = viewProfile.findViewById(R.id.btnUpdate_infor_profile);
        btnSaveInfor = viewProfile.findViewById(R.id.btnSave_infor_Profile);
        btnChooseImg = viewProfile.findViewById(R.id.btnChoose_Img_profile);
        btnSaveImg = viewProfile.findViewById(R.id.btnSave_img_Profile);
        btnDeleteAccount = viewProfile.findViewById(R.id.btnDeleteUser_Profile);
        btnResetPass = viewProfile.findViewById(R.id.btnUpdatePass_profile);
        btnResetEmail = viewProfile.findViewById(R.id.btnResetEmail_Profile);
        imgProfile = viewProfile.findViewById(R.id.imgUser_Profile);
        txtWelcome = viewProfile.findViewById(R.id.txtWelcome_Profile);
    }

    //lay anh tu thu vien
    public void imgChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Ch???n h??nh ???nh "), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                selectedImagePath = data.getData().toString();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imgProfile.setImageURI(selectedImageUri);
                    //upLoadImgtoFireBase(selectedImageUri);
                }
            }
        }
    }

    //tai anh len firebase
    public void upLoadImgtoFireBase(Uri imgUri) {
        progressDialog.setTitle("L??u ???nh");
        progressDialog.setMessage("Vui l??ng ?????i trong gi??y l??t...");
        progressDialog.show();
        final StorageReference fileRef = storageReference.child("UserImg/" + userID + "/imgProfile.jpg");
        fileRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imgProfile);
                        downloadImg();
                        Toast.makeText(getActivity(), "C???p nh???t ???nh th??nh c??ng!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "C???p nh???t ???nh th???t b???i!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //download url after upload
    public void downloadImg() {
        storageReference.child("UserImg/" + userID + "/imgProfile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                reference.child(userID).child("img").setValue(uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    public void disableEditText() {
        edtMail.setEnabled(false);
        edtName.setEnabled(false);
        edtAge.setEnabled(false);
    }

    public void disableBtnSaveInfor() {
        btnSaveInfor.setEnabled(false);
        btnSaveInfor.setTextColor(Color.parseColor("#7c8f92"));
        btnSaveInfor.setBackgroundColor(Color.parseColor("#C4cbcb"));
    }

    public void enableBtnsaveInfor() {
        edtName.setEnabled(true);
        edtAge.setEnabled(true);
        btnSaveInfor.setEnabled(true);
        btnSaveInfor.setTextColor(Color.parseColor("#151616"));
        btnSaveInfor.setBackgroundColor(Color.parseColor("#56A1BF"));
    }

    public void disableBtnSaveImg() {
        btnSaveImg.setEnabled(false);
        btnSaveImg.setTextColor(Color.parseColor("#7c8f92"));
        btnSaveImg.setBackgroundColor(Color.parseColor("#C4cbcb"));
    }

    public void enableBtnSaveImg() {
        btnSaveImg.setEnabled(true);
        btnSaveImg.setTextColor(Color.parseColor("#151616"));
        btnSaveImg.setBackgroundColor(Color.parseColor("#56A1BF"));
    }

    public boolean updateProfile() {
        if (TextUtils.isEmpty(edtName.getText().toString().trim())) {
            edtName.setError("Y??u c???u nh???p t??n!");
            return false;
        }
        if (TextUtils.isEmpty(edtName.getText().toString().trim())) {
            edtAge.setError("Y??u c???u nh???p tu???i!");
            return false;
        } else {
            String name = edtName.getText().toString().trim();
            String age = edtAge.getText().toString().trim();
            reference.child(userID).child("name").setValue(name);
            reference.child(userID).child("age").setValue(age);
            txtWelcome.setText("Xin ch??o " + edtName.getText().toString().trim() + " !!!");
            return true;
        }
    }

    //xac nhan xoa tai khoan
    public void showMessageToDeleteAccount() {
        AlertDialog.Builder alertLogout = new AlertDialog.Builder(getActivity());
        alertLogout.setTitle("X??a t??i kho???n");
        alertLogout.setMessage("B???n c?? ch???c ch???n mu???n x??a t??i kho???n?");
        alertLogout.setPositiveButton("X??c nh???n", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteAccount();
            }
        });

        alertLogout.setNegativeButton("H???y b???", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertLogout.show();
    }

    //xoa tai khoan
    public void deleteAccount() {
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), "0");
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            reference.child(userID).removeValue();
                            Toast.makeText(getActivity(), "T??i kho???n ???? b??? x??a!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), OnBoard.class));
                        } else
                            Toast.makeText(getActivity(), "X??a th???t b???i!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public void openDialogConfirmNewpw(int gravity)
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirm_newpassword);

        Window window = dialog.getWindow();
        if (window == null)
        {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity)
        {
            dialog.setCancelable(true);
        }

        edtNewpw = dialog.findViewById(R.id.edtNewpassWord_Profile);
        btnExitnewpw = dialog.findViewById(R.id.btn_Cancel_newPass_Profile);
        btnConfirmnewpw = dialog.findViewById(R.id.btn_confirm_newPass_Profile);

        btnExitnewpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnConfirmnewpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newpass = edtNewpw.getText().toString().trim();
                if(TextUtils.isEmpty(newpass) || newpass.length() < 6)
                {
                    edtNewpw.setError("Y??u c???u m???t kh???u b???ng ho???c l???n h??n 6 k?? t???!");
                    return;
                }
                resetPassword(newpass);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void openDialogConfirmNewEmail(int gravity)
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_email);

        Window window = dialog.getWindow();
        if (window == null)
        {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if(Gravity.CENTER == gravity)
        {
            dialog.setCancelable(true);
        }

        edtNewEmail = dialog.findViewById(R.id.edtNewEmail_Profile);
        btnExitnewEmail = dialog.findViewById(R.id.btn_Cancel_newEmail_Profile);
        btnConfirmNewEmail = dialog.findViewById(R.id.btn_confirm_newEmail_Profile);

        btnExitnewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnConfirmNewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newpEmail = edtNewEmail.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(newpEmail).matches() || TextUtils.isEmpty(edtNewEmail.getText().toString().trim()))
                {
                    edtNewEmail.setError("Y??u c???u nh???p ch??nh x??c email c???a b???n!");
                    edtNewEmail.requestFocus();
                }
                resetEmail(newpEmail);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //?????i m???t kh???u
    public void resetPassword(String newpw) {
        progressDialog.setTitle("?????i m???t kh???u");
        progressDialog.setMessage("Vui l??ng ?????i trong gi??y l??t...");
        progressDialog.show();
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), "0");
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                user.updatePassword(newpw).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "C???p nh???t m???t kh???u th??nh c??ng!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "C???p nh???t m???t kh???u th???t b???i!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //?????i email
    public void resetEmail(String email) {
        progressDialog.setTitle("?????i email");
        progressDialog.setMessage("Vui l??ng ?????i trong gi??y l??t...");
        progressDialog.show();
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), "0");
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        reference.child(userID).child("email").setValue(email);
                        Toast.makeText(getActivity(), "C???p nh???t email th??nh c??ng!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "C???p nh???t email th???t b???i!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    

}
