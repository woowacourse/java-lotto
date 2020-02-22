package util;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumbers;
import domain.lottonumber.LottoNumbersDto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbersDtoGenerator {
    private static final int NUMBER_OF_NUMBERS = 6;

    public static LottoNumbersDto generateRandomTicketDto() {
        Set<LottoNumber> randomNumbers = new HashSet<>(generateShuffledNumbers().subList(0, NUMBER_OF_NUMBERS));
        LottoNumbers randomLottoNumbers = new LottoNumbers(randomNumbers);

        return new LottoNumbersDto(randomLottoNumbers);
    }

    private static List<LottoNumber> generateShuffledNumbers() {
        List<LottoNumber> lottoNumbers = LottoNumber.getAllValues();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }

    public static LottoNumbersDto generateManualNumbersDto(Set<Integer> numbers, int bonusNumber) {
        Set<LottoNumber> numbersSet = parseIntegersToLottoNumbers(numbers);

        return new LottoNumbersDto(new LottoNumbers(numbersSet), LottoNumber.valueOf(bonusNumber));
    }

    private static Set<LottoNumber> parseIntegersToLottoNumbers(Set<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet());
    }
}