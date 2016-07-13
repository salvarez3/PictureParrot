package byteme.pictureparrot;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by iamse on 11/07/2016.
 */
public class FragmentTwo extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_two_layout, container, false);

        // Changes the font of the "Automatically overlay quotes.." Text View
        TextView txt = (TextView) v.findViewById(R.id.textView3);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Chewy.ttf");
        txt.setTypeface(font);

        Button tapToStart = (Button) v.findViewById(R.id.tapToStart);

        tapToStart.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(getActivity(), PictureLocation.class);
                        startActivity(intent);
                    }
                }
        );
        return v;

    }
}
