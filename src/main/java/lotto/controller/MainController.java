package lotto.controller;

import lotto.dao.RoundDao;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class MainController {
    private final RoundDao roundDao;

    public MainController(final RoundDao roundDao) {
        this.roundDao = roundDao;
    }

    public Object main(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("round", roundDao.getLatest() + 1);
        model.put("rounds", roundDao.findAll());
        return render(model, "start.html");
    }
}
