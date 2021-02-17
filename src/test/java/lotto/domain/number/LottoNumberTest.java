package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {
    @DisplayName("로또번호 생성 테스트")
    @Test
    void 로또번호_생성_테스트() {
        int testNumber = 1;
        LottoNumber testLottoNumber = LottoNumber.createLottoNumber(testNumber);

        assertThat(testLottoNumber).isEqualTo(LottoNumber.createLottoNumber(testNumber));
    }
}