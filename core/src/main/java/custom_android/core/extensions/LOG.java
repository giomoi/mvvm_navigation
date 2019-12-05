package custom_android.core.extensions;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import androidx.annotation.NonNull;

public class LOG {

    // ログを出力するかどうか
    public static boolean DEBUG;

    // デバッグログをSDカードに出力するかどうか
    private static final boolean PUTLOG_STORAGE = false;

    private static int MAX_SIZE = 1000;

    public static void initialize(boolean debug) {
        DEBUG = debug;
    }

    public static void v(String tag, String msg) {
        if (tag == null || msg == null) return;
        if (DEBUG) {
            if (PUTLOG_STORAGE) {
                put(tag, msg);
            }
            logVAlot(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (tag == null || msg == null) return;
        if (DEBUG) {
            if (PUTLOG_STORAGE) {
                put(tag, msg);
            }
            logIAlot(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (tag == null || msg == null) return;
        if (DEBUG) {
            if (PUTLOG_STORAGE) {
                put(tag, msg);
            }
            logDAlot(tag, msg);
        }
    }

    public static void d(@NonNull String tag, @NonNull String[] keys, @NonNull String[] values) {
        String msg = createMessage(keys, values);
        d(tag, msg);
    }

    public static void e(@NonNull String tag, @NonNull String[] keys, @NonNull String[] values) {
        String msg = createMessage(keys, values);
        e(tag, msg);
    }

    public static void e(@NonNull String tag, Throwable e) {
        if (DEBUG) {
            e(tag, e.getMessage(), e);
        }
    }

    public static void e(String tag, String s, Throwable e) {
        if (DEBUG) {
            e(tag, s);
            e.printStackTrace();
        }
    }

    public static void w(String tag, String msg) {
        if (tag == null || msg == null) return;
        if (DEBUG) {
            if (PUTLOG_STORAGE) {
                put(tag, msg);
            }
            logWAlot(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (tag == null || msg == null) return;
        if (DEBUG) {
            if (PUTLOG_STORAGE) {
                put(tag, msg);
            }
            logEAlot(getTag(tag), msg);
        }
    }

    /**
     * Get Tag in specific form:
     *
     * @return
     */
    public static String getTag(String identityTag) {
        StringBuilder tag = new StringBuilder();
        tag.append(identityTag);
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        for (int i = 0; i < ste.length; i++) {
            if (ste[i].getMethodName().equals("getTag")) {
                tag.append("(")
                        .append(ste[i + 1].getFileName())
                        .append(":")
                        .append(ste[i + 1].getLineNumber())
                        .append(")");
            }
        }
        return tag.toString();
    }

    private final static String LOGDIR = Environment.getExternalStorageDirectory().getPath() + "/logs/";
    private final static String SDFILE = LOGDIR + "LOG.txt";
    private static boolean enable = true;

    /**
     * スタックトレースから呼び出し元の基本情報を取得。
     *
     * @return <<className#methodName:lineNumber>>
     */
    private static String getStackTraceInfo() {
        // 現在のスタックトレースを取得。
        // 0:VM 1:スレッド 2:getStackTraceInfo() 3:outputLog() 4:logDebug()等 5:呼び出し元
        StackTraceElement element = Thread.currentThread().getStackTrace()[5];
        StackTraceElement elementParent = Thread.currentThread().getStackTrace()[6];

        String fullName = element.getClassName();
        String className = fullName.substring(fullName.lastIndexOf(".") + 1);
        String methodName = element.getMethodName();
        String fileName = element.getFileName();
        int lineNumber = element.getLineNumber();
        String fullNameParent = elementParent.getClassName();
        String classNameParent = fullNameParent.substring(fullNameParent.lastIndexOf(".") + 1);
        String methodNameParent = elementParent.getMethodName();

        return "<<" + className + "#" + methodName + /*"@" + fileName +*/ ":" + lineNumber + " from " + classNameParent + "#" + methodNameParent + ">> ";
    }

    static private void put(String tag, String text) {
        if (!enable) return;
        Date now = new Date();
        BufferedWriter bw;
        text = getStackTraceInfo() + text;
        try {
            mkdir_p(LOGDIR);

            FileOutputStream file = new FileOutputStream(SDFILE, true);
            bw = new BufferedWriter(new OutputStreamWriter(
                    file, "UTF-8"));
            bw.append(String.valueOf(now.getYear() + 1900)).append("/").append(String.valueOf(now.getMonth() + 1)).append("/").append(String.valueOf(now.getDate())).append(" ").append(String.valueOf(now.getHours())).append(":").append(String.valueOf(now.getMinutes())).append(":").append(String.valueOf(now.getSeconds())).append("\t").append(text).append("\n");
            logDAlot(tag, text);

            bw.close();
        } catch (IOException e) {
            e(tag, e.getMessage());
        }
    }

    private static void logDAlot(String tag, String longMess) {
        for (int i = 0; i <= longMess.length() / MAX_SIZE; i++) {
            int start = i * MAX_SIZE;
            int end = (i + 1) * MAX_SIZE;
            end = end > longMess.length() ? longMess.length() : end;
            Log.d(tag, longMess.substring(start, end));
        }
    }

    private static void logEAlot(String tag, String longMess) {
        for (int i = 0; i <= longMess.length() / MAX_SIZE; i++) {
            int start = i * MAX_SIZE;
            int end = (i + 1) * MAX_SIZE;
            end = end > longMess.length() ? longMess.length() : end;
            Log.e(tag, longMess.substring(start, end));
        }
    }

    private static void logVAlot(String tag, String longMess) {
        for (int i = 0; i <= longMess.length() / MAX_SIZE; i++) {
            int start = i * MAX_SIZE;
            int end = (i + 1) * MAX_SIZE;
            end = end > longMess.length() ? longMess.length() : end;
            Log.v(tag, longMess.substring(start, end));
        }
    }

    private static void logIAlot(String tag, String longMess) {
        for (int i = 0; i <= longMess.length() / MAX_SIZE; i++) {
            int start = i * MAX_SIZE;
            int end = (i + 1) * MAX_SIZE;
            end = end > longMess.length() ? longMess.length() : end;
            Log.v(tag, longMess.substring(start, end));
        }
    }

    private static void logWAlot(String tag, String longMess) {
        for (int i = 0; i <= longMess.length() / MAX_SIZE; i++) {
            int start = i * MAX_SIZE;
            int end = (i + 1) * MAX_SIZE;
            end = end > longMess.length() ? longMess.length() : end;
            Log.w(tag, longMess.substring(start, end));
        }
    }

    public static boolean mkdir_p(File dir) throws IOException {
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("File.mkdirs() failed.");
            }
            return true;

        } else if (!dir.isDirectory()) {
            throw new IOException("Cannot client path. " + dir.toString() + " already exists and is not a directory.");

        } else {
            return false;
        }
    }

    public static boolean mkdir_p(String dir) throws IOException {
        return mkdir_p(new File(dir));
    }

    private static String createMessage(String[] keys, String[] values) {
        if (keys.length != values.length)
            throw new IllegalStateException("Keys length ("
                    + keys.length + ") is different from Values length ("
                    + values.length + ")");
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = 0; i < keys.length; i++) {
            builder.append(keys[i]).append(" = ").append(values[i]).append(",\n");
        }
        builder.delete(builder.lastIndexOf(",\n"), builder.length());
        return builder.toString();
    }
}
