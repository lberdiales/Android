package smadm.microteam.kiosco;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SmadmToast {
    public static int DEBUG = R.drawable.ic_debug;
    public static int INFORMATION = R.drawable.ic_information;
    public static int WARNING = R.drawable.ic_warning;
    public static int ERROR = R.drawable.ic_error;

    public static int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static int LENGTH_LONG = Toast.LENGTH_LONG;

    public static void message(Activity theActivity, int theType, String theMessage, int theDuration) {
        showMessage(theActivity, theType, theMessage, theDuration, Gravity.CENTER_VERTICAL);
    }

    public static void message(Activity theActivity, int theType, String theMessage, int theDuration, int theGravity) {
        showMessage(theActivity, theType, theMessage, theDuration, theGravity);
    }

    private static void showMessage(Activity theActivity, int theType, String theMessage, int theDuration, int theGravity) {
        LayoutInflater aInflater = theActivity.getLayoutInflater();
        View aLayout = aInflater.inflate(R.layout.toast_layout, (ViewGroup) theActivity.findViewById(R.id.toast_layout_root));

        TextView aTextView = (TextView) aLayout.findViewById(R.id.toast_layout_text);
        aTextView.setCompoundDrawablesWithIntrinsicBounds(theType, 0, 0, 0);
        aTextView.setText(theMessage);

        Toast toast = new Toast(theActivity.getApplicationContext());
        toast.setGravity(theGravity, 0, 0);
        toast.setDuration(theDuration);
        toast.setView(aLayout);
        toast.show();
    }
}
