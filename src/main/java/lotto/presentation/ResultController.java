package lotto.presentation;

import lotto.service.ResultDTO;
import lotto.service.ResultService;
import spark.Request;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static lotto.presentation.Utils.render;

public class ResultController {
    public static final String RESULT_URL = "/result/:roundId";

    public String get(Request req) throws SQLException {
        int thisRoundId = Integer.parseInt(req.params("roundId"));
        ResultDTO result = ResultService.getResultByRoundId(thisRoundId);
        Map<String, Object> model = new HashMap<>();
        model.put("result", result);
        return render(model, "result.html");
    }
}
