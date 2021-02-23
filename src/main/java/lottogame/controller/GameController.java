package lottogame.controller;

import lottogame.domain.LottoMachine;
import lottogame.domain.TicketMachine;
import lottogame.domain.dto.LottoDto;
import lottogame.domain.dto.LottoResultDto;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.statistic.LottoResult;
import lottogame.domain.statistic.LottoResults;
import lottogame.domain.lotto.WinningLotto;
import lottogame.view.InputView;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.Money;
import lottogame.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private static final InputView inputView = new InputView(new Scanner(System.in));
    private static final LottoMachine lottoMachine = new LottoMachine();
    private static final TicketMachine ticketMachine = new TicketMachine();

    public void run() {
        Money money = new Money(inputView.inputMoney());
        int manualQuantity = inputView.inputManualQuantity();
        money = ticketMachine.validManualTicket(money, manualQuantity);
        int autoTicketQuantity = ticketMachine.buyableAutoTicketQuantity(money);
        List<Lotto> manualLottos = makeManualLotto(autoTicketQuantity);
        List<Lotto> autoLottos = lottoMachine.buyAutoTicket(autoTicketQuantity);
        Lottos lottos = new Lottos(manualLottos, autoLottos);
        OutputView.showLottos(lottos.numbersOfLottos());
        LottoResults lottoResults = matchLottos(lottos, askWinningLotto());
        LottoResultDto lottoResultDto = lottoResults.makeStatistics(money);
        OutputView.printResult(lottoResultDto);
    }

    private List<Lotto> makeManualLotto(int autoTicketQuantity) {
        List<LottoDto> manualLotto = inputView.inputManualLotto(autoTicketQuantity);
        List<Lotto> manualLottos = lottoMachine.makeManualLotto(manualLotto);
        return manualLottos;
    }

    private LottoResults matchLottos(Lottos lottos, WinningLotto winningLotto) {
        List<LottoResult> results = lottos.matchesLottos(winningLotto);
        return new LottoResults(results);
    }

    private WinningLotto askWinningLotto() {
        List<Integer> numbers = inputView.inputWinningLottoNumbers();
        int bonusBall = inputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusBall);
    }
}
