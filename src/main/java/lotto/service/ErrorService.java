package lotto.service;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class ErrorService {

    private ErrorService() {
    }

    public static Object exception(Request req, Response res){
        Map<String, Object> model = new HashMap<>();
        model.put("message", req.queryParams("message"));
        return render(model, "error.html");
    }
}
