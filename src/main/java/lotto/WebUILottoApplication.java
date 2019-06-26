package lotto;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final int SERVICE_PORT = 8080;
    private static final String STATIC_FILE_LOCATION = "/";
    private static final String CONTENT_JSON = "application/json";
    private static final String EMPTY_JSON = "{}";

    public static void main(final String[] args) {
        port(SERVICE_PORT);
        staticFileLocation(STATIC_FILE_LOCATION);
        init();

        post("/api/newLottos", (request, response) -> {
            System.err.println(request.body());
            response.type(CONTENT_JSON);
            return EMPTY_JSON;
        });
    }
}
