package smadm.microteam.kiosco;

public class WebserviceSMADM extends WebserviceRest {
    private static final String TAG = "WebserviceSMADM";

    private static final String VALIDATE_USER_SERVICE = "afil_por_dni.php";
    public static final int VALIDATE_USER = 1;
    public static final String VALIDATE_USER_SERVICE_DNI = "dni";

    public WebserviceSMADM(int theEnvironment) {
        super(theEnvironment, WebserviceRest.WS_TYPE_SMADM);
    }

    public String getServiceUrl(int theService) {
        switch(theService) {
            case VALIDATE_USER:
                return VALIDATE_USER_SERVICE;
            default:
                return null;
        }
    }
}
