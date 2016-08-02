package byteme.pictureparrot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu_layout);

        // Changes the font of the "Settings" Text View
        TextView tx1 = (TextView)findViewById(R.id.settingsTitle);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Cabin-Regular.ttf");
        tx1.setTypeface(custom_font1);

        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(SettingsMenu.this, PictureLocation.class));
                    }

                }
        );
    }
}
