package controller;

import static util.LottoNumberValidator.validateNoDuplicateInList;
import static util.LottoNumberValidator.validateNoDuplicates;

import domain.LottoGame;
import domain.LottoNumber;
import domain.WinningLotto;
import domain.LottoTickets;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.LottoNumberValidator;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static final String REGEX = ", ";
    public static final String INVALID_SIZE_EXCEPTION_MESSAGE = "6개의 당첨 번호를 입력해야 합니다.";

    public void run() {
        LottoTickets lottoTickets = initCustomerLottos();
        WinningLotto referee = initLottoReferee();
        LottoGame lottoGame = new LottoGame(lottoTickets, referee);
        OutputView.printLottoResults(lottoGame.getResultStatistics());
        OutputView.printLottoResults(lottoGame.calculateProfitRatio());
    }

    private LottoTickets initCustomerLottos() {
        int money = InputView.requestUserMoney();
        LottoTickets lottoTickets = LottoTickets.purchaseBy(money);
        OutputView.printPurchaseInfo(lottoTickets.getLottoTickets());
        return lottoTickets;
    }

    private WinningLotto initLottoReferee() {
        List<LottoNumber> winningNumbers = registerWinningNumbers();
        LottoNumber bonusNumber = registerBonusNumber(winningNumbers);
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private List<LottoNumber> registerWinningNumbers() {
        String winningNumbersInput = InputView.requestWinningNumbers();
        List<LottoNumber> winningNumbers = Arrays.stream(winningNumbersInput.split(REGEX))
                .map(LottoNumberValidator::validateAndParseNumber)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(INVALID_SIZE_EXCEPTION_MESSAGE);
        }
        validateNoDuplicates(winningNumbers);

        return winningNumbers;
    }

    private LottoNumber registerBonusNumber(List<LottoNumber> winningNumbers) {
        int bonusNumber = InputView.requestBonusNumber();
        validateNoDuplicateInList(bonusNumber,
                winningNumbers.stream().map(LottoNumber::getNumber).collect(Collectors.toList()));
        return LottoNumber.of(bonusNumber);
    }
}
