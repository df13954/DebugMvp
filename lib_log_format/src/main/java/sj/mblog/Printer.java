package sj.mblog;

public interface Printer {

    LL.Builder init();

    LL.Builder getLogBuilder();

    void setLastMethodClassName(String className);

    void d(Object... args);

    void e(Object... args);

    void e(Throwable throwable, Object... args);

    void w(Object... args);

    void i(Object... args);

    void v(Object... args);

    void wtf(Object... args);
}
