package server.queryhandlers.dataconverters;

public class QueryConverter {
    public static String[] convert(String userDataOnQuery) {
        String[] result = userDataOnQuery.split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }

        return result;
    }
}
