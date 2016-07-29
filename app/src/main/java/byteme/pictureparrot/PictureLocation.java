package byteme.pictureparrot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PictureLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_location_layout);



        // Changes the font of the "Choose Image Location" Text View
        TextView tx1 = (TextView)findViewById(R.id.imageLocation);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Cabin-Regular.ttf");
        tx1.setTypeface(custom_font1);

        // Changes the font of the "Select Image from Gallery" Text View
        TextView tx2 = (TextView)findViewById(R.id.galleryText);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx2.setTypeface(custom_font2);

        // Changes the font of the "Take New Picture" Text View
        TextView tx3 = (TextView)findViewById(R.id.newImageText);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx3.setTypeface(custom_font3);

        ImageView newPictureImage = (ImageView)findViewById(R.id.newPictureImage);
        newPictureImage.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(PictureLocation.this, SettingsMenu.class));
                    }

                }
        );

        ImageButton settingsButton = (ImageButton)findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(PictureLocation.this, SettingsMenu.class));
                    }

                }
        );
    }
}
