package lotto.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.count.Count;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.LottoMoney;
import lotto.parser.GameParser;

public class LottoService {

    private GameParser gameParser = new GameParser();

    public LottoMoney createLottoMoney(String inputMoney) {
        int money = gameParser.parseInputToInt(inputMoney);
        return new LottoMoney(money);
    }

    public Count createCount(LottoMoney lottoMoney, String inputManualCount) {
        int manualCount = gameParser.parseInputToInt(inputManualCount);
        return new Count(lottoMoney.getLottoPurchaseCounts(), manualCount);
    }

    public LottoTickets createManualLottoTickets(List<String> lottoTicketInput) {
        List<Set<Integer>> lottoTicketsNumbers  = lottoTicketInput.stream()
                .map(gameParser::parseInputToNumbers)
                .collect(Collectors.toList());

        return LottoFactory.publishLottoTicketsFrom(lottoTicketsNumbers);
    }

    public LottoTickets createAutoLottoTickets(Count count) {
        return LottoFactory.publishAutoLottoTicketsFrom(count);
    }

    public WinningLotto createWinningLotto(String inputNumbers, String inputBonusNumber) {
        Set<Integer> winningLottoNumbers = gameParser.parseInputToNumbers(inputNumbers);
        Integer bonusNumber = gameParser.parseInputToInt(inputBonusNumber);
        return LottoFactory.publishWinningLotto(winningLottoNumbers, bonusNumber);
    }
}
