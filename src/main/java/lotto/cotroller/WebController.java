package lotto.cotroller;

import spark.Request;
import spark.Response;

import java.sql.SQLException;

public interface WebController {
    String page(Request request, Response response) throws SQLException;
}
