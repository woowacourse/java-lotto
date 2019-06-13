package lotto.controller;

import lotto.WinningLottoService;
import lotto.domain.*;
import lotto.dto.GameResultDto;
import lotto.util.LottoDtoConverter;
import lotto.util.LottoParser;
import spark.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultController {
    private final static ResultController INSTANCE = new ResultController();

    private final LottoService lottoService;
    private final WinningLottoService winningLottoService;
    private final ResultService resultService;

    private ResultController() {
        lottoService = LottoService.getInstance();
        winningLottoService = WinningLottoService.getInstance();
        resultService = ResultService.getInstance();
    }

    public static ResultController getInstance() {
        return INSTANCE;
    }

    public Map<String, Object> processResult(final Request req) {
        Map<String, Object> model = new HashMap<>();
        WinningLotto winningLotto = makeWinningLotto(req);
        winningLottoService.add(winningLotto);
        List<Lotto> lottos = makeLottos();
        GameResultDto gameResultDto = makeGameResultDto(winningLotto, lottos);
        model.put("profit", stringifyProfit(gameResultDto));
        model.put("stat", stringifyResult(gameResultDto));
        resultService.add(gameResultDto);
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
        return lottoService.getLottos().stream()
                .map(converter::convertDtoToLotto)
                .collect(Collectors.toList());
    }

    private GameResultDto makeGameResultDto(WinningLotto winningLotto, List<Lotto> lottos) {
        GameResultMatcher gameResultMatcher = GameResultMatcher.of(lottos);
        gameResultMatcher.match(winningLotto);
        return GameResultDto.of(gameResultMatcher);
    }

    private String stringifyProfit(GameResultDto gameResultDto) {
        return String.format("%.1f", gameResultDto.getProfit());
    }

    private List<String> stringifyResult(final GameResultDto result) {
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
