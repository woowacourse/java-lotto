package lotto.domain;

import lotto.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @DisplayName("로또 번호 일치 테스트")
    @Test
    void equalNumber() {
        assertThat(new LottoNumber(32)).isEqualTo(new LottoNumber(32));
    }

    @DisplayName("올바르지 않은 로또 번호 범위 테스트")
    @Test
    void invalidBound() {
        assertThatThrownBy(() -> {
            new LottoNumber(0);
        }).isInstanceOf(InvalidLottoNumberException.class);
    }
}