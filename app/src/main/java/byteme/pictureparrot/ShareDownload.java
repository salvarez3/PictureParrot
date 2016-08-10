package byteme.pictureparrot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class ShareDownload extends AppCompatActivity {

    private ImageView finalImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_download_layout);

        finalImage = (ImageView) findViewById(R.id.finalImage);

        // GETTING THE MODIFIED IMAGE FROM INTERNAL STORAGE AND DISPLAYING IT IN THE IMAGEVIEW
        File file = new File(android.os.Environment.getExternalStorageDirectory(), "PictureParrot-modified.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        finalImage.setImageBitmap(bitmap);


    }
}
