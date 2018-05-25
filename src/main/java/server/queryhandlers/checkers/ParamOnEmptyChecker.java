package server.queryhandlers.checkers;

public class ParamOnEmptyChecker {
    public static boolean isEmpty(String query) {
        boolean result = true;
        if (!query.equals("???")) {
            result = false;
        }

        return result;
    }
}
