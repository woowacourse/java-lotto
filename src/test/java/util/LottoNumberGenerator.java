package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    public static List<domain.LottoNumber> of(Integer... numbers) {
        return Arrays.stream(numbers)
                .map(domain.LottoNumber::of)
                .collect(Collectors.toList());
    }

}
