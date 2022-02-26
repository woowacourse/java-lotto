package controller;

import domain.LottoGame;
import domain.LottoNumber;
import domain.LottoTicket;
import domain.UserBalance;
import domain.WinningLotto;
import domain.LottoTickets;

import java.util.Arrays;
import java.util.stream.Collectors;

import view.InputView;
import view.OutputView;

public class LottoController {
    public static final String DELIMITER = ", ";

    public void run() {
        UserBalance userBalance = new UserBalance(InputView.requestUserBalance());
        LottoTickets lottoTickets = initCustomerLottoTickets(userBalance);
        WinningLotto winningLotto = initWinningLotto();
        LottoGame lottoGame = new LottoGame(lottoTickets, winningLotto);
        OutputView.printLottoResults(lottoGame.getResultStatistics());
        OutputView.printProfitRatio(lottoGame.calculateProfitRatio(userBalance.getUserBalance()));
    }

    private LottoTickets initCustomerLottoTickets(UserBalance userBalance) {
        LottoTickets lottoTickets = LottoTickets.purchaseBy(userBalance);
        OutputView.printPurchaseInfo(lottoTickets.getLottoTickets());
        return lottoTickets;
    }

    private WinningLotto initWinningLotto() {
        LottoTicket winningNumbers = registerWinningNumbers();
        LottoNumber bonusNumber = registerBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private LottoTicket registerWinningNumbers() {
        String winningNumbersInput = InputView.requestWinningNumbers();

        return LottoTicket.createManualLotto(Arrays.stream(winningNumbersInput.split(DELIMITER))
                .map(LottoNumber::of)
                .collect(Collectors.toList()));
    }

    private LottoNumber registerBonusNumber() {
        String bonusNumberInput = InputView.requestBonusNumber();
        return LottoNumber.of(bonusNumberInput);
    }
}
