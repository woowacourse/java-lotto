package lotto.domain;

import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    void 같은_정수값으로_LottoNumber_생성한_경우_비교하기() {
        assertThat(new LottoNumber(3)).isEqualTo(new LottoNumber(3));
    }

    @Test
    void 로또_숫자의_범위를_벗어난_경우() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            new LottoNumber(0);
        });
        assertThrows(InvalidLottoNumberException.class, () -> {
            new LottoNumber(46);
        });
    }
}