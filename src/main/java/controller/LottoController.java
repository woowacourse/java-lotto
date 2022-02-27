package controller;

import static domain.LottoGame.LOTTO_PRICE;
import static validator.NumberValidators.validateManualLottosCount;
import static validator.NumberValidators.validateNoDuplicateInList;
import static validator.NumberValidators.validateNoDuplicates;
import static validator.NumberValidators.validateTotalLottoPriceUnit;
import static validator.NumberValidators.validateLottoNumbersSize;

import domain.Lotto;
import dto.LottoCountsDto;
import domain.LottoNumber;
import domain.LottoReferee;
import domain.Lottos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.NumberValidators;

public class LottoController {

    private static final String LOTTO_NUMBERS_DELIMITER = ", ";

    public LottoCountsDto initCountsDto(int totalLottoPrice, int manualsCount) {
        validateTotalLottoPriceUnit(totalLottoPrice);
        int totalCount = totalLottoPrice / LOTTO_PRICE;

        validateManualLottosCount(manualsCount, totalCount);
        int autosCount = totalCount - manualsCount;

        return new LottoCountsDto(manualsCount, autosCount);
    }

    public Lottos initLottos(List<String> manualsString, LottoCountsDto countsDto) {
        List<Lotto> manualLottos = getValidManuals(manualsString);
        return Lottos.of(manualLottos, countsDto);
    }

    private List<Lotto> getValidManuals(List<String> manualStrings) {
        if (manualStrings == null) {
            return new ArrayList<>();
        }

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
