package lottogame.controller;

import lottogame.domain.LottoMachine;
import lottogame.domain.TicketMachine;
import lottogame.domain.dto.LottoDto;
import lottogame.domain.dto.LottoResultsDto;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.statistic.LottoResults;
import lottogame.domain.lotto.WinningLotto;
import lottogame.domain.statistic.Rank;
import lottogame.view.InputView;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.lotto.Money;
import lottogame.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameController {
    private static final InputView inputView = new InputView(new Scanner(System.in));
    private static final LottoMachine lottoMachine = new LottoMachine();
    private static final TicketMachine ticketMachine = new TicketMachine();

    public void run() {
        Money money = new Money(inputView.inputMoney());
        int manualQuantity = inputView.inputManualQuantity();
        money = ticketMachine.buyManualTicket(money, manualQuantity);
        int autoTicketQuantity = ticketMachine.buyableAutoTicketQuantity(money);
        List<Lotto> lottoGroup = makeManualLotto(manualQuantity);
        lottoGroup.addAll(lottoMachine.buyAutoTicket(autoTicketQuantity));
        Lottos lottos = new Lottos(lottoGroup);
        OutputView.showLottos(manualQuantity, lottos.numbersOfLottos());
        LottoResults lottoResults = lottos.matchesLottos(askWinningLotto());
        LottoResultsDto lottoResultsDto = makeLottoResultsDto(lottoResults, money);
        OutputView.printResult(lottoResultsDto);
    }

    private LottoResultsDto makeLottoResultsDto(LottoResults lottoResults, Money money) {
        Map<Rank, Integer> statistics = lottoResults.makeStatistics();
        return new LottoResultsDto(statistics, lottoResults.makeProfit(statistics, money));
    }

    private List<Lotto> makeManualLotto(int manualTicketQuantity) {
        List<LottoDto> manualLotto = inputView.inputManualLotto(manualTicketQuantity);
        return lottoMachine.makeManualLotto(manualLotto);
    }

    private WinningLotto askWinningLotto() {
        List<Integer> numbers = inputView.inputWinningLottoNumbers();
        int bonusBall = inputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusBall);
    }
}
