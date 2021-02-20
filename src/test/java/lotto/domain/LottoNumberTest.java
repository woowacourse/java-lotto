package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {
    @DisplayName("로또 번호가 1에서 45 사이인지 검증 하는지")
    @Test
    void LottoNumber_numberOutOfBounds_throwError() {
        assertThatThrownBy(() -> new LottoNumber(49)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("매칭이 되는 번호가 있는지 없는지에 대해 판별을 올바르게 하는지")
    @Test
    void hasAnyMatchingNumber() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(new int[] {1, 2, 3, 4, 5, 6});
        LottoNumber lottoNumber1 = new LottoNumber(4);
        LottoNumber lottoNumber2 = new LottoNumber(10);

        assertThat(lottoNumber1.hasAnyMatchingNumber(manualLottoGenerator.createLottoNumbers())).isTrue();
        assertThat(lottoNumber2.hasAnyMatchingNumber(manualLottoGenerator.createLottoNumbers())).isFalse();
    }
}
