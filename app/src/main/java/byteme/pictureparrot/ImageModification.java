package byteme.pictureparrot;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class ImageModification extends AppCompatActivity {

    final CharSequence [] COLOR_OPTIONS = {"BLACK", "WHITE", "RED", "BLUE", "GREEN", "YELLOW"};
    final CharSequence [] QUOTE_CATEGORIES = {"RANDOM", "FUNNY", "INSPIRATIONAL", "MOTIVATIONAL"};
    public ImageView sourceImage;
    private TextView quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_modification_layout);

        // Changes the font of the "Select Image from Gallery" Text View
        TextView tx2 = (TextView)findViewById(R.id.selectQuote);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx2.setTypeface(custom_font2);

        // Changes the font of the "Select Image from Gallery" Text View
        TextView tx = (TextView)findViewById(R.id.textColour);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx.setTypeface(custom_font);

        sourceImage = (ImageView) findViewById(R.id.sourceImage);
        quote = (TextView) findViewById(R.id.draggableQuote);

        // GETTING THE IMAGE FROM INTERNAL STORAGE AND DISPLAYING IT IN THE IMAGEVIEW
        File file = new File(android.os.Environment.getExternalStorageDirectory(), "PictureParrot.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        sourceImage.setImageBitmap(bitmap);

        // CODE TO CHOOSE A COLOUR FOR THE QUOTE TEXTVIEW
        ImageButton selectTextColour = (ImageButton)findViewById(R.id.selectTextColour);
        selectTextColour.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ImageModification.this);
                        builder.setTitle("Select Text Colour");
                        builder.setItems(COLOR_OPTIONS, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int colour) {
                                if (COLOR_OPTIONS[colour].equals("BLACK")) {
                                    quote.setTextColor(Color.BLACK);
                                } else if (COLOR_OPTIONS[colour].equals("WHITE")) {
                                    quote.setTextColor(Color.WHITE);
                                } else if (COLOR_OPTIONS[colour].equals("RED")) {
                                    quote.setTextColor(Color.RED);
                                } else if (COLOR_OPTIONS[colour].equals("BLUE")) {
                                    quote.setTextColor(Color.BLUE);
                                } else if (COLOR_OPTIONS[colour].equals("GREEN")) {
                                    quote.setTextColor(Color.GREEN);
                                } else if (COLOR_OPTIONS[colour].equals("YELLOW")) {
                                    quote.setTextColor(Color.YELLOW);
                                }
                            }
                        });
                        builder.show();
                    }
                }
        );

        // CODE TO SELECT A QUOTE FROM A CATEGORY
        ImageButton quoteSelect = (ImageButton)findViewById(R.id.quoteSelect);
        quoteSelect.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ImageModification.this);
                        builder.setTitle("Select Quote Category");
                        builder.setItems(QUOTE_CATEGORIES, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int category) {
                                if (QUOTE_CATEGORIES[category].equals("RANDOM")) {
                                    quote.setText("RANDOM QUOTE");
                                    // GET RANDOM QUOTE
                                } else if (QUOTE_CATEGORIES[category].equals("FUNNY")) {
                                    quote.setText("FUNNY QUOTE");
                                    // GET FUNNY QUOTE
                                } else if (QUOTE_CATEGORIES[category].equals("INSPIRATIONAL")) {
                                    quote.setText("INSPIRATIONAL QUOTE");
                                    // GET INSPIRATIONAL QUOTE
                                } else if (QUOTE_CATEGORIES[category].equals("MOTIVATIONAL")) {
                                    quote.setText("MOTIVATIONAL QUOTE");
                                    // GET MOTIVATIONAL QUOTE
                                }
                            }
                        });
                        builder.show();
                    }
                }
        );
}
}