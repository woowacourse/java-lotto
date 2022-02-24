package controller;

import static constant.LottoConstants.LOTTO_PRICE;
import static validator.NumberValidators.validateNoDuplicateInList;
import static validator.NumberValidators.validateNoDuplicates;
import static validator.NumberValidators.validateTotalLottoPriceUnit;
import static validator.NumberValidators.validateWinningNumbersSize;

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

    public static final String WINNING_NUMBERS_DELIMITER = ", ";

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
        List<LottoNumber> winningNumbers = getWinningNumbers(InputView.requestWinningNumbers());

        validateWinningNumbersSize(winningNumbers);
        validateNoDuplicates(winningNumbers);

        return winningNumbers;
    }

    private List<LottoNumber> getWinningNumbers(String winningNumbersInput) {
        return Arrays.stream(winningNumbersInput.split(WINNING_NUMBERS_DELIMITER))
                .map(NumberValidators::validateAndParseNumber)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    private LottoNumber registerBonusNumber(List<LottoNumber> winningNumbers) {
        LottoNumber bonusNumber = LottoNumber.of(InputView.requestBonusNumber());

        validateNoDuplicateInList(bonusNumber, winningNumbers);

        return bonusNumber;
    }
}
