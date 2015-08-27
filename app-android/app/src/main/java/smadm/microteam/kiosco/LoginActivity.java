package smadm.microteam.kiosco;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager; //se hace por style
import android.content.Intent;
import com.loopj.android.http.*;
import org.apache.http.Header;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends Activity implements SmadmKeyboardFragment.SmadmKeyboardListener {
    private static final String TAG = "LoginActivity";

    private SmadmKeyboardFragment mSmadmKeyboardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //Seteos de pantalla
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //se hace por style

        //Inicializar las preferencias
        KioscoPreference.initializeKioscoSharedPreferences(LoginActivity.this);

        // Get a reference to the SmadmKeyboardFragment
        mSmadmKeyboardFragment = (SmadmKeyboardFragment) getFragmentManager().findFragmentById(R.id.fLogin); //con fragment estatico
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

    // Interfaz para la accion de fin de entrada del teclado definida en el fragment SmadmKeyboardFragment
    @Override
    public void onFinishKeyboardEntry(String theKeyboardEntry) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onFinishKeyboardEntry(" + theKeyboardEntry + ")");

        if(getResources().getString(R.string.codigo_administrador).equals(theKeyboardEntry))
            mostrarPreferencias();
        else
            validarUsuario(theKeyboardEntry);
    }

    private void mostrarPreferencias() {
        Log.i(TAG, getClass().getSimpleName() + ":entered mostrarPreferencias()");
        Intent aKioscoPreferenceActivityIntent = new Intent(LoginActivity.this, KioscoPreferenceActivity.class);
        startActivityForResult(aKioscoPreferenceActivityIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityResult()");
        KioscoPreference.loadPreferences();
    }

    private void validarUsuario(String theDni) {
        Log.i(TAG, getClass().getSimpleName() + ":entered validarUsuario(" + theDni + ")");
        final String aKeyboardEntry = theDni;

        WebserviceSMADM aWebserviceSMADM = new WebserviceSMADM(KioscoPreference.getAmbiente());
        RequestParams aRequestParams = new RequestParams(WebserviceSMADM.VALIDATE_USER_SERVICE_DNI, aKeyboardEntry);

        aWebserviceSMADM.get(WebserviceSMADM.VALIDATE_USER, aRequestParams, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
                Log.i(TAG, "Antes de llamar al webservice: " + this.getRequestURI());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                // called when response HTTP status is "200 OK"
                Log.i(TAG, "LLamado al webservice OK: " + response);

                //if (!response.equals("***<br>")) {
                if (!response.equals(getResources().getString(R.string.ws_smadm_error))) {
                    String aString = response.substring(0, response.length() - 4); //<br>
                    String[] anStringParts = aString.split("\\*");

                    Intent aOptionsActivityIntent = new Intent(LoginActivity.this, OptionsActivity.class);
                    Afiliado anAfiliado = new Afiliado(Integer.parseInt(aKeyboardEntry), anStringParts[1], anStringParts[2], anStringParts[0]);
                    anAfiliado.packageIntent(aOptionsActivityIntent);
                    startActivity(aOptionsActivityIntent);
                } else {
                    SmadmToast.message(LoginActivity.this, SmadmToast.WARNING, "DNI " + aKeyboardEntry + " no encontrado", SmadmToast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e(TAG, "LLamado al webservice ERROR: " + errorResponse);
                SmadmToast.message(LoginActivity.this, SmadmToast.ERROR, "No se pudo consultar DNI " + aKeyboardEntry, SmadmToast.LENGTH_LONG);
            }
        });
    }

    //Lamentablemente para obtener los eventos de las Key debe ser a nivel de Activity, por lo
    //tanto no puedo hacerlo en el fragment
    //TODO: Revisar si se puede solucionar implmentando onKeyUp sobre la vista del DNI en el fragment
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //Android envia los eventos como [key][modificador/es][key][modificador/es]
        //                           ej: [a][SHIFT][2][SHIFT][CTRL]
        //Log.i(TAG, getClass().getSimpleName() + ":entered onKeyUp()");

        //Solo mando los caracteres, no tomo en cuenta los modificadores porque vienen implicitos en el evento
        if(!KeyEvent.isModifierKey(keyCode))
            mSmadmKeyboardFragment.processCardReaderInput(keyCode, event);

        return super.onKeyUp(keyCode, event);
    }

    /*@Override
    public void onBackPressed() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onBackPressed()");
    }*/
}
