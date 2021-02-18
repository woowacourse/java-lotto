package lotto.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberFactoryTest {
    @DisplayName("로또번호 생성 테스트")
    @Test
    void 로또번호_생성_테스트() {
        int testNumber = 1;
        LottoNumber testLottoNumber = LottoNumberFactory.getInstance(testNumber);

        assertThat(testLottoNumber).isEqualTo(LottoNumber.createLottoNumber(testNumber));
    }

    @DisplayName("예외 처리 : 로또번호가 1~45범위밖이면 예외가 나는지")
    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 46, 100})
    void 로또번호_범위_예외_테스트(int testNumber) {
        assertThatThrownBy(() -> LottoNumberFactory.getInstance(testNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}