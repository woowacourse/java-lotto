package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoManualGeneratorTest {
    @Test
    void 수동생성() {
        Lotto lottoNumbers = new Lotto(LottoManualGenerator.generate("1,2,3,4,5,6"));
        assertThat(lottoNumbers).isEqualTo(new Lotto(Arrays.asList(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3), LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6))));
    }

    @Test
    void 로또숫자_6개_아닌경우() {
        assertThrows(IllegalArgumentException.class, () -> LottoManualGenerator.generate("1,2,3,4,5"));
    }

    @Test
    void 입력이_잘못된_경우() {
        assertThrows(IllegalArgumentException.class, () -> LottoManualGenerator.generate("1,2,3,4,,,5"));
    }

    @Test
    void 중복된_입력() {
        assertThrows(IllegalArgumentException.class, () -> LottoManualGenerator.generate("1,2,3,4,5,5"));
    }
}
