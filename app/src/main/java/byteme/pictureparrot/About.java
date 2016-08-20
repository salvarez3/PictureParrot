package byteme.pictureparrot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Scroller;
import android.widget.TextView;

import org.w3c.dom.Text;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        // CODE TO CHANGE FONT OF "About Picture Parrot" AND "Picture Parrot description" TEXTVIEWS
        TextView aboutText = (TextView) findViewById(R.id.aboutText);
        TextView aboutTitle = (TextView) findViewById(R.id.aboutTitle);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        aboutText.setTypeface(custom_font1);
        aboutTitle.setTypeface(custom_font1);

        aboutText.setText("Picture Parrot is a dynamic application that allows users to customise " +
                "an image of their choosing, either from the camera or their gallery. The app can " +
                "get quotes from the following categories: Random, Inspirational, Sports, Life, " +
                "Funny, Love, Management and also the Quote of the Day which can be from any category. " +
                "The user is also able to enter their own text or quote if they wish. Once a quote " +
                "has been chosen by the user, it will be overlaid onto their selected image. " +
                "The quote can be moved around the screen by pressing, holding and dragging the quote. " +
                "To allow further customisation of the quote, Picture Parrot features change font, " +
                "change text size and change text colour functionality. Once the user is happy with their " +
                "customised image, they can proceed to the sharing and save screen. This screen allows " +
                "the user to share the image they've created with many apps, including: " +
                "Whatsapp, Messenger, Gmail and more. When the save image button is pressed, the user " +
                "will be informed where the image has been stored on their device. From this same screen, " +
                "the user can choose to start the customisation process again with a new image.\n\n© 2016 Byteme");

        // CODE TO GO BACK TO THE SETTINGS SCREEN FROM THE ABOUT SCREEN
        ImageButton aboutToSettings = (ImageButton)findViewById(R.id.aboutToSettings);
        aboutToSettings.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        startActivity(new Intent(About.this, SettingsMenu.class));
                    }
                }
        );
    }


}

        /*aboutText.setText("Picture Parrot is a dynamic application that automatically finds famous " +
                "quotations based on object information extracted from user supplied images. " +
                "It then tastefully overlays the text on the desired image and invites the user " +
                "to share their expression with others via the applications sharing functions. " +
                "\\n Â© 2016 Byteme â„¢");*/
