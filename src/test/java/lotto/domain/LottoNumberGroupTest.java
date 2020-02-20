package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberGroupTest {
    @Test
    @DisplayName("로또 번호 생성 테스트")
    void getInstance() {
        Assertions.assertThat(LottoNumberGroup.getInstance(1))
                .isInstanceOf(LottoNumber.class);
    }

    @Test
    @DisplayName("로또 번호 중복 생성 테스트")
    void getInstance_duplicate() {
        Assertions.assertThat(LottoNumberGroup.getInstance(1))
                .isEqualTo(LottoNumberGroup.getInstance(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 숫자 범위 밖의 입력이 들어온 경우 테스트")
    void getInstance_outOfRange(int i) {
        Assertions.assertThatThrownBy(() -> LottoNumberGroup.getInstance(i))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lotto number out of range.");
    }
}
