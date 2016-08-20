package byteme.pictureparrot;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageModification extends AppCompatActivity {

    final CharSequence [] COLOR_OPTIONS = {"BLACK", "WHITE", "RED", "BLUE", "GREEN", "YELLOW"};
    final CharSequence [] QUOTE_CATEGORIES = {"RANDOM", "INSPIRATIONAL", "SPORTS", "LIFE", "FUNNY", "LOVE", "MANAGEMENT", "QUOTE OF THE DAY (FREE)"};
    final CharSequence [] TEXT_SIZES = {"VERY SMALL", "SMALL", "MEDIUM", "LARGE", "EXTRA LARGE", "HUGE"};
    final CharSequence [] TEXT_FONTS = {"CABIN", "CHEWY", "BALOODA", "PACIFICO", "POIRET ONE", "INDIE FLOWER", "ORBITRON", "SHADOWS INTO LIGHT", "CINZEL"};
    public ImageView sourceImage;
    private TextView quote;
    private RelativeLayout imageLayout;
    private final String API_KEY = "7KjMYpDeZDhTh_QYtyK60geF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_modification_layout);

        // Change font of SELECT QUOTE, SELECT FONT, TEXT COLOUR, TEXT SIZE buttons
        TextView tx1 = (TextView)findViewById(R.id.selectTextColour);
        TextView tx2 = (TextView)findViewById(R.id.selectTextFont);
        TextView tx3 = (TextView)findViewById(R.id.textSizeSelect);
        TextView tx4 = (TextView)findViewById(R.id.quoteSelect);
        TextView tx5 = (TextView)findViewById(R.id.customQuote);
        TextView tx6 = (TextView)findViewById(R.id.acceptImage);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/JosefinSans-Regular.ttf");
        tx1.setTypeface(custom_font1);
        tx2.setTypeface(custom_font1);
        tx3.setTypeface(custom_font1);
        tx4.setTypeface(custom_font1);
        tx5.setTypeface(custom_font1);
        tx6.setTypeface(custom_font2);

        // CODE TO GET A CUSTOM QUOTE FROM THE USER
        final Context context = this;
        Button customQuote = (Button)findViewById(R.id.customQuote);
        customQuote.setTransformationMethod(null);

        // CODE TO GET A CUSTOM QUOTE FROM THE USER
        customQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_quote_prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

                alertDialogBuilder
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        quote.setText(userInput.getText().toString());
                                    }
                                })
                        .setNeutralButton("ADD QUOTATION MARKS",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        quote.setText("\"" + userInput.getText().toString()+ "\".");
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        sourceImage = (ImageView) findViewById(R.id.sourceImage);
        quote = (TextView) findViewById(R.id.draggableQuote);
        // SETTING THE DEFAULT QUOTE TEXT SIZE TO 19 WHEN THE TEXTVIEW APPEARS
        quote.setTextSize(TypedValue.COMPLEX_UNIT_SP,19);

        // GETTING THE IMAGE FROM INTERNAL STORAGE AND DISPLAYING IT IN THE IMAGEVIEW
        File file = new File(android.os.Environment.getExternalStorageDirectory(), "PictureParrot-source.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        sourceImage.setImageBitmap(bitmap);

        // CODE TO CHOOSE A COLOUR FOR THE QUOTE TEXTVIEW
        Button selectTextColour = (Button)findViewById(R.id.selectTextColour);
        selectTextColour.setTransformationMethod(null);
        selectTextColour.setOnClickListener(
                new Button.OnClickListener(){
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

        //CODE TO CHANGE THE FONT OF THE QUOTE
        Button selectTextFont = (Button)findViewById(R.id.selectTextFont);
        selectTextFont.setTransformationMethod(null);
        selectTextFont.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ImageModification.this);
                        builder.setTitle("Select Text Font");
                        builder.setItems(TEXT_FONTS, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int category) {
                                TextView font = (TextView)findViewById(R.id.draggableQuote);
                                if (TEXT_FONTS[category].equals("CABIN")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(),  "fonts/Cabin-Regular.ttf");
                                    font.setTypeface(customFont);
                                } else if (TEXT_FONTS[category].equals("CHEWY")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(),  "fonts/Chewy.ttf");
                                    font.setTypeface(customFont);
                                } else if (TEXT_FONTS[category].equals("BALOODA")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(),  "fonts/BalooDa-Regular.ttf");
                                    font.setTypeface(customFont);
                                } else if (TEXT_FONTS[category].equals("PACIFICO")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(),  "fonts/Pacifico.ttf");
                                    font.setTypeface(customFont);
                                } else if (TEXT_FONTS[category].equals("POIRET ONE")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(),  "fonts/PoiretOne-Regular.ttf");
                                    font.setTypeface(customFont);
                                } else if (TEXT_FONTS[category].equals("INDIE FLOWER")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/IndieFlower.ttf");
                                    font.setTypeface(customFont);
                                } else if (TEXT_FONTS[category].equals("ORBITRON")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Orbitron-Regular.ttf");
                                    font.setTypeface(customFont);
                                } else if (TEXT_FONTS[category].equals("SHADOWS INTO LIGHT")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/ShadowsIntoLight.ttf");
                                    font.setTypeface(customFont);
                                } else if (TEXT_FONTS[category].equals("CINZEL")) {
                                    Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Cinzel.ttf");
                                    font.setTypeface(customFont);
                                }
                            }
                        });
                        builder.show();
                    }
                }
        );

        // CODE TO CHANGE TEXT SIZE OF THE QUOTE
        Button textSizeSelect = (Button)findViewById(R.id.textSizeSelect);
        textSizeSelect.setTransformationMethod(null);
        textSizeSelect.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ImageModification.this);
                        builder.setTitle("Select Text Size");
                        builder.setItems(TEXT_SIZES, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int category) {
                                if (TEXT_SIZES[category].equals("VERY SMALL")) {
                                    quote.setTextSize(TypedValue.COMPLEX_UNIT_SP,9);
                                } else if (TEXT_SIZES[category].equals("SMALL")) {
                                    quote.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                                } else if (TEXT_SIZES[category].equals("MEDIUM")) {
                                    quote.setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
                                } else if (TEXT_SIZES[category].equals("LARGE")) {
                                    quote.setTextSize(TypedValue.COMPLEX_UNIT_SP,21);
                                } else if (TEXT_SIZES[category].equals("EXTRA LARGE")) {
                                    quote.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                                } else if (TEXT_SIZES[category].equals("HUGE")) {
                                    quote.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
                                }
                            }
                        });
                        builder.show();
                    }
                }
        );

        // CODE TO SELECT A QUOTE FROM A CATEGORY
        Button quoteSelect = (Button)findViewById(R.id.quoteSelect);
        quoteSelect.setTransformationMethod(null);
        quoteSelect.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ImageModification.this);
                        builder.setTitle("Select Quote Category");
                        builder.setItems(QUOTE_CATEGORIES, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int category) {
                                if (QUOTE_CATEGORIES[category].equals("RANDOM")) {
                                    quote.setGravity(Gravity.CENTER);
                                    new JSONTask().execute("http://quotes.rest/qod.json?maxlength=80&api_key="+ API_KEY);
                                }

                                else if (QUOTE_CATEGORIES[category].equals("INSPIRATIONAL")) {
                                    quote.setGravity(Gravity.CENTER);
                                    new JSONTask().execute("http://quotes.rest/qod.json?category=inspire&maxlength=80&api_key="+ API_KEY);

                                } else if (QUOTE_CATEGORIES[category].equals("SPORTS")) {
                                    quote.setGravity(Gravity.CENTER);
                                    new JSONTask().execute("http://quotes.rest/qod.json?category=sports&maxlength=80&api_key="+ API_KEY);

                                } else if (QUOTE_CATEGORIES[category].equals("LIFE")) {
                                    quote.setGravity(Gravity.CENTER);
                                    new JSONTask().execute("http://quotes.rest/qod.json?category=life&maxlength=80&api_key="+ API_KEY);

                                } else if (QUOTE_CATEGORIES[category].equals("FUNNY")) {
                                    quote.setGravity(Gravity.CENTER);
                                    new JSONTask().execute("http://quotes.rest/qod.json?category=funny&maxlength=80&api_key="+ API_KEY);

                                }  else if (QUOTE_CATEGORIES[category].equals("LOVE")) {
                                    quote.setGravity(Gravity.CENTER);
                                    new JSONTask().execute("http://quotes.rest/qod.json?category=love&maxlength=80&api_key=" + API_KEY);

                                }  else if (QUOTE_CATEGORIES[category].equals("MANAGEMENT")) {
                                    quote.setGravity(Gravity.CENTER);
                                    new JSONTask().execute("http://quotes.rest/qod.json?category=management&maxlength=80&api_key=" + API_KEY);

                                }  else if (QUOTE_CATEGORIES[category].equals("QUOTE OF THE DAY (FREE)")) {
                                    quote.setGravity(Gravity.CENTER);
                                    new JSONTask().execute("http://quotes.rest/qod.json");
                                }
                            }
                        });
                        builder.show();
                    }
                }
        );

        // CODE TO SAVE MODIFIED IMAGE TO THE USER'S DEVICE AND GO TO THE SHAREDOWNLOAD CLASS SO IT
        // CAN BE DISPLAYED
        Button acceptImage = (Button)findViewById(R.id.acceptImage);
        acceptImage.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        // CODE TO SAVE MODIFIED IMAGE HERE AND START SHAREDOWNLOAD ACTIVITY

                        imageLayout = (RelativeLayout)findViewById(R.id.modifiedImageRelativeLayout);
                        imageLayout.setDrawingCacheEnabled(true);
                        Bitmap modifiedImage = Bitmap.createBitmap(imageLayout.getDrawingCache());
                        imageLayout.setDrawingCacheEnabled(false);

                        //BitmapDrawable drawable = (BitmapDrawable) sourceImage.getDrawable();
                        //Bitmap modifiedImage = drawable.getBitmap();

                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        modifiedImage.compress(Bitmap.CompressFormat.PNG, 80, bytes);

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

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String rawQuote = buffer.toString();

                // CODE TO GET THE QUOTE WITHOUT QUOTATION MARKS BEFORE AND AFTER
                //rawQuote = rawQuote.substring(115, rawQuote.length()-10);
                //rawQuote = rawQuote.substring(rawQuote.indexOf("\"")+ 1);
                //rawQuote = rawQuote.substring(0, rawQuote.indexOf("\""));
                //return rawQuote;

                // CODE TO GET THE QUOTE WITH QUOTATION MARKS BEFORE AND AFTER
                // USING WITH QUOTATION MARKS FOR NOW.
                rawQuote = rawQuote.substring(115, rawQuote.length()-10);
                rawQuote = rawQuote.substring(rawQuote.indexOf("\"")+ 1);
                rawQuote = rawQuote.substring(0, rawQuote.indexOf("\""));
                String finalQuote = "\"" + rawQuote + "\"";
                return finalQuote;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            quote.setText(result);
        }
    }
}