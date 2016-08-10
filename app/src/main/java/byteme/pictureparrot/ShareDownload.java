package byteme.pictureparrot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.File;

public class ShareDownload extends AppCompatActivity {

    private ImageView modifiedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_download_layout);

        modifiedImage = (ImageView) findViewById(R.id.modifiedImage);

        // GETTING THE MODIFIED IMAGE FROM INTERNAL STORAGE AND DISPLAYING IT IN THE IMAGEVIEW
        File file = new File(android.os.Environment.getExternalStorageDirectory(), "PictureParrot-modified.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        modifiedImage.setImageBitmap(bitmap);

        // CODE FOR SHARE OPTIONS
        ImageButton menu_item_share = (ImageButton)findViewById(R.id.menu_item_share);
        menu_item_share.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain"); //CHANGE TEXT/PLAIN TO IMAGE? --"image/jpeg"
                        String shareBody = "Here is the share content body"; //CHANGE STRING TO IMAGE AND CONTENT TO MODIFIED IMAGE?
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody); //CAN I CHANGE EXTRA_TEXT TO SUIT OUT MODIFIED IMAGE?
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    }
                }
        );

        /* // Test Code
        ImageButton menu_item_share = (ImageButton)findViewById(R.id.menu_item_share);
        menu_item_share.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("image/jpeg"); //CHANGE TEXT/PLAIN TO IMAGE? -- "image/jpeg"
                        Image shareBody = bitmap; //CHANGE STRING TO IMAGE AND CONTENT TO MODIFIED IMAGE?
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody); //CAN I CHANGE EXTRA_TEXT TO SUIT OUT MODIFIED IMAGE?

                        //TO SHARE IMAGE??
                        sharingIntent.putExtra(Intent.EXTRA_STREAM, modifiedImage); // <<< THIS IS THE CORRECT FORMAT!! --use parse?
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    }
                }
        ); */





    }
}
