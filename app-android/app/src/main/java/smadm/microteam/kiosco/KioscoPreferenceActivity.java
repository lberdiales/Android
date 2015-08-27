package smadm.microteam.kiosco;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class KioscoPreferenceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new KioscoPreferenceFragment()).commit();
    }

    public static class KioscoPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preference);
        }
    }
}
