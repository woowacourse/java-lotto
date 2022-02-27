package lotto.util;

import lotto.domain.LottoNumber;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class IntConverter {

    private IntConverter() {
    }

    public static List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(toList());
    }
}
