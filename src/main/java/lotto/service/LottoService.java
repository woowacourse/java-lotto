package lotto.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.count.Count;
import lotto.domain.lotto.RandomNumbersFactory;
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
        List<Set<Integer>> manualLottoTicketsNumbers  = lottoTicketInput.stream()
                .map(gameParser::parseInputToNumbers)
                .collect(Collectors.toList());
        return LottoTickets.publishLottoTickets(manualLottoTicketsNumbers);
    }

    public LottoTickets createAutoLottoTickets(Count count) {
        List<Set<Integer>> autoLottoTicketsNumbers = RandomNumbersFactory.publishAutoLottoTicketsNumbersFrom(count);
        return LottoTickets.publishLottoTickets(autoLottoTicketsNumbers);
    }

    public WinningLotto createWinningLotto(String inputNumbers, String inputBonusNumber) {
        Set<Integer> winningLottoNumbers = gameParser.parseInputToNumbers(inputNumbers);
        int bonusNumber = gameParser.parseInputToInt(inputBonusNumber);
        return new WinningLotto(winningLottoNumbers, bonusNumber);
    }
}
