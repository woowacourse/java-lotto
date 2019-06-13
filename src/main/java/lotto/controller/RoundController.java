package lotto.controller;

import lotto.dao.RoundDao;
import lotto.domain.Rank;
import lotto.dto.StatDto;
import lotto.service.LottoService;
import lotto.service.StatService;
import lotto.service.TurnService;
import lotto.service.WinningLottoService;
import spark.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundController {
    private static final RoundController INSTANCE = new RoundController();
    private final String RESTART = "restart";

    private final LottoService lottoService;
    private final WinningLottoService winningLottoService;
    private final StatService statService;
    private final TurnService turnService;

    private RoundController() {
        lottoService = LottoService.getInstance();
        winningLottoService = WinningLottoService.getInstance();
        statService = StatService.getInstance();
        turnService = TurnService.getInstance();
    }

    public static RoundController getInstance() {
        return INSTANCE;
    }

    public Map<String, Object> processEndPage(final Request req) {
        Map<String, Object> model = new HashMap<>();
        if (req.queryParams("token").equals(RESTART)) {
            return backToMain(model);
        }
        return initialize(model);
    }

    private Map<String, Object> backToMain(Map<String, Object> model) {
        RoundDao.getInstance().add();
        model.put("current_turn", turnService.findNext());
        model.put("turns", turnService.findAll());
        return model;
    }

    private Map<String, Object> initialize(Map<String, Object> model) {
        statService.deleteAll();
        winningLottoService.deleteAll();
        lottoService.deleteAll();
        turnService.deleteAll();
        return model;
    }

    public Map<String, Object> processTurnInfo(final Request req) {
        Map<String, Object> model = new HashMap<>();
        int round = Integer.parseInt(req.params("index"));
        StatDto statDto = statService.findByRound(round);
        model.put("turn", round);
        model.put("lottos", lottoService.findAllByRound(round));
        model.put("winning_lotto", winningLottoService.findByRound(round));
        model.put("result", stringifyResult(statDto));
        model.put("profit", stringifyProfit(statDto));
        return model;
    }

    private List<String> stringifyResult(final StatDto statDto) {
        StatStringifier statStringifier = new StatStringifier();
        return statStringifier.stringifyResult(statDto);
    }

    private String stringifyProfit(final StatDto statDto) {
        StatStringifier statStringifier = new StatStringifier();
        return statStringifier.stringifyProfit(statDto);
    }
}
