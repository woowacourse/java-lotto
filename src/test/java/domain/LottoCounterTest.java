package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoCounterTest {

    @DisplayName("LottoCounter 객체를 생성한다")
    @Test
    void testInitLottoCounter() {
        //given
        int totalLottoNumber = 3;
        int manualLottoNumber = 1;

        //when
        LottoCounter lottoCounter = LottoCounter.of(totalLottoNumber, manualLottoNumber);

        //then
        assertThat(lottoCounter).isNotNull();
        assertThat(lottoCounter.getNumberOfAutoLotto()).isEqualTo(2);
        assertThat(lottoCounter.getNumberOfManualLotto()).isEqualTo(1);
    }

    @DisplayName("수동로또 갯수가 총 로또 갯수보다 많으면 예외가 발생한다")
    @Test
    void testInitLottoCountIfManualLottoNumberOverThanTotalLottoNumber() {
        //given
        int totalLottoNumber = 2;
        int manualLottoNumber = 3;

        //when //then
        assertThatThrownBy(() -> LottoCounter.of(totalLottoNumber, manualLottoNumber))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
