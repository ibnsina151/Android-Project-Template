package navdrawer.test.com.navigationdrawertest;



import android.content.Context;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by amit on 26/3/16.
 */
public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
