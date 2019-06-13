package lotto.controller;

import lotto.dao.RoundDao;
import lotto.service.LottoService;

import java.util.HashMap;
import java.util.Map;

public class InitialViewController {
    private static final InitialViewController INSTANCE = new InitialViewController();

    LottoService lottoService;

    private InitialViewController() {
        lottoService = LottoService.getInstance();
    }

    public static InitialViewController getInstance() {
        return INSTANCE;
    }

    public Map<String, Object> processMain() {
        Map<String, Object> model = new HashMap<>();
        RoundDao roundDao = RoundDao.getInstance();
        model.put("current_turn", roundDao.findNext());
        model.put("turns", roundDao.findAll());
        return model;
    }
}
