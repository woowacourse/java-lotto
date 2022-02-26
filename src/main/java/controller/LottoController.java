package controller;

import static domain.LottoGame.LOTTO_PRICE;
import static validator.NumberValidators.validateNoDuplicateInList;
import static validator.NumberValidators.validateNoDuplicates;
import static validator.NumberValidators.validateTotalLottoPriceUnit;
import static validator.NumberValidators.validateLottoNumbersSize;
import static view.InputView.requestManualLottoCount;
import static view.InputView.requestManualLottos;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoReferee;
import domain.Lottos;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.NumberValidators;

public class LottoController {

    public static final String LOTTO_NUMBERS_DELIMITER = ", ";

    public Lottos initCustomerLottos(int totalLottoPrice) {
        validateTotalLottoPriceUnit(totalLottoPrice);

        int totalCount = totalLottoPrice / LOTTO_PRICE;
        int manualCount = requestManualLottoCount();

        return initManualAndRandomLottos(totalCount, manualCount);
    }

    private Lottos initManualAndRandomLottos(int totalCount, int manualCount) {
        int randomCount = totalCount - manualCount;

        if (manualCount <= 0) {
            return Lottos.ofRandom(randomCount);
        }

        List<Lotto> manualLottos = getValidManualLottos(requestManualLottos(manualCount));
        return Lottos.of(manualLottos, randomCount);
    }

    private List<Lotto> getValidManualLottos(List<String> manualStrings) {
        return manualStrings
                .stream()
                .map(this::getValidLottoNumbers)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    public LottoReferee initLottoReferee(String winningNumbersInput, int bonusBallValue) {
        List<LottoNumber> winningNumbers = registerWinningNumbers(winningNumbersInput);
        LottoNumber bonusNumber = registerBonusNumber(winningNumbers, bonusBallValue);

        return new LottoReferee(winningNumbers, bonusNumber);
    }

    private List<LottoNumber> registerWinningNumbers(String winningNumbersInput) {
        List<LottoNumber> winningNumbers = getValidLottoNumbers(winningNumbersInput);

        validateNoDuplicates(winningNumbers);

        return winningNumbers;
    }

    private LottoNumber registerBonusNumber(List<LottoNumber> winningNumbers, int bonusBallValue) {
        LottoNumber bonusNumber = LottoNumber.of(bonusBallValue);

        validateNoDuplicateInList(bonusNumber, winningNumbers);

        return bonusNumber;
    }

    private List<LottoNumber> getValidLottoNumbers(String lottoNumbersInput) {
        List<LottoNumber> lottoNumbers = parseLottoNumbers(lottoNumbersInput);

        validateLottoNumbersSize(lottoNumbers);

        return lottoNumbers;
    }

    private List<LottoNumber> parseLottoNumbers(String lottoNumbersInput) {
        return Arrays.stream(lottoNumbersInput.split(LOTTO_NUMBERS_DELIMITER))
                .map(NumberValidators::validateAndParseNumber)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }
}
