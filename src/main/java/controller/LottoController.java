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
        int manualLottoCount = parseLottoCount(InputView.requestManualLottoCount(), userBalance);
        LottoTickets lottoTickets = initCustomerLottoTickets(userBalance, manualLottoCount);
        WinningLotto winningLotto = initWinningLotto();
        LottoGame lottoGame = new LottoGame(lottoTickets, winningLotto);
        OutputView.printLottoResults(lottoGame.getResultStatistics());
        OutputView.printProfitRatio(lottoGame.calculateProfitRatio(userBalance.getUserBalance()));
    }

    private LottoTickets initCustomerLottoTickets(UserBalance userBalance, int manualLottoCount) {
        LottoTickets lottoTickets = new LottoTickets();
        OutputView.printManualLottoNumbersMessage();
        for (int i = 0; i < manualLottoCount; i++) {
            lottoTickets.add(createLottoTicket());
        }
        lottoTickets.purchaseBy(userBalance, manualLottoCount);
        OutputView.printPurchaseInfo(manualLottoCount, lottoTickets.getLottoTickets());
        return lottoTickets;
    }

    private int parseLottoCount(String value, UserBalance userBalance) {
        int lottoCount = parseNumber(value);
        validateLottoCount(lottoCount, userBalance);
        return lottoCount;
    }

    private int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구매할 로또 수는 숫자여야 합니다.");
        }
    }

    private void validateLottoCount(int lottoCount, UserBalance userBalance) {
        if (lottoCount < 0 || lottoCount > userBalance.getUserBalance() / 1000) {
            throw new IllegalArgumentException("수동 입력 가능 횟수는 0번 이상 최대 구입 가능 이하여야 합니다.");
        }
    }

    private WinningLotto initWinningLotto() {
        OutputView.printWinningNumbersMessage();
        LottoTicket winningNumbers = createLottoTicket();
        LottoNumber bonusNumber = createBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private LottoTicket createLottoTicket() {
        String winningNumbersInput = InputView.requestLottoNumbers();
        return LottoTicket.createManualLotto(Arrays.stream(winningNumbersInput.split(DELIMITER))
                .map(LottoNumber::of)
                .collect(Collectors.toList()));
    }

    private LottoNumber createBonusNumber() {
        String bonusNumberInput = InputView.requestBonusNumber();
        return LottoNumber.of(bonusNumberInput);
    }
}
