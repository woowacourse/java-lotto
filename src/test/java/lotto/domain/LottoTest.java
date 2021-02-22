package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {

    @DisplayName("6개 아닌 로또 번호 생성 오류 확인")
    @Test
    void lottoLengthCheck() {
        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(1, 2));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 6개의 번호가 있어야합니다.");

    }

    @DisplayName("유효하지 않는 로또 번호 생성 오류 확인")
    @Test
    void validateCheck() {
        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(-1, 2, 3, 20, 21, 40));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 번호입니다.");

        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(100, 2, 3, 20, 21, 40));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 번호입니다.");
    }

    @DisplayName("보너스 번호 일치여부 확인")
    @Test
    void bonusMatch() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40));
        int bonusNumber = 20;
        int notBonusNumber = 22;

        assertTrue(lotto.isBonusMatch(bonusNumber));
        assertFalse(lotto.isBonusMatch(notBonusNumber));
    }


}
