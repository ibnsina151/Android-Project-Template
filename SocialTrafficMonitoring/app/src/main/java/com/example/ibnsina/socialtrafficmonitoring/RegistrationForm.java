package com.example.ibnsina.socialtrafficmonitoring;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;


public class RegistrationForm extends AppCompatActivity  {
    //firebse ........................

    private static final int STORAGE_PERMISSION_CODE = 123;


    //firebase ..................................
    private ImageView reg_img;
    private AutoCompleteTextView reg_name;
    private TextView reg_mobile,reg_password;
    private RadioButton reg_ma,reg_fe;
    private Button btn_reg;
    private TextView reg_terms;
    private View mRegFormView;
    private View mRegProgressView;
    private RadioGroup radioGroup ;
    RadioButton rb;

    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri filePath;
    String flag = "0";

    private int radio_id;

    Required_data_Set_Get R_set_get = new Required_data_Set_Get();

    String Android_id;
    private static int timeout =5000;
    String Run_flags = "First_Time_Run";

    JSONArray array_All_Recipes;

    private UserRegistration mAuthTask = null;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        //check internet connection;
        //checkConnection();
        requestStoragePermission();

        this.setTitle("Registration");

        Android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        reg_img = (ImageView) findViewById(R.id.reg_image_loader);
        reg_name = (AutoCompleteTextView)findViewById(R.id.reg_txt_create_name);
        radioGroup = (RadioGroup) findViewById(R.id.radioGrp);
        reg_ma = (RadioButton)findViewById(R.id.radioM);
        reg_fe = (RadioButton)findViewById(R.id.radioF);
        reg_mobile = (TextView) findViewById(R.id.reg_txt_create_mobile);
        reg_password= (TextView) findViewById(R.id.reg_txt_create_password);
        btn_reg = (Button)findViewById(R.id.btn_signup);
        reg_terms =(TextView)findViewById(R.id.reg_txt_terms);
        mRegFormView = findViewById(R.id.reg_fromview);
        mRegProgressView = findViewById(R.id.reg_process);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioM) {
                    radio_id = 0;
                } else  if (checkedId == R.id.radioF) {
                    radio_id = 1;
                }
            }
        });

        reg_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login_2 || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        reg_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();

            }
        });

        reg_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    Bitmap picture = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/DCIM/MyPhoto.jpg");
//                    Log.v("Path", Environment.getExternalStorageDirectory().getPath()+"/DCIM/MyPhoto.jpg");
//                    reg_img.setImageBitmap(picture);
//                } catch (Exception e) {
//                    Log.e("Error reading file", e.toString());
//                }
                showFileChooser();
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                reg_img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void uploadMultipart(String android_id,String name,int gender,String mobile,String password) {
        //getting name for the image
        //String name = editText.getText().toString().trim();

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, Server_path.IMAGES_URL)
                    .addParameter("uniqueid", android_id) //Adding text parameter to the request
                    .addParameter("name",name)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("gender", String.valueOf(gender))
                    .addParameter("mobile",mobile)
                    .addParameter("password",password)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(6)
                    .startUpload(); //Starting the upload
            flag = "1";

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            flag = "0";

        }
    }

    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        reg_mobile.setError(null);
        reg_password.setError(null);

        // Store values at the time of the login attempt.
        String mobile = reg_mobile.getText().toString();
        String password = reg_password.getText().toString();
        String name = reg_name.getText().toString();
        String radio = ""+radio_id;

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            reg_mobile.setError(getString(R.string.error_invalid_password));
            focusView = reg_password;
            cancel = true;
        }

        // Check for User Name.
        if(TextUtils.isEmpty(name) && isNameValid(name))
        {
            reg_name.setError(getString(R.string.error_user_name));
            focusView = reg_name;
            cancel = true;
        }

        // Check for User Name.
        if(TextUtils.isEmpty(radio) || isgroup(radio))
        {
            reg_name.setError(getString(R.string.error_user_name));
            focusView = reg_name;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(mobile)) {
            reg_mobile.setError(getString(R.string.error_field_required));
            focusView = reg_mobile;
            cancel = true;
        } else if (!isPhoneValid(mobile)) {
            reg_mobile.setError(getString(R.string.error_invalid_email));
            focusView = reg_mobile;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            //String u_photo = getStringImage(bitmap);
            String u_name = reg_name.getText().toString();
            String u_mobile =reg_mobile.getText().toString();
            String u_password = reg_password.getText().toString();
            if(isOnline()){
            mAuthTask = new UserRegistration(Android_id,u_name,radio_id,u_mobile,u_password);
            mAuthTask.execute((Void) null);}
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mRegProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mRegProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private boolean isPhoneValid(String setnum) {
        //TODO: Replace this with your own logic

        String regexStr2 = "^(01\\)?[0-9]{9}$";
//        if (setnum.matches(regexStr2)){
//        }
//        return setnum.contains("01");
        //String regexStr2 = "^(1\\)?[0-9]{11}$";
        setnum.contains("01");
        if (setnum == null || TextUtils.isEmpty(setnum)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(setnum).matches();
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    private boolean isNameValid(String _name)
    {
        return _name.isEmpty();
    }

    private boolean isgroup(String id)
    {
        return id.isEmpty();
    }

    public class UserRegistration extends AsyncTask<Void, Void, Boolean> {

        private String androidid;
        private String name;
        private int radio;
        private String mobile;
        private String password;

        public UserRegistration(String androidid,String name, int radio, String mobile, String password) {
            this.androidid = androidid;
            this.name = name;
            this.radio = radio;
            this.mobile = mobile;
            this.password = password;

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            uploadMultipart(androidid,name,radio,mobile,password);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (flag == "1") {
                Intent intent =new Intent(RegistrationForm.this,MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(RegistrationForm.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                reg_password.setError(getString(R.string.error_incorrect_password));
                reg_password.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
        }
    }

    protected boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        else
            return false;
    }
    public void checkConnection(){
        if(isOnline()){
            Toast.makeText(RegistrationForm.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegistrationForm.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }
}
