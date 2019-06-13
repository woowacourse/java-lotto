package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.WebUILottoApplication;
import com.woowacourse.lotto.db.dao.LottoDao;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PlayController {
    public static Object init(Request request, Response response) throws SQLException {
        // 회차의 증가
        int round = LottoDao.selectRound();
        LottoDao.addRound(round);

        request.session().attribute("round", round + 1);

        Map<String, Object> model = new HashMap<>();
        model.put("round", round + 1);
        return WebUILottoApplication.render(model, "play.html");
    }
}
