package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoNumbersGeneratorTest {
    @Test
    void 로또번호리스트_생성하기() {
        assertThat(LottoNumbersGenerator.create(Arrays.asList(1, 2, 3)))
                .isEqualTo(Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3)));
    }
}