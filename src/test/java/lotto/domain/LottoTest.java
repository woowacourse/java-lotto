package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40));
    }

    @DisplayName("6개 아닌 로또 번호 생성 오류 확인")
    @Test
    void lottoLengthCheck(){
        assertThatThrownBy(()->{
            new Lotto(Arrays.asList(1,2));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 6개의 번호가 있어야합니다.");

    }

    @DisplayName("유효하지 않는 로또 번호 생성 오류 확인")
    @Test
    void validateCheck(){
        assertThatThrownBy(()->{
            new Lotto(Arrays.asList(-1, 2, 3, 20, 21, 40));
            new Lotto(Arrays.asList(100, 2, 3, 20, 21, 40));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 번호입니다.");
    }

    @DisplayName("당첨번호 일치 갯수 확인")
    @Test
    void countNumberOfMatch() {
        int matches = lotto.matchingCount(new WinningNumber("1, 2, 3, 4, 5, 6"));

        assertThat(matches).isEqualTo(3);
    }

    @DisplayName("보너스 번호 일치여부 확인")
    @Test
    void bonusMatch() {
        int bonusNumber = 20;
        int notBonusNumber = 22;

        assertTrue(lotto.isBonusMatch(bonusNumber));
        assertFalse(lotto.isBonusMatch(notBonusNumber));
    }


}
