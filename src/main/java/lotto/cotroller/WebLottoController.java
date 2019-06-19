package lotto.cotroller;

import lotto.WebUILottoApplication;
import lotto.domain.dao.RoundDAO;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WebLottoController implements WebController {
    public String page(Request request, Response response) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        model.put("searchCount", RoundDAO.searchMaxCount());
        return WebUILottoApplication.render(model, "lotto.html");
    }
}
