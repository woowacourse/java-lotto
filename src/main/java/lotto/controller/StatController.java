package lotto.controller;

import lotto.service.LottoService;
import lotto.service.StatService;
import lotto.service.WinningLottoService;
import lotto.domain.*;
import lotto.dto.GameStatDto;
import lotto.util.LottoDtoConverter;
import lotto.util.LottoParser;
import spark.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatController {
    private final static StatController INSTANCE = new StatController();

    private final LottoService lottoService;
    private final WinningLottoService winningLottoService;
    private final StatService statService;

    private StatController() {
        lottoService = LottoService.getInstance();
        winningLottoService = WinningLottoService.getInstance();
        statService = StatService.getInstance();
    }

    public static StatController getInstance() {
        return INSTANCE;
    }

    public Map<String, Object> processResult(final Request req) {
        Map<String, Object> model = new HashMap<>();
        WinningLotto winningLotto = makeWinningLotto(req);
        winningLottoService.add(winningLotto);
        List<Lotto> lottos = makeLottos();
        GameStatDto gameStatDto = makeGameResultDto(winningLotto, lottos);
        model.put("profit", stringifyProfit(gameStatDto));
        model.put("stat", stringifyResult(gameStatDto));
        statService.add(gameStatDto);
        return model;
    }

    private WinningLotto makeWinningLotto(final Request req) {
        LottoParser parser = new LottoParser();
        final Lotto lotto = parser.parseLotto(req.queryParams("winninglotto"));
        LottoNumber lottoNumber = parser.parseLottoNumber(Integer.parseInt(req.queryParams("bonusnumber")));
        WinningLotto winningLotto = WinningLotto.of(lotto, lottoNumber);
        return winningLotto;
    }

    private List<Lotto> makeLottos() {
        LottoDtoConverter converter = new LottoDtoConverter();
        return converter.convertDtoToLottos(lottoService.getLottos());
    }

    private GameStatDto makeGameResultDto(WinningLotto winningLotto, List<Lotto> lottos) {
        GameResultMatcher gameResultMatcher = GameResultMatcher.of(lottos);
        gameResultMatcher.match(winningLotto);
        return GameStatDto.of(gameResultMatcher);
    }

    private String stringifyProfit(GameStatDto gameStatDto) {
        return String.format("%.1f", gameStatDto.getProfit());
    }

    private List<String> stringifyResult(final GameStatDto result) {
        List<String> results = new ArrayList<>();
        for (Rank rank : Rank.reverseValues()) {
            results.add(stringifyRank(rank) + result.getCount(rank) + "개");
        }
        return results;
    }

    private String stringifyRank(final Rank rank) {
        StringBuilder sb = new StringBuilder();
        sb.append(rank.getMatchCount() + "개 일치");
        if (rank == Rank.SECOND) {
            sb.append(", 보너스 볼 일치");
        }
        sb.append("(" + rank.getMoney() + ")- ");
        return sb.toString();
    }
}
