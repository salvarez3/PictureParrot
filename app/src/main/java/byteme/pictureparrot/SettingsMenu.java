package byteme.pictureparrot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu_layout);

        // Changes the font of the "Settings" Text View
        TextView tx1 = (TextView)findViewById(R.id.settingsTitle);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx1.setTypeface(custom_font1);

        // CODE TO GO TO THE ABOUT SCREEN
        Button about = (Button)findViewById(R.id.about);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        about.setTypeface(custom_font2);
        about.setTransformationMethod(null);
        about.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(SettingsMenu.this, About.class));
                    }
                }
        );

        ImageButton settingsToPictureLocation = (ImageButton)findViewById(R.id.settingsToPictureLocation);
        settingsToPictureLocation.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(SettingsMenu.this, PictureLocation.class));
                    }

                }
        );
    }
}
