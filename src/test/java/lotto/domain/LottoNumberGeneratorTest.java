package lotto.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoNumberGeneratorTest {

    private static final int MAX_LOTTO_NUMBER = 46;
    private static final int MIN_LOTTO_NUMBER = 1;

    @Test
    void 만들어진_로또번호가_1과_46_사이의_값인지_확인() {
        int generatedLottoNumber = LottoNumberGenerator.generatedLottoNumber();
        for (int i = 0; i < 100; i++) {
            assertTrue(generatedLottoNumber >= MIN_LOTTO_NUMBER
                    && generatedLottoNumber <= MAX_LOTTO_NUMBER);
        }
    }

}
