package lotto.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomLottoGenerator implements LottoGenerator {
    private final String[] userNumbers;

    public CustomLottoGenerator(String[] userNumbers) {
        this.userNumbers = userNumbers;
    }

    @Override
    public Lotto makeLotto() {
        return new Lotto(Arrays.stream(userNumbers)
                .map(number -> LottoNumber.generateNumber(Integer.parseInt(number)))
                .collect(Collectors.toSet()));
    }
}