package am.tir.abstractaction.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import am.tir.abstractaction.api.parser.ResponseParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SimpleHttpClient implements Runnable {

    private static final int MAX_RETRY_COUNT = 3;

    private Thread thread;
    private String requestURL;
    private ResponseParser responseParser;
    private Context context;

    public SimpleHttpClient(String requestURL, ResponseParser responseParser, Context context) {
        this.requestURL = requestURL;
        this.responseParser = responseParser;
        this.context = context;
    }

    public void execute() {
        if (thread != null) {
            throw new IllegalStateException();
        }

        boolean connected = checkConnection();
        if (connected) {
            thread = new Thread(this);
            thread.setPriority(Thread.MAX_PRIORITY); // TODO: remove
            thread.start();
        } else {
            responseParser.sendErrorMessage(ResponseParser.MSG_NO_CONNECTION);
        }
    }

    @Override
    public void run() {
        byte[] res = null;
        try {
            boolean success = false;
            boolean retry = true;
            Exception lastException = null;
            int retryCount = 1;

            while (retry) {
                try {
                    res = issueRequest();
                    success = true;
                    retry = false;
                } catch (Exception ex) {
                    lastException = ex;
                }

                if (success || retryCount == MAX_RETRY_COUNT) {
                    retry = false;

                    if (lastException != null) {
                        throw lastException;
                    }
                }
            }

            responseParser.processData(res);
        } catch (Exception e) {
            if (e instanceof IOException) {
                responseParser.sendErrorMessage(ResponseParser.MSG_IO_ERROR);
            } // TODO:  handle timeout error
        }
    }

    private boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private byte[] issueRequest() throws IOException {
        HttpClient httpClient = HttpClientProvider.getHttpClient();
        HttpGet httpGet = new HttpGet(requestURL);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        return EntityUtils.toByteArray(httpResponse.getEntity());
    }
}
