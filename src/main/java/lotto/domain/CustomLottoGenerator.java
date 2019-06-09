package lotto.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomLottoGenerator implements LottoGenerator {
    private final int[] userNumbers;

    public CustomLottoGenerator(int[] userNumbers) {
        this.userNumbers = userNumbers;
    }

    @Override
    public Lotto makeLotto() {
        return new Lotto(Arrays.stream(userNumbers)
                .mapToObj(LottoNumber::generateNumber)
                .collect(Collectors.toSet()));
    }
}