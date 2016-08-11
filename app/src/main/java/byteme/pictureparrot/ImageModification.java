package byteme.pictureparrot;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageModification extends AppCompatActivity {

    final CharSequence [] COLOR_OPTIONS = {"BLACK", "WHITE", "RED", "BLUE", "GREEN", "YELLOW"};
    final CharSequence [] QUOTE_CATEGORIES = {"RANDOM", "FUNNY", "INSPIRATIONAL", "MOTIVATIONAL"};
    public ImageView sourceImage;
    private TextView quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_modification_layout);

        // Changes the font of the "Select Quote" Text View
        TextView tx2 = (TextView)findViewById(R.id.selectQuote);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx2.setTypeface(custom_font2);

        // Changes the font of the "Text Colour" Text View
        TextView tx = (TextView)findViewById(R.id.textColour);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        tx.setTypeface(custom_font);

        sourceImage = (ImageView) findViewById(R.id.sourceImage);
        quote = (TextView) findViewById(R.id.draggableQuote);

        // GETTING THE IMAGE FROM INTERNAL STORAGE AND DISPLAYING IT IN THE IMAGEVIEW
        File file = new File(android.os.Environment.getExternalStorageDirectory(), "PictureParrot-source.jpg");
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
                                    quote.setGravity(Gravity.CENTER);
                                    quote.setText("RANDOM QUOTE");
                                    // GET RANDOM QUOTE
                                } else if (QUOTE_CATEGORIES[category].equals("FUNNY")) {
                                    quote.setGravity(Gravity.CENTER);
                                    quote.setText("FUNNY QUOTE");
                                    // GET FUNNY QUOTE
                                } else if (QUOTE_CATEGORIES[category].equals("INSPIRATIONAL")) {
                                    quote.setGravity(Gravity.CENTER);
                                    quote.setText("INSPIRATIONAL QUOTE");
                                    // GET INSPIRATIONAL QUOTE
                                } else if (QUOTE_CATEGORIES[category].equals("MOTIVATIONAL")) {
                                    quote.setGravity(Gravity.CENTER);
                                    quote.setText("MOTIVATIONAL QUOTE");
                                    // GET MOTIVATIONAL QUOTE
                                }
                            }
                        });
                        builder.show();
                    }
                }
        );

        // CODE TO SAVE MODIFIED IMAGE TO THE USER'S DEVICE AND GO TO THE SHAREDOWNLOAD CLASS SO IT
        // CAN BE DISPLAYED
        ImageButton acceptImage = (ImageButton)findViewById(R.id.acceptImage);
        acceptImage.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        // CODE TO SAVE MODIFIED IMAGE HERE AND START SHAREDOWNLOAD ACTIVITY
                        BitmapDrawable drawable = (BitmapDrawable) sourceImage.getDrawable();
                        Bitmap modifiedImage = drawable.getBitmap();

                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        modifiedImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);

                        File destination = new File(Environment.getExternalStorageDirectory(), "PictureParrot-modified.jpg");

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

                        startActivity(new Intent(ImageModification.this, ShareDownload.class));
                    }
                }
        );
}
}