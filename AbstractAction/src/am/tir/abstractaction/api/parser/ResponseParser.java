package am.tir.abstractaction.api.parser;

import android.os.Handler;
import android.os.Message;

public abstract class ResponseParser {

    public static final int MSG_OK = 0;
    public static final int MSG_NO_CONNECTION = 1;
    public static final int MSG_TIMEOUT = 2;
    public static final int MSG_IO_ERROR = 3;
    public static final int MSG_PARSE_ERROR = 4;

    public static final String RESULT = "result";

    protected Handler handler;

    public ResponseParser(Handler handler) {
        this.handler = handler;
    }

    public void sendErrorMessage(int conde) {
        Message msg = Message.obtain(handler, conde);
        handler.sendMessage(msg);
    }

    public abstract void processData(byte[] data);
}
