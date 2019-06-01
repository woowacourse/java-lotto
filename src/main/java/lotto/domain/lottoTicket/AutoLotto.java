package lotto.domain.lottoTicket;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutoLotto extends Lotto {
    public AutoLotto() {
        super(createRandomNumbers());
    }

    public static List<LottoNumber> createRandomNumbers() {
        Set<LottoNumber> numbers = new HashSet<>();
        while (numbers.size() < MAX_LOTTO_SIZE) {
            numbers.add(new LottoNumber());
        }
        return convertLottoNumbers(numbers);
    }

    private static List<LottoNumber> convertLottoNumbers(Set<LottoNumber> numbers) {
        List<LottoNumber> convertLottoNumbers = new ArrayList<>();
        numbers.stream()
                .sorted()
                .forEach(number -> convertLottoNumbers.add(number));
        return convertLottoNumbers;
    }
}
