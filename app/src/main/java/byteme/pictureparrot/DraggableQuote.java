package byteme.pictureparrot;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import java.text.AttributedCharacterIterator;

/**
 * Created by Seb on 8/08/2016.
 */

// THIS CLASS CONTAINS THE CODE THAT ALLOWS TEXTVIEWS TO BE MOVED AROUND
// THE SCREEN WHEN PRESSED AND DRAGGED
public class DraggableQuote extends FrameLayout{

    private float startingPointerX;
    private float startingPointerY;
    private float startingViewX;
    private float startingViewY;


    public DraggableQuote(Context context){
        this(context, null, 0);
    }

    public DraggableQuote(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public DraggableQuote(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                // Record x,y
                startingViewX = getX();
                startingViewY = getY();
                startingPointerX = event.getRawX();
                startingPointerY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                // Get pointer x,y
                float pointerX = event.getRawX();
                float pointerY = event.getRawY();

                // Get dx,dy
                float dx = pointerX - startingPointerX;
                float dy = pointerY - startingPointerY;

                // Get new view x,y
                float viewX = startingViewX + dx;
                float viewY = startingViewY + dy;

                // Move this view
                setX(viewX);
                setY(viewY);
                break;
        }
        return true;
    }
}
