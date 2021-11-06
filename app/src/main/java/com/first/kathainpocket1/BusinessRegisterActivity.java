package com.first.kathainpocket1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class BusinessRegisterActivity extends AppCompatActivity
{
    private EditText bu_name;
    private Spinner spinner;
    private CircleImageView circleImageView;
    private Button getImage, register;
    private Uri image;
    private BusinessDatabase mydb;
    private Toolbar toolbar;
    private String business_type, business_name;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_register);

        mydb = new BusinessDatabase(this);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        spinner = findViewById(R.id.buss_type_spinner);
        bu_name =(EditText) findViewById(R.id.editbuss_name);
        circleImageView = findViewById(R.id.circle_image);
        getImage = findViewById(R.id.get_image);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                business_type = spinner.getSelectedItem().toString();
                business_name = bu_name.getText().toString();
                if(business_name.equals("") || business_type.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"please filled all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String uristring = image.toString();
                    Boolean B_name_check = mydb.Business_name_check(business_name);
                    if(!B_name_check)
                    {
                        Boolean flag = mydb.insertdata(business_name,business_type,uristring);
                        if(flag)
                        {
                            Toast.makeText(getApplicationContext(),"You are registered",Toast.LENGTH_SHORT).show();
                            bu_name.setText("");
                            Intent intent = new Intent(BusinessRegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"registration failed...",Toast.LENGTH_SHORT).show();
                    }
                    /*else
                    {
                        Toast.makeText(getApplicationContext(),"Business name already exist",Toast.LENGTH_SHORT).show();
                    }*/
                }
            }
        });

        getImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                launcher.launch(intent);
            }
        });
        launcher = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result)
                    {
                        if(result.getResultCode() == RESULT_OK)
                        {
                            Intent intent = result.getData();
                            image = intent.getData();
                            circleImageView.setImageURI(image);
                        }
                    }
                });

    }

}