package smadm.microteam.kiosco;

import com.loopj.android.http.*;

public interface IWebserviceRest {
    public String getServiceUrl(int theService);

    //To retrieve a resource, use GET.
    public void get(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler);

    //To create a resource on the server, use POST.
    public void post(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler);

    //To remove or delete a resource, use DELETE.
    public void delete(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler);

    //To change the state of a resource or to update it, use PUT.
    public void put(int theService, RequestParams params, AsyncHttpResponseHandler responseHandler);
}
