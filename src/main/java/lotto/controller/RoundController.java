package lotto.controller;

import lotto.dao.RoundDao;
import lotto.db.DatabaseConnection;
import lotto.utils.ViewUtils;
import spark.Route;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundController {
    public static Route makeRoundPage = (req, res) -> {
        Connection conn = new DatabaseConnection().getConnection();
        RoundDao roundDao = new RoundDao(conn);
        Map<String, Object> model = new HashMap<>();
        int presentRound = roundDao.findLatestRound() + 1;
        List<Integer> rounds = roundDao.findAllRound();
        model.put("present", presentRound);
        model.put("rounds", rounds);
        model.put("message", req.queryParams("message"));
        req.session().attribute("round", presentRound);
        return ViewUtils.render(model, "home.html");
    };
}
