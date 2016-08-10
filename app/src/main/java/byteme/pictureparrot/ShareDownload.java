package byteme.pictureparrot;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ShareDownload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_download_layout);

        // CODE FOR SHARE OPTIONS
        ImageButton menu_item_share = (ImageButton)findViewById(R.id.menu_item_share);
        menu_item_share.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain"); //CHANGE TEXT/PLAIN TO IMAGE?
                        String shareBody = "Here is the share content body"; //CHANGE STRING TO IMAGE AND CONTENT TO MODIFIED IMAGE?
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody); //CAN I CHANGE EXTRA_TEXT TO SUIT OUT MODIFIED IMAGE?
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));

                    }
                }
        );





    }
}
