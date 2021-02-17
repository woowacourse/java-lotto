package lotto.domain.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;

public class Winning {

    private Lotto lotto;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5)
            .stream()
            .map(number -> LottoNumber.valueOf(number))
            .collect(Collectors.toList());
        lotto = Lotto.generatedBy(() -> lottoNumbers);
    }

    public Winning(String inputNumber) {

    }
}
