package util;

import domain.LottoNumber;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    public static Set<LottoNumber> of(Integer... numbers) {
        return Arrays.stream(numbers)
                .map(domain.LottoNumber::of)
                .collect(Collectors.toSet());
    }

}
