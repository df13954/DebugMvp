package sj.mblog;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sj.mblog.parser.JsonParser;
import sj.mblog.parser.ObjectParser;
import sj.mblog.parser.Parser;
import sj.mblog.parser.UrlParser;

/**
 * @author dr
 * @date 2019-12-12
 * @description
 */
public class MBPrinter implements Printer {

    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;

    /**
     * classname.
     */
    protected static String MBLOG_CLASSNAME;

    protected static final int CHUNK_SIZE = 4000;

    protected LL.Builder logBuilder;

    public MBPrinter() {
        MBLOG_CLASSNAME = getClass().getPackage().getName();
    }

    public LL.Builder init() {
        if (logBuilder == null) {
            logBuilder = new LL.Builder();
        }
        if (logBuilder.parserList == null) {
            logBuilder.parserList = new LinkedList<>();
            logBuilder.parserList.add(new JsonParser());
            //logBuilder.parserList.add(new XmlParser());
            logBuilder.parserList.add(new UrlParser());
            logBuilder.parserList.add(new ObjectParser());
        }
        if (TextUtils.isEmpty(logBuilder.tag)) {
            logBuilder.tag = LL.LOG_TAG_DEFUALT;
        }
        return logBuilder;
    }

    public LL.Builder getLogBuilder() {
        return logBuilder;
    }

    @Override
    public void setLastMethodClassName(String className) {
        if (TextUtils.isEmpty(className)) {
            return;
        }
        MBLOG_CLASSNAME = className;
    }

    public void d(Object... args) {
        log(DEBUG, args);
    }

    public void e(Object... args) {
        log(ERROR, args);
    }

    public void e(Throwable throwable, Object... args) {
        if (args == null || throwable == null) {
            return;
        }
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            list.add(args[i]);
        }
        list.add(Log.getStackTraceString(throwable));
        log(ERROR, list.toArray());
    }

    public void w(Object... args) {
        log(WARN, args);
    }

    public void i(Object... args) {
        log(INFO, args);
    }

    public void v(Object... args) {
        log(VERBOSE, args);
    }

    public void wtf(Object... args) {
        log(ASSERT, args);
    }

    protected synchronized void log(int logType, Object... args) {

        if (logBuilder == null) {
            return;
        }
        if (logBuilder.printType == null) {
            logBuilder.printType = LL.PRINT.MBLOG;
        }

        if (LL.PRINT.NONE == logBuilder.printType) {
            return;
        } else if (LL.PRINT.SYSTEM == logBuilder.printType) {
            print(logType, logBuilder.tag, getContent(args));
            return;
        }

        StringBuilder builder = new StringBuilder();
        if (LL.PRINT.MBLOG == logBuilder.printType) {
            builder.append(getMethod() + "\n");
        }
        builder.append(getContent(args));
        logChunk(logType, logBuilder.tag, builder.toString());
    }

    protected String getMethod() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();

        int stackOffset = 0;
        for (int i = trace.length - 1; i >= 0; i--) {
            if (trace[i].getClassName().indexOf(MBLOG_CLASSNAME) >= 0) {
                break;
            }
            stackOffset = i;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("$ ")
                .append("(")
                .append(trace[stackOffset].getFileName())
                .append(":")
                .append(trace[stackOffset].getLineNumber())
                .append(")")
                .append(" Method: " + trace[stackOffset].getMethodName())
                .append(" Thread: " + Thread.currentThread().getName());
        return builder.toString();
    }

    protected String getContent(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (Object object : args) {
            if (object == null) {
                continue;
            }
            String content = null;
            if (logBuilder.parserList != null
                    && LL.PRINT.NONE != logBuilder.printType
                    && LL.PRINT.SYSTEM != logBuilder.printType) {
                for (Parser parser : logBuilder.parserList) {
                    content = parser.parse(object);
                    if (!TextUtils.isEmpty(content)) {
                        break;
                    }
                }
            }
            builder.append(TextUtils.isEmpty(content) ? object.toString() : content);
            builder.append("\n");
        }
        return builder.toString();
    }

    protected void logChunk(int logType, String tag, String chunk) {
        byte[] bytes = chunk.getBytes();
        int length = bytes.length;
        if (length <= CHUNK_SIZE) {
            printChunk(logType, tag, chunk);
            return;
        }
        for (int i = 0; i < length; i += CHUNK_SIZE) {
            int count = Math.min(length - i, CHUNK_SIZE);
            //create a new String with system's default charset (which is UTF-8 for Android)
            printChunk(logType, tag, new String(bytes, i, count));
        }
    }

    protected void printChunk(int logType, String tag, String chunk) {
        String[] lines = chunk.split(System.getProperty("line.separator"));
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            builder.append(line + "\n");
        }
        print(logType, tag, chunk);
    }

    protected void print(int logType, String tag, String chunk) {
        switch (logType) {
            case ERROR:
                Log.e(tag, chunk);
                break;
            case INFO:
                Log.i(tag, chunk);
                break;
            case VERBOSE:
                Log.v(tag, chunk);
                break;
            case WARN:
                Log.w(tag, chunk);
                break;
            case ASSERT:
                Log.wtf(tag, chunk);
                break;
            case DEBUG:
                // Fall through, log debug by default
            default:
                Log.d(tag, chunk);
                break;
        }
    }
}
