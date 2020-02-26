package util;

import domain.lottonumbers.lottonumber.LottoNumber;
import domain.lottonumbers.LottoNumbers;
import domain.lottonumbers.LottoNumbersDto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbersDtoGenerator {

    public static LottoNumbersDto generateManualNumbersDto(Set<Integer> numbers, int bonusNumber) {
        Set<LottoNumber> numbersSet = parseIntegersToLottoNumbers(numbers);

        return new LottoNumbersDto(new LottoNumbers(numbersSet), LottoNumber.of(bonusNumber));
    }

    private static Set<LottoNumber> parseIntegersToLottoNumbers(Set<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toSet());
    }
}