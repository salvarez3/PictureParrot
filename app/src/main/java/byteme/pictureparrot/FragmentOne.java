package byteme.pictureparrot;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by iamse on 11/07/2016.
 */
public class FragmentOne extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_one_layout, container, false);

        // Changes the font of the "Picture Parrot App Title" Text View
        TextView txt = (TextView) v.findViewById(R.id.appTitle);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Pacifico.ttf");
        txt.setTypeface(font);

        return v;
    }
}
