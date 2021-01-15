package com.debug.base;


public class FrameworkConfig {

    public String cacheDir = "";
    public String preferenceName = "preference";

    public boolean useUnsafeHttps = false;

    public String[] sslCertPath;

    private static class ConfigWrapper {
        private static FrameworkConfig mInstance = new FrameworkConfig();
    }

    private static class CrashLogConfigWrapper {
        private static CrashLogConfig mInstance = new CrashLogConfig();
    }

    public static FrameworkConfig getConfig() {
        return ConfigWrapper.mInstance;
    }

    public static CrashLogConfig getCrashConfig() {
        return CrashLogConfigWrapper.mInstance;
    }

    public static class CrashLogConfig {

        public boolean crashCatch = true;

        public boolean showCrashUI = true;

        public long crashFileMaxSize = 10 * 1000 * 1000;

    }
}
