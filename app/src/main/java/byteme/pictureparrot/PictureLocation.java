package byteme.pictureparrot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class
PictureLocation extends Activity {

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageButton takePhoto;
    private ImageButton fromGallery;
    private ImageView ivImage;
    private String userChosenTask;

    private String pictureImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_location_layout);

        // Changes the font of the "Choose Image Location" Text View
        TextView tx1 = (TextView)findViewById(R.id.imageLocation);
        //Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Cabin-Regular.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx1.setTypeface(custom_font1);

        // Changes the font of the "Select Image from Gallery" Text View
        TextView tx2 = (TextView)findViewById(R.id.selectFromGallery);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx2.setTypeface(custom_font2);

        // Changes the font of the "Take New Picture" Text View
        TextView tx3 = (TextView)findViewById(R.id.takeNewPicture);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx3.setTypeface(custom_font3);

        // CODE FOR BUTTON THAT GOES TO THE SETTINGS MENU
        ImageButton settingsButton = (ImageButton)findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(PictureLocation.this, SettingsMenu.class));
                    }

                }
        );

        // CODE FOR GETTING BUTTON TO DO THE TAKE PHOTO CODE
        takePhoto = (ImageButton) findViewById(R.id.takePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        ivImage = (ImageView) findViewById(R.id.ivImage);

        // CODE FOR GETTING BUTTON TO DO THE GET IMAGE FROM GALLERY CODE
        fromGallery = (ImageButton) findViewById(R.id.fromGallery);
        fromGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fromGallery();
            }
        });
        ivImage = (ImageView) findViewById(R.id.ivImage);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent()
    {

        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //String imageFileName = timeStamp + ".jpg";
        String imageFileName = "PictureParrot-source.jpg";
        //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStorageDirectory();
        //pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        File file = new File(pictureImagePath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);

        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(intent, REQUEST_CAMERA);
    }


    private void takePhoto()
    {
        boolean result = Utility.checkPermission(PictureLocation.this);
        userChosenTask = "Take Photo";
        if (result)
            cameraIntent();
    }


    private void fromGallery()
    {
        boolean result = Utility.checkPermission(PictureLocation.this);
        userChosenTask = "Choose from Library";
        if (result)
            galleryIntent();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA) {
                File imgFile = new  File(pictureImagePath);
                if(imgFile.exists()){
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    Bitmap bm = Bitmap.createScaledBitmap(myBitmap, 539, 720, true);
                    bm.compress(Bitmap.CompressFormat.PNG, 80, bytes);
                    File destination = new File(Environment.getExternalStorageDirectory(), "PictureParrot-source.jpg");

                    FileOutputStream fo;
                    try {
                        destination.createNewFile();
                        fo = new FileOutputStream(destination);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(this, ImageModification.class);
                    startActivity(intent);
                }


            }

        }
    }

    /*// THIS IS WHERE THE BITMAP FILE IS GENERATED WHEN TAKING A PHOTO
    private void onCaptureImageResult(Intent data) {
        //BitmapFactory.Options options = new BitmapFactory.Options();
           // options.inScaled = false;
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, bytes);

        //File destination = new File(Environment.getExternalStorageDirectory(),
                //  System.currentTimeMillis() + ".jpg");

        File destination = new File(Environment.getExternalStorageDirectory(), "PictureParrot-source.jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, ImageModification.class);
        startActivity(intent);
    } */

    // THIS IS WHERE THE BITMAP FILE IS GENERATED WHEN SELECTING FROM THE GALLERY
    private void onSelectFromGalleryResult(Intent data) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());

                int width = bm.getWidth();
                int height = bm.getHeight();
                if (width >= 2444 && height >= 3268) {
                    Bitmap bm2 = Bitmap.createScaledBitmap(bm, 539, 720, true);
                    bm2.compress(Bitmap.CompressFormat.PNG, 80, bytes);
                } else if (width >= 600 && height >= 800) {
                    Bitmap bm2 = Bitmap.createScaledBitmap(bm, width / 2, height / 2, true);
                    bm2.compress(Bitmap.CompressFormat.PNG, 80, bytes);
                } else {
                    bm.compress(Bitmap.CompressFormat.PNG, 80, bytes);
                }

                File destination = new File(Environment.getExternalStorageDirectory(), "PictureParrot-source.jpg");

                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(this, ImageModification.class);
        startActivity(intent);
    }
}