package smadm.microteam.kiosco;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class WebserviceKIOSCO extends WebserviceRest {
    private static final String TAG = "WebserviceKIOSCO";

    private static final String BOTONES_TURNO = "turno";
    private static final String BOTONES_IMPRIMIR = "imprimir";
    private static final String BOTONES_AUTORIZACION = "autoriz";
    private static final String BOTONES_REINTEGRO = "reint";

    //WS Pedir turno tramite Generico
    private static final String PEDIR_TURNO_SERVICE = "index.php";
    public static final int PEDIR_TURNO = 1;
    public static final String PEDIR_TURNO_SERVICE_BOTONES = "botones";
    public static final String PEDIR_TURNO_SERVICE_ID_INSTITUCION = "id_institucion";
    public static final String PEDIR_TURNO_SERVICE_DNI = "dni";

    public static final String PEDIR_TURNO_SERVICE_RESPUESTA_ESTADO = "estado";
    public static final String PEDIR_TURNO_SERVICE_RESPUESTA_MENSAJE = "mensaje";

    //WS Imprimir turnos del dia
    private static final String IMPRIMIR_TURNO_SERVICE = "index.php";
    public static final int IMPRIMIR_TURNO = 2;
    public static final String IMPRIMIR_TURNO_SERVICE_BOTONES = "botones";
    public static final String IMPRIMIR_TURNO_SERVICE_ID_INSTITUCION = "id_institucion";
    public static final String IMPRIMIR_TURNO_SERVICE_DNI = "dni";

    public static final String IMPRIMIR_TURNO_SERVICE_RESPUESTA_ESTADO = "estado";
    public static final String IMPRIMIR_TURNO_SERVICE_RESPUESTA_MENSAJE = "mensaje";

    //WS Pedir turno tramite Autorizacion
    private static final String AUTORIZACION_SERVICE = "index.php";
    public static final int AUTORIZACION = 3;
    public static final String AUTORIZACION_SERVICE_BOTONES = "botones";
    public static final String AUTORIZACION_SERVICE_ID_INSTITUCION = "id_institucion";
    public static final String AUTORIZACION_SERVICE_DNI = "dni";

    public static final String AUTORIZACION_SERVICE_RESPUESTA_ESTADO = "estado";
    public static final String AUTORIZACION_SERVICE_RESPUESTA_MENSAJE = "mensaje";

    //WS Pedir turno tramite Reintegro
    private static final String REINTEGRO_SERVICE = "index.php";
    public static final int REINTEGRO = 4;
    public static final String REINTEGRO_SERVICE_BOTONES = "botones";
    public static final String REINTEGRO_SERVICE_ID_INSTITUCION = "id_institucion";
    public static final String REINTEGRO_SERVICE_DNI = "dni";

    public static final String REINTEGRO_SERVICE_RESPUESTA_ESTADO = "estado";
    public static final String REINTEGRO_SERVICE_RESPUESTA_MENSAJE = "mensaje";

    public WebserviceKIOSCO(int theEnvironment) {
        super(theEnvironment, WebserviceRest.WS_TYPE_KIOSCO);
    }

    public String getServiceUrl(int theService) {
        switch(theService) {
            case PEDIR_TURNO:
                return PEDIR_TURNO_SERVICE;
            case IMPRIMIR_TURNO:
                return IMPRIMIR_TURNO_SERVICE;
            case AUTORIZACION:
                return AUTORIZACION_SERVICE;
            case REINTEGRO:
                return REINTEGRO_SERVICE;
            default:
                return null;
        }
    }

    public void get(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        RequestParams theRequestParams = params;

        switch(theService) {
            case PEDIR_TURNO:
                theRequestParams.add(WebserviceKIOSCO.PEDIR_TURNO_SERVICE_BOTONES, BOTONES_TURNO);
                break;
            case IMPRIMIR_TURNO:
                theRequestParams.add(WebserviceKIOSCO.IMPRIMIR_TURNO_SERVICE_BOTONES, BOTONES_IMPRIMIR);
                break;
            case AUTORIZACION:
                theRequestParams.add(WebserviceKIOSCO.AUTORIZACION_SERVICE_BOTONES, BOTONES_AUTORIZACION);
                break;
            case REINTEGRO:
                theRequestParams.add(WebserviceKIOSCO.REINTEGRO_SERVICE_BOTONES, BOTONES_REINTEGRO);
                break;
            default:
                break;
        }

        super.get(theService, theRequestParams, responseHandler);
    }
}
