package com.example.ibnsina.socialtrafficmonitoring;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;


import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.R.attr.bitmap;
import static android.app.Activity.RESULT_OK;

/**
 * Created by ibnsina on 1/9/18.
 */

public class Information_Fragment extends Fragment {

    private static final int STORAGE_PERMISSION_CODE = 123;

    View viewer,viewer_dialog,viewer_dialog_item;
    TextView txt_post_make_head;
    Button btn_wating_time,btn_jam_level;
    ImageView post_image;

    private View mRegProgressView;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String uploadImagePath = "";

    String flag = "0";


    AutoCompleteTextView area,signalarea;
    EditText post_body;

    private UserPost mAuthTask = null;


    private static final String DIALOG_DATE = "Time_Date";
    private Uri uri;
    private Activity activity;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requestStoragePermission();


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        viewer = inflater.inflate(R.layout.activity_fragment_information, container,false);
        viewer_dialog = inflater.inflate(R.layout.wating_dialog_viewer,container,false);
        viewer_dialog_item = inflater.inflate(R.layout.waiting_dialog_item,container,false);

        final FragmentManager fm=getActivity().getFragmentManager();
        final waiting_dialog_fragment p =new waiting_dialog_fragment();

        GridView gd =(GridView)viewer_dialog.findViewById(R.id.waiting_dialog_grid);
        // activity fragment information Item...........................................
        area = (AutoCompleteTextView)viewer.findViewById(R.id.txt_area);
        signalarea = (AutoCompleteTextView)viewer.findViewById(R.id.txt_signal);
        post_body = (EditText) viewer.findViewById(R.id.txt_post_body);
        post_image = (ImageView)viewer.findViewById(R.id.image_loader);
        btn_jam_level = (Button)viewer.findViewById(R.id.btn_jam_level);
        txt_post_make_head = (TextView)viewer.findViewById(R.id.txt_post_make_header);
        btn_wating_time = (Button) viewer.findViewById(R.id.btm_signal_waiting_time);
        mRegProgressView = viewer.findViewById(R.id.post_process);


        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btn_wating_time.setText("ne"+position);
            }
        });

        btn_wating_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p.show(fm,DIALOG_DATE);

            }
        });
        post_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        //Font Change From Assets Folder............................................................................
        //AssetManager am = context.getApplicationContext().getAssets();
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),  "fonts/NexaRustSlab.otf");
        txt_post_make_head.setTypeface(custom_font);




        //Custom Alert Dialog Box Healper...........................................................................

        //DisplayContext displayContext;

        return viewer;
    }



//    private void showAlertDialog() {
//        // Prepare grid view
//        GridView gridView = new GridView(viewer.getContext());
//
//        List<Integer> mList = new ArrayList<Integer>();
//        for (int i = 1; i < 36; i++) {
//            mList.add(i);
//        }
//
//        gridView.setAdapter(new ArrayAdapter(viewer.getContext(), android.R.layout.simple_list_item_1, mList));
//        gridView.setNumColumns(5);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // do something here
//            }
//        });
//
//        // Set grid view to alertDialog
//        AlertDialog.Builder builder = new AlertDialog.Builder(viewer.getContext());
//        builder.setView(gridView);
//        builder.setTitle("Goto");
//        builder.show();
//    }
//

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bm;
                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(), btmapOptions);

                    bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
                    post_image.setImageBitmap(bm);
                    uploadImagePath = f.getAbsolutePath();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();

                String tempPath = getPath(selectedImageUri, getActivity());
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                post_image.setImageBitmap(bm);
                uploadImagePath = tempPath;

            }
        }
    }

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(viewer.getContext(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(viewer.getContext(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }



    private void attemptPost() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        area.setError(null);
        signalarea.setError(null);
        post_body.setError(null);


        // Store values at the time of the login attempt.
        String Area_name = area.getText().toString();
        String signal_area = signalarea.getText().toString();
        String Post_body = post_body.getText().toString();
        String jamtime = btn_wating_time.getText().toString();
        String jamlevel = btn_jam_level.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(Area_name)) {
            area.setError("Please Select Area");
            focusView = area;
            cancel = true;
        }

        // Check for User Name.
        if(TextUtils.isEmpty(signal_area))
        {
            signalarea.setError("Please Select Signal");
            focusView = signalarea;
            cancel = true;
        }
        // Check for User Name.
        if(TextUtils.isEmpty(jamtime))
        {
            btn_wating_time.setError(getString(R.string.error_user_name));
            focusView = btn_wating_time;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(jamlevel)) {
            btn_jam_level.setError(getString(R.string.error_field_required));
            focusView = btn_jam_level;
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
                mAuthTask = new UserPost(signal_area,jamtime,jamlevel,Post_body);
                mAuthTask.execute((Void) null);
        }
    }





    @SuppressWarnings("deprecation")
    public String getPath(Uri uri, Activity activity) {
        this.uri = uri;
        this.activity = activity;
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void uploadMultipart(String signalname,String jamwait,String jamlevel,String body) {
        //getting name for the image
        //String name = editText.getText().toString().trim();
        String usermobile = "01675345810";
        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(viewer.getContext(), uploadId, Server_path.POST_ITEM)
                    .addParameter("usermobile", usermobile) //Adding text parameter to the request
                    .addParameter("signalname",signalname)
                    .addParameter("waittime", jamwait) //Adding text parameter to the request
                    .addParameter("jamlevel",jamlevel)
                    .addFileToUpload(uploadImagePath, "image") //Adding file
                    .addParameter("postbody", body)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(6)
                    .startUpload(); //Starting the upload
            flag = "1";

        } catch (Exception exc) {
            Toast.makeText(viewer.getContext(), exc.getMessage(), Toast.LENGTH_SHORT).show();
            flag = "0";

        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);



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
        }
    }


    public class UserPost extends AsyncTask<Void, Void, Boolean> {

        private String signalname;
        private String jamtime;
        private String jamlevel;
        private String postbody;

        public UserPost(String signal, String jamtime, String jamlevel, String postbody) {
            this.signalname = signal;
            this.jamtime = jamtime;
            this.jamlevel = jamlevel;
            this.postbody = postbody;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            uploadMultipart(signalname,jamtime,jamlevel,postbody);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (flag == "1") {

                Toast.makeText(viewer.getContext(), "Success", Toast.LENGTH_SHORT).show();
            } else {
                //reg_password.setError(getString(R.string.error_incorrect_password));
                //reg_password.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
        }
    }





}





