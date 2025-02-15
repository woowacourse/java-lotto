package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.Lotto.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또는 1과 45 사이의 번호가 아니면 예외를 던진다")
    @Test
    void 로또는_1과_45_사이의_번호가_아니면_예외를_던진다() {
        assertThatThrownBy(() -> validateLottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 %d ~ %d 사이여야 합니다.".formatted(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
    }

    @DisplayName("로또는 6개의 번호를 가진다")
    @Test
    void 로또는_6개의_번호를_가진다() {
        assertThat(new Lotto(NumbersGenerator.generateLottoNumbers()).getNumbers()).hasSize(6);
    }

    @DisplayName("로또는 중복되지 않는 숫자를 가진다")
    @Test
    void 로또는_중복되지_않는_숫자를_가진다() {
        assertThat(new Lotto(NumbersGenerator.generateLottoNumbers()).getNumbers()).doesNotHaveDuplicates();
    }
}
