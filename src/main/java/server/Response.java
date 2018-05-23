package server;

public class Response {
    private final long count;
    private final String response;

    public Response(long count, String response) {
        this.count = count;
        this.response = response;
    }

    public long getCount() {
        return count;
    }

    public String getResponse() {
        return response;
    }
}
