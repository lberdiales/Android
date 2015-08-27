package smadm.microteam.kiosco;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;
import org.json.JSONObject;

public class OptionsActivity extends Activity implements OptionsFragment.OptionsListener {
    private static final String TAG = "OptionsActivity";

    private OptionsFragment mOptionsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_options);

        //Seteos de pantalla
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Inicializar las preferencias
        //KioscoPreference.initializeKioscoSharedPreferences(LoginActivity.this);

        // Get a reference to the OptionsFragment
        mOptionsFragment = (OptionsFragment) getFragmentManager().findFragmentById(R.id.fOptions);
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");

        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    // Interfaz para las acciones de los botones definidas en el fragment OptionsFragment
    @Override
    public void onPedirTurno(int theInstitucionId, int theDni) {
        WebserviceKIOSCO aWebserviceKIOSCO = new WebserviceKIOSCO(KioscoPreference.getAmbiente());
        RequestParams aRequestParams = new RequestParams(WebserviceKIOSCO.PEDIR_TURNO_SERVICE_ID_INSTITUCION, Integer.toString(theInstitucionId));
        aRequestParams.add(WebserviceKIOSCO.PEDIR_TURNO_SERVICE_DNI, Integer.toString(theDni));

        aWebserviceKIOSCO.get(WebserviceKIOSCO.PEDIR_TURNO, aRequestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                Log.i(TAG, "Antes de llamar al webservice: " + this.getRequestURI());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.i(TAG, "LLamado al webservice OK: " + response.toString());
                SmadmToast.message(OptionsActivity.this, (response.optInt(WebserviceKIOSCO.PEDIR_TURNO_SERVICE_RESPUESTA_ESTADO, 0) != 0) ? SmadmToast.INFORMATION : SmadmToast.WARNING, response.optString(WebserviceKIOSCO.PEDIR_TURNO_SERVICE_RESPUESTA_MENSAJE), SmadmToast.LENGTH_LONG);
                if(KioscoPreference.getTransacciones() == KioscoPreference.TRANSACCIONES_1)
                    finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e(TAG, "LLamado al webservice ERROR1: " + errorResponse);
                Log.e(TAG, "LLamado al webservice ERROR2: " + e.toString());
                SmadmToast.message(OptionsActivity.this, SmadmToast.ERROR, e.getMessage(), SmadmToast.LENGTH_LONG);
            }
        });
    }

    @Override
    public void onImprimirTurno(int theInstitucionId, int theDni) {
        WebserviceKIOSCO aWebserviceKIOSCO = new WebserviceKIOSCO(KioscoPreference.getAmbiente());
        RequestParams aRequestParams = new RequestParams(WebserviceKIOSCO.IMPRIMIR_TURNO_SERVICE_ID_INSTITUCION, Integer.toString(theInstitucionId));
        aRequestParams.add(WebserviceKIOSCO.IMPRIMIR_TURNO_SERVICE_DNI, Integer.toString(theDni));

        aWebserviceKIOSCO.get(WebserviceKIOSCO.IMPRIMIR_TURNO, aRequestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                Log.i(TAG, "Antes de llamar al webservice: " + this.getRequestURI());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.i(TAG, "LLamado al webservice OK: " + response.toString());
                SmadmToast.message(OptionsActivity.this, (response.optInt(WebserviceKIOSCO.IMPRIMIR_TURNO_SERVICE_RESPUESTA_ESTADO, 0) != 0) ? SmadmToast.INFORMATION : SmadmToast.WARNING, response.optString(WebserviceKIOSCO.IMPRIMIR_TURNO_SERVICE_RESPUESTA_MENSAJE), SmadmToast.LENGTH_LONG);
                if(KioscoPreference.getTransacciones() == KioscoPreference.TRANSACCIONES_1)
                    finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e(TAG, "LLamado al webservice ERROR1: " + errorResponse);
                Log.e(TAG, "LLamado al webservice ERROR2: " + e.toString());
                SmadmToast.message(OptionsActivity.this, SmadmToast.ERROR, e.getMessage(), SmadmToast.LENGTH_LONG);
            }
        });
    }

    @Override
    public void onAutorizacion(int theInstitucionId, int theDni) {
        WebserviceKIOSCO aWebserviceKIOSCO = new WebserviceKIOSCO(KioscoPreference.getAmbiente());
        RequestParams aRequestParams = new RequestParams(WebserviceKIOSCO.AUTORIZACION_SERVICE_ID_INSTITUCION, Integer.toString(theInstitucionId));
        aRequestParams.add(WebserviceKIOSCO.AUTORIZACION_SERVICE_DNI, Integer.toString(theDni));

        aWebserviceKIOSCO.get(WebserviceKIOSCO.AUTORIZACION, aRequestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                Log.i(TAG, "Antes de llamar al webservice: " + this.getRequestURI());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.i(TAG, "LLamado al webservice OK: " + response.toString());
                SmadmToast.message(OptionsActivity.this, (response.optInt(WebserviceKIOSCO.AUTORIZACION_SERVICE_RESPUESTA_ESTADO, 0) != 0) ? SmadmToast.INFORMATION : SmadmToast.WARNING, response.optString(WebserviceKIOSCO.AUTORIZACION_SERVICE_RESPUESTA_MENSAJE), SmadmToast.LENGTH_LONG);
                if(KioscoPreference.getTransacciones() == KioscoPreference.TRANSACCIONES_1)
                    finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e(TAG, "LLamado al webservice ERROR1: " + errorResponse);
                Log.e(TAG, "LLamado al webservice ERROR2: " + e.toString());
                SmadmToast.message(OptionsActivity.this, SmadmToast.ERROR, e.getMessage(), SmadmToast.LENGTH_LONG);
            }
        });
    }

    @Override
    public void onReintegro(int theInstitucionId, int theDni) {
        WebserviceKIOSCO aWebserviceKIOSCO = new WebserviceKIOSCO(KioscoPreference.getAmbiente());
        RequestParams aRequestParams = new RequestParams(WebserviceKIOSCO.REINTEGRO_SERVICE_ID_INSTITUCION, Integer.toString(theInstitucionId));
        aRequestParams.add(WebserviceKIOSCO.REINTEGRO_SERVICE_DNI, Integer.toString(theDni));

        aWebserviceKIOSCO.get(WebserviceKIOSCO.REINTEGRO, aRequestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                Log.i(TAG, "Antes de llamar al webservice: " + this.getRequestURI());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.i(TAG, "LLamado al webservice OK: " + response.toString());
                SmadmToast.message(OptionsActivity.this, (response.optInt(WebserviceKIOSCO.REINTEGRO_SERVICE_RESPUESTA_ESTADO, 0) != 0) ? SmadmToast.INFORMATION : SmadmToast.WARNING, response.optString(WebserviceKIOSCO.REINTEGRO_SERVICE_RESPUESTA_MENSAJE), SmadmToast.LENGTH_LONG);
                if(KioscoPreference.getTransacciones() == KioscoPreference.TRANSACCIONES_1)
                    finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e(TAG, "LLamado al webservice ERROR1: " + errorResponse);
                Log.e(TAG, "LLamado al webservice ERROR2: " + e.toString());
                SmadmToast.message(OptionsActivity.this, SmadmToast.ERROR, e.getMessage(), SmadmToast.LENGTH_LONG);
            }
        });
    }

    @Override
    public void onCancelar() {
        finish();
    }
}
