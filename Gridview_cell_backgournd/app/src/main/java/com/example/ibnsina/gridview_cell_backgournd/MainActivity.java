package com.example.ibnsina.gridview_cell_backgournd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {


    private GridView sdcardImages;
    ArrayList<String> IPath = new ArrayList<String>();
    String imagePath;
    private ImageAdapter imageAdapter;
    GridView grid = sdcardImages;
    private Display display;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Request progress bar
            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
            setContentView(R.layout.activity_main);
            Button selpic = (Button) findViewById(R.id.gallery);
            display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

            setupViews();
            setProgressBarIndeterminateVisibility(true);
            loadImages();
       /*   sdcardImages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

              public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                             int position, long arg3) {
                  try{
//sdcardImages.setBackground(position);

                      int columnIndex = 0;
                      String[] projection = {MediaStore.Images.Media.DATA};
                      Cursor cursor = managedQuery( MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                              projection,
                              null,
                              null,
                              null);
                      if (cursor != null) {
                          columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                          cursor.moveToPosition(position);
                          imagePath = cursor.getString(columnIndex);
                      }
                      if(IPath.contains(imagePath))
                      {

                              Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show();
                          IPath.remove(imagePath);
                          Toast.makeText(getApplicationContext(),IPath.toString(),Toast.LENGTH_SHORT).show();
                      }
                      else{
                              Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_SHORT).show();
                          IPath.add(imagePath);
                          Toast.makeText(getApplicationContext(),IPath.toString(),Toast.LENGTH_SHORT).show();
                          }
                  }
                  catch (Exception e)
                  {
                      Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                  }

                  //Toast.makeText(MainActivity.this, "LONG PRESS", Toast.LENGTH_SHORT).show();
                  //set the image as wallpaper
                  return true;
              }
          });*/

            selpic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent intentMessage = new Intent(MainActivity.this, gallery.class);
                        intentMessage.putStringArrayListExtra("IMAGE", IPath);
                        startActivity(intentMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Free up bitmap related resources.
     */
    protected void onDestroy() {
        super.onDestroy();

        final int count = grid.getChildCount();
        ImageView v = null;
        for (int i = 0; i < count; i++) {
            v = (ImageView) grid.getChildAt(i);
            ((BitmapDrawable) v.getDrawable()).setCallback(null);
        }
    }

    /**
     * Setup the grid view.
     */

    private void setupViews() {
        sdcardImages = (GridView) findViewById(R.id.sdcard);
        // sdcardImages.setNumColumns(display.getWidth()/95);
        sdcardImages.setChoiceMode(2);
        sdcardImages.setClipToPadding(false);
        sdcardImages.setOnItemClickListener(MainActivity.this);
        imageAdapter = new ImageAdapter(getApplicationContext());
        sdcardImages.setAdapter(imageAdapter);

    }

    /**
     * Load images.
     */


    private void loadImages() {
        final Object data = getLastNonConfigurationInstance();
        if (data == null) {
            new LoadImagesFromSDCard().execute();
        } else {
            final LoadedImage[] photos = (LoadedImage[]) data;
            if (photos.length == 0) {
                new LoadImagesFromSDCard().execute();
            }
            for (LoadedImage photo : photos) {
                addImage(photo);
            }
        }
    }

    /**
     * Add image(s) to the grid view adapter.
     *
     * @param value Array of LoadedImages references
     */
    private void addImage(LoadedImage... value) {
        for (LoadedImage image : value) {
            imageAdapter.addPhoto(image);
            imageAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Save bitmap images into a list and return that list.

     *
     * @see Activity#onRetainNonConfigurationInstance()
     */


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /**
     * Async task for loading the images from the SD card.
     *
     * @author Mihai Fonoage
     */

    class LoadImagesFromSDCard extends AsyncTask<Object, LoadedImage, Object> {

        /**
         * Load images from SD Card in the background, and display each image on the screen.
         *
         * @see AsyncTask#
         * doInBackground(Params[])
         */

        @Override
        protected Object doInBackground(Object... params) {
            //setProgressBarIndeterminateVisibility(true);
            Bitmap bitmap = null;
            Bitmap newBitmap = null;
            Uri uri = null;

            // Set up an array of the Thumbnail Image ID column we want
            String[] projection = {MediaStore.Images.Thumbnails._ID};
            // Create the cursor pointing to the SDCard
            Cursor cursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                    projection, // Which columns to return
                    null,       // Return all rows
                    null,
                    null);
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
            int size = cursor.getCount();
            // If size is 0, there are no images on the SD Card.
            if (size == 0) {
                //No Images available, post some message to the user
            }

            int imageID = 0;
            for (int i = 0; i < size; i++) {
                cursor.moveToPosition(i);
                imageID = cursor.getInt(columnIndex);
                uri = Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID);
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    if (bitmap != null) {
                        newBitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                        bitmap.recycle();
                        if (newBitmap != null) {
                            publishProgress(new LoadedImage(newBitmap));
                        }
                    }

                } catch (IOException e) {
                    //Error fetching image, try to recover
                }
            }
            cursor.close();
            return null;
        }

        /**
         * Add a new LoadedImage in the images grid.
         *
         * @param value The image.
         */

        @Override
        public void onProgressUpdate(LoadedImage... value) {
            addImage(value);
        }

        /**
         * Set the visibility of the progress bar to false.
         *
         * @see AsyncTask#onPostExecute(Object)
         */
        @Override
        protected void onPostExecute(Object result) {
            setProgressBarIndeterminateVisibility(false);
        }
    }

    /**
     * Adapter for our image files.
     *
     * @author Mihai Fonoage
     */

    class ImageAdapter extends BaseAdapter {

        private Context mContext;
        private ArrayList<LoadedImage> photos = new ArrayList<LoadedImage>();

        public ImageAdapter(Context context) {
            mContext = context;
        }

        public void addPhoto(LoadedImage photo) {
            photos.add(photo);
        }

        public int getCount() {
            return photos.size();
        }

        public Object getItem(int position) {
            return photos.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            final ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(1, 1, 1, 1);
            imageView.setImageBitmap(photos.get(position).getBitmap());

            return imageView;
        }
    }

    /**
     * A LoadedImage contains the Bitmap loaded for the image.
     */
    private static class LoadedImage {
        Bitmap mBitmap;

        LoadedImage(Bitmap bitmap) {
            mBitmap = bitmap;
        }

        public Bitmap getBitmap() {
            return mBitmap;
        }
    }

    /**
     * When an image is clicked, load that image as a puzzle.
     */
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        try {
//sdcardImages.setBackground(position);

            int columnIndex = 0;
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
            if (cursor != null) {
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToPosition(position);
                imagePath = cursor.getString(columnIndex);
            }
            if (IPath.contains(imagePath)) {

                Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_SHORT).show();
                IPath.remove(imagePath);
                //Toast.makeText(getApplicationContext(),IPath.toString(),Toast.LENGTH_SHORT).show();
            } else {


                Toast.makeText(getApplicationContext(), "Selected", Toast.LENGTH_SHORT).show();
                IPath.add(imagePath);
                //Toast.makeText(getApplicationContext(),IPath.toString(),Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.gc();
    }
}
