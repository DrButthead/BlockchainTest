import java.util.Date;

public class Logger {

    public Logger() {
    }

    private String getTimeStamp() {
        return new Date() + "";
    }

    private String formatMessage(String message, String caller, String level) {
        return getTimeStamp() + " [" + level + "] (" + caller + ") " + message;
    }

    private String getCallerMethodName() {
        StackTraceElement[] els = Thread.currentThread().getStackTrace();

        for (StackTraceElement el : els) {
            // Skip the first entry
            if ("java.base".equals(el.getModuleName())) {
                continue;
            }
            // Wait until we get out of this class
            if ("Logger".equals(el.getClassName())) {
                continue;
            }
            return el.getClassName() + "::" + el.getMethodName();
        }

        return "";
    }


    public void log(Object obj) {
        log(obj == null ? "null" : obj.toString());
    }

    public void log(String message) {
        System.out.println(formatMessage(message, getCallerMethodName(), "INFO"));
    }

    public void error(String message) {
        System.err.println(formatMessage(message, getCallerMethodName(), "ERROR"));
    }
}
