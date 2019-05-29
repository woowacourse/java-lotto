package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumbersTest {

    @Test
    void AllLottoNumbers_생성_확인() {
        assertThat(new Number(1).getNumber()).isEqualTo(NumberSet.of(1).getNumber());
    }

    @Test
    void LottoNumbers_번호_범위_45_초과_생성_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            NumberSet.of(46);
        });
    }

    @Test
    void LottoNumbers_번호_범위_1_미만_생성_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            NumberSet.of(0);
        });
    }
}
