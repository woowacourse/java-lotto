package controller;

import static constant.LottoConstants.LOTTO_PRICE;
import static validator.NumberValidators.validateNoDuplicateInList;
import static validator.NumberValidators.validateNoDuplicates;
import static validator.NumberValidators.validateTotalLottoPriceUnit;
import static validator.NumberValidators.validateWinningNumbersSize;

import domain.LottoNumber;
import domain.LottoReferee;
import domain.Lottos;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.NumberValidators;

public class LottoController {

    public static final String WINNING_NUMBERS_DELIMITER = ", ";

    public Lottos initCustomerLottos(int totalLottoPrice) {
        validateTotalLottoPriceUnit(totalLottoPrice);

        return Lottos.of(totalLottoPrice / LOTTO_PRICE);
    }

    public LottoReferee initLottoReferee(String winningNumbersInput, int bonusBallValue) {
        List<LottoNumber> winningNumbers = registerWinningNumbers(winningNumbersInput);
        LottoNumber bonusNumber = registerBonusNumber(winningNumbers, bonusBallValue);

        return new LottoReferee(winningNumbers, bonusNumber);
    }

    private List<LottoNumber> registerWinningNumbers(String winningNumbersInput) {
        List<LottoNumber> winningNumbers = getWinningNumbers(winningNumbersInput);

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

    private LottoNumber registerBonusNumber(List<LottoNumber> winningNumbers, int bonusBallValue) {
        LottoNumber bonusNumber = LottoNumber.of(bonusBallValue);

        validateNoDuplicateInList(bonusNumber, winningNumbers);

        return bonusNumber;
    }
}
