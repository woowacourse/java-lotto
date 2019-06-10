package lotto.domain.lottonumber;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    void 같은_정수값으로_LottoNumber_생성한_경우_비교하기() {
        assertThat(LottoNumber.of(3)).isEqualTo(LottoNumber.of(3));
    }

    @Test
    void 로또_숫자의_범위를_벗어난_경우() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            LottoNumber.of(0);
        });
        assertThrows(InvalidLottoNumberException.class, () -> {
            LottoNumber.of(46);
        });
    }
}