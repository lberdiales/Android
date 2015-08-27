package smadm.microteam.kiosco;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public abstract class WebserviceRest implements IWebserviceRest {
    public static final int PROD = 1;
    public static final int TEST = 2;

    protected static final int WS_TYPE_SMADM = 3;
    protected static final int WS_TYPE_KIOSCO = 4;

    protected final int mEnvironment;
    protected final int mWsType;
    protected final String mDomain;

    protected static final AsyncHttpClient client = new AsyncHttpClient();

    public WebserviceRest(int theEnvironment, int theWsType) {
        mEnvironment = theEnvironment;
        mWsType = theWsType;
        mDomain = getDomain();
    }

    protected String getDomain() {
        return KioscoPreference.getDominio(mWsType);
    }

    protected String getAbsoluteUrl(String theUrlService) {
        return mDomain + '/' + theUrlService;
    }

    public void get(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(getServiceUrl(theService)), params, responseHandler);
    }

    public void post(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(getServiceUrl(theService)), params, responseHandler);
    }

    public void delete(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.delete(getAbsoluteUrl(getServiceUrl(theService)), params, responseHandler);
    }

    public void put(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.put(getAbsoluteUrl(getServiceUrl(theService)), params, responseHandler);
    }
}
