package smadm.microteam.kiosco;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class KioscoPreference {
    private static String TAG = "KioscoPreference";

    public static int TRANSACCIONES_1 = 1;
    public static int TRANSACCIONES_INF = 0;

    private static Context mContext = null;
    private static SharedPreferences mSharedPreferences = null;

    private static int AMBIENTE;
    private static int TRANSACCIONES;
    private static Map<Integer, Map<Integer, String>> DOMINIOS;
    private static int ID_INSTITUCION;

    private static Map<Integer, Map<Integer, String>> createDomainsMap() {
        Map<Integer, Map<Integer, String>> aResult = new HashMap<Integer, Map<Integer, String>>();

        aResult.put(WebserviceRest.PROD, new HashMap<Integer, String>());
        aResult.get(WebserviceRest.PROD).put(WebserviceRest.WS_TYPE_SMADM, mSharedPreferences.getString(mContext.getResources().getString(R.string.preference_categWsSMADM_prod_key), ""));
        aResult.get(WebserviceRest.PROD).put(WebserviceRest.WS_TYPE_KIOSCO, mSharedPreferences.getString(mContext.getResources().getString(R.string.preference_categWsKIOSCO_prod_key), ""));
        aResult.put(WebserviceRest.TEST, new HashMap<Integer, String>());
        aResult.get(WebserviceRest.TEST).put(WebserviceRest.WS_TYPE_SMADM, mSharedPreferences.getString(mContext.getResources().getString(R.string.preference_categWsSMADM_test_key), ""));
        aResult.get(WebserviceRest.TEST).put(WebserviceRest.WS_TYPE_KIOSCO, mSharedPreferences.getString(mContext.getResources().getString(R.string.preference_categWsKIOSCO_test_key), ""));

        return Collections.unmodifiableMap(aResult);
    }

    public static int getAmbiente() {
        return AMBIENTE;
    }

    public static int getTransacciones() {
        return TRANSACCIONES;
    }

    public static String getDominio(int theWsType) {
        return DOMINIOS.get(AMBIENTE).get(theWsType);
    }

    public static int getIdInstitucion() {
        return ID_INSTITUCION;
    }

    public static void loadPreferences() {
        //TODO: Los default values se pueden especificar en la preference.xml, estaria bueno tomarlos desde ahi directamente
        AMBIENTE = Integer.parseInt(mSharedPreferences.getString(mContext.getResources().getString(R.string.preference_categAmbiente_key), new Integer(WebserviceRest.PROD).toString()));
        TRANSACCIONES = Integer.parseInt(mSharedPreferences.getString(mContext.getResources().getString(R.string.preference_categTransacciones_key), new Integer(TRANSACCIONES_1).toString()));
        DOMINIOS = createDomainsMap();
        ID_INSTITUCION = Integer.parseInt(mSharedPreferences.getString(mContext.getResources().getString(R.string.preference_categInstitucion_key), new Integer(0).toString()));
    }

    public static void initializeKioscoSharedPreferences(Context theContext) {
        if(mContext == null)
            mContext = theContext;
        if(mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            loadPreferences();
        }
    }
}
