package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoManualGeneratorTest {
    @Test
    void 수동생성() {
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator("1,2,3,4,5,6");
        Lotto lottoNumbers = lottoManualGenerator.generate();
        assertThat(lottoNumbers).isEqualTo(new Lotto(Arrays.asList(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3), LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6))));
    }

    @Test
    void 로또숫자_6개_아닌경우() {
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator("1,2,3,4,5");
        assertThrows(IllegalArgumentException.class, () -> lottoManualGenerator.generate());
    }

    @Test
    void 입력이_잘못된_경우() {
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator("1,2,3,4,,,5");
        assertThrows(IllegalArgumentException.class, () -> lottoManualGenerator.generate());
    }

    @Test
    void 중복된_입력() {
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator("1,2,3,4,5,5");
        assertThrows(IllegalArgumentException.class, () -> lottoManualGenerator.generate());
    }
}
