package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        long purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printCountMessage(lottoService.countNumberOfPurchases(purchaseAmount));
        LottoTicket lottoTicket = lottoService.issueTicket(purchaseAmount);

        List<MatchResultDto> matchResults = getMatchResultDtos(lottoTicket);
        Map<LottoRank, Integer> winningInfo = getLottoRankIntegerMap(matchResults);

        OutputView.printWinningStatics(winningInfo);
        OutputView.printProfitRate(lottoService.calculateProfitRate(winningInfo, purchaseAmount));
    }

    private Map<LottoRank, Integer> getLottoRankIntegerMap(List<MatchResultDto> matchResults) {
        Map<LottoRank, Integer> winningInfo = new HashMap<>();

        for (MatchResultDto matchResult : matchResults) {
            LottoRank lottoRank = LottoRank.findRankWithMatchResult(matchResult);
            winningInfo.put(lottoRank, winningInfo.getOrDefault(lottoRank, 0) + 1);
        }
        return winningInfo;
    }

    private List<MatchResultDto> getMatchResultDtos(LottoTicket lottoTicket) {
        WinningNumber winningNumber = new WinningNumber(InputView.inputWinningNumbers());
        BonusNumber bonusNumber = new BonusNumber(InputView.inputBonusNumber());
        return lottoTicket.deriveMatchResults(winningNumber, bonusNumber);
    }
}
