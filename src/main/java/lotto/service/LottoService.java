package lotto.service;

import lotto.domain.count.Count;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.LottoMoney;
import lotto.parser.GameParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<LottoTicket> lottoTickets = lottoTicketInput.stream()
                .map(gameParser::parseInputToNumbers)
                .map(LottoFactory::publishLottoTicketFrom)
                .collect(Collectors.toList());

        return new LottoTickets(lottoTickets);
    }

    public LottoTickets createAutoLottoTickets(Count count) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count.getAutoCounts(); i++) {
            lottoTickets.add(LottoFactory.publishLottoTicketOfRandom());
        }
        return new LottoTickets(lottoTickets);
    }

    public WinningLotto createWinningLotto(String inputNumbers, String inputBonusNumber) {
        Set<Integer> winningLottoNumbers = gameParser.parseInputToNumbers(inputNumbers);
        Integer bonusNumber = gameParser.parseInputToInt(inputBonusNumber);
        return LottoFactory.publishWinningLotto(winningLottoNumbers, bonusNumber);
    }
}
