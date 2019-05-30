package lotto.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomLottoGenerator {
    public static Lotto makeLotto(String[] userNumbers) {
        return new Lotto(Arrays.stream(userNumbers)
                .map(number -> LottoNumber.getNumber(Integer.parseInt(number)))
                .collect(Collectors.toSet()));
    }
}