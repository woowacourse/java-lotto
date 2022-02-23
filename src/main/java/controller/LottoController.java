package controller;

import static constant.ExceptionMessages.INVALID_WINNING_NUMBERS_SIZE_EXCEPTION_MESSAGE;
import static constant.LottoConstants.LOTTO_PRICE;
import static constant.LottoConstants.WINNING_NUMBERS_SIZE;
import static validator.NumberValidators.validateNoDuplicateInList;
import static validator.NumberValidators.validateNoDuplicates;
import static validator.NumberValidators.validateTotalLottoPriceUnit;

import domain.LottoGame;
import domain.LottoNumber;
import domain.LottoReferee;
import domain.Lottos;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.NumberValidators;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        Lottos lottos = initCustomerLottos();
        LottoReferee referee = initLottoReferee();

        LottoGame lottoGame = new LottoGame(lottos, referee);

        OutputView.printLottoResults(lottoGame.getResultStatistics());
        OutputView.printLottoResults(lottoGame.calculateProfitRatio());
    }

    private Lottos initCustomerLottos() {
        int totalLottoPrice = InputView.requestTotalLottoPrice();
        validateTotalLottoPriceUnit(totalLottoPrice);
        Lottos lottos = Lottos.of(totalLottoPrice / LOTTO_PRICE);
        OutputView.printPurchaseInfo(lottos.getLottos());
        return lottos;
    }

    private LottoReferee initLottoReferee() {
        List<LottoNumber> winningNumbers = registerWinningNumbers();
        LottoNumber bonusNumber = registerBonusNumber(winningNumbers);
        return new LottoReferee(winningNumbers, bonusNumber);
    }

    private List<LottoNumber> registerWinningNumbers() {
        String winningNumbersInput = InputView.requestWinningNumbers();
        List<LottoNumber> winningNumbers = Arrays.stream(winningNumbersInput.split(", "))
                .map(NumberValidators::validateAndParseNumber)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        if (winningNumbers.size() != WINNING_NUMBERS_SIZE) {
            throw new IllegalArgumentException(INVALID_WINNING_NUMBERS_SIZE_EXCEPTION_MESSAGE);
        }
        validateNoDuplicates(winningNumbers.stream().map(LottoNumber::getNumber).collect(Collectors.toList()));

        return winningNumbers;
    }

    private LottoNumber registerBonusNumber(List<LottoNumber> winningNumbers) {
        int bonusNumber = InputView.requestBonusNumber();
        validateNoDuplicateInList(bonusNumber, winningNumbers.stream().map(LottoNumber::getNumber).collect(Collectors.toList()));
        return LottoNumber.of(bonusNumber);
    }
}
