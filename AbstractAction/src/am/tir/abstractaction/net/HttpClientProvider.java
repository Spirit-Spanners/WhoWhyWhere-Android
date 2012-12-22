package am.tir.abstractaction.net;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpClientProvider {

    private HttpClientProvider() {
    }

    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int SO_TIMEOUT = 5000;

    private static HttpClient httpClient;

    static {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
        httpClient = new DefaultHttpClient(httpParams);
    }

    public static HttpClient getHttpClient() {
        return httpClient;
    }
}
