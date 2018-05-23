package server.queryhandlers.checkers;

public class QueryOnEmptyChecker {
    public static boolean isEmpty(String query) {
        boolean result = true;
        if (!query.equals("???")) {
            result = false;
        }

        return result;
    }
}
