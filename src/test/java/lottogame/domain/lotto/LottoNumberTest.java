package lottogame.domain.lotto;

import lottogame.utils.InvalidLottoNumberRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {
    @DisplayName("기본 생성자는 랜덤 로또 번호 6개를 생성한다")
    @Test
    void 로또_번호_생성_테스트() {
        LottoNumber lottoNumber = LottoNumber.valueOf(5);
        assertThat(lottoNumber).isEqualTo(LottoNumber.valueOf(5));
        assertThat(lottoNumber).isNotEqualTo(LottoNumber.valueOf(9));
    }

    @DisplayName("로또 번호가 범위를 넘어 가는 경우 예외 처리")
    @Test
    void 로또_번호_범위_테스트() {
        assertThrows(InvalidLottoNumberRangeException.class,
                () -> LottoNumber.valueOf(48));
        assertThrows(InvalidLottoNumberRangeException.class,
                () -> LottoNumber.valueOf(0));
    }
}