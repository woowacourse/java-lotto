package lotto.service;

import lotto.dao.RoundDao;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class MainService {
    public static Object main(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        RoundDao roundDao = new RoundDao();
        model.put("round", roundDao.getLatest() + 1);
        model.put("rounds", roundDao.findAll());
        return render(model, "start.html");
    }
}
