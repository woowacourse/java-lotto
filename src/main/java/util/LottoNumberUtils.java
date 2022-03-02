package util;

import static validator.NumberValidators.validateLottoNumbersSize;
import static validator.NumberValidators.validateNoDuplicateInList;
import static validator.NumberValidators.validateNoDuplicates;

import domain.Lotto;
import domain.LottoNumber;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.NumberValidators;

public class LottoNumberUtils {

    private static final String LOTTO_NUMBERS_DELIMITER = ", ";

    public static List<Lotto> getValidManuals(List<String> manualStrings) {
        if (manualStrings.isEmpty()) {
            return List.of();
        }

        return manualStrings.stream()
                .map(LottoNumberUtils::getValidLottoNumbers)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    public static List<LottoNumber> getValidLottoNumbers(String lottoNumbersInput) {
        List<LottoNumber> lottoNumbers = parseLottoNumbers(lottoNumbersInput);

        validateLottoNumbersSize(lottoNumbers);

        return lottoNumbers;
    }

    private static List<LottoNumber> parseLottoNumbers(String lottoNumbersInput) {
        return Arrays.stream(lottoNumbersInput.split(LOTTO_NUMBERS_DELIMITER))
                .map(NumberValidators::validateAndParseNumber)
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    public static List<LottoNumber> registerWinningNumbers(String winningNumbersInput) {
        List<LottoNumber> winningNumbers = getValidLottoNumbers(winningNumbersInput);

        validateNoDuplicates(winningNumbers);

        return winningNumbers;
    }

    public static LottoNumber registerBonusNumber(List<LottoNumber> winningNumbers, int bonusBallValue) {
        LottoNumber bonusNumber = LottoNumber.of(bonusBallValue);

        validateNoDuplicateInList(bonusNumber, winningNumbers);

        return bonusNumber;
    }
}
