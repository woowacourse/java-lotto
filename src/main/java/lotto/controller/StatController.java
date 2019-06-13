package lotto.controller;

import lotto.service.LottoService;
import lotto.service.StatService;
import lotto.service.WinningLottoService;
import lotto.domain.*;
import lotto.dto.StatDto;
import lotto.util.LottoDtoConverter;
import lotto.util.LottoParser;
import spark.Request;

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

        StatDto statDto = makeGameResultDto(winningLotto, lottos);
        model.put("profit", stringifyProfit(statDto));
        model.put("stat", stringifyResult(statDto));
        statService.add(statDto);
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

    private StatDto makeGameResultDto(WinningLotto winningLotto, List<Lotto> lottos) {
        GameResultMatcher gameResultMatcher = GameResultMatcher.of(lottos);
        gameResultMatcher.match(winningLotto);
        return StatDto.of(gameResultMatcher);
    }

    private String stringifyProfit(StatDto statDto) {
        StatStringifier statStringifier = new StatStringifier();
        return statStringifier.stringifyProfit(statDto);
    }

    private List<String> stringifyResult(final StatDto statDto) {
        StatStringifier statStringifier = new StatStringifier();
        return statStringifier.stringifyResult(statDto);
    }
}
