package byteme.pictureparrot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class PictureLocation extends Activity {

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ImageButton takePhoto;
    private ImageButton fromGallery;
    private ImageView ivImage;
    private String userChosenTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_location_layout);

        // Changes the font of the "Choose Image Location" Text View
        TextView tx1 = (TextView)findViewById(R.id.imageLocation);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Cabin-Regular.ttf");
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

        // CODE FOR GETTING A BUTTON TO DO THE TAKE PHOTO CODE
        takePhoto = (ImageButton) findViewById(R.id.takePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        ivImage = (ImageView) findViewById(R.id.ivImage);

        // CODE FOR GETTING A BUTTON TO DO THE GET IMAGE FROM GALLERY CODE
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
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
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
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);


        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

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
        ivImage.setImageBitmap(thumbnail);
    }


    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ivImage.setImageBitmap(bm);
    }
}