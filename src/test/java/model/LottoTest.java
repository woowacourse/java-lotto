package model;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @DisplayName("로또 한 개는 여섯 개의 숫자로 이루어져 있다")
    @Test
    void containsSixNumbersInlotto() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @DisplayName("로또 번호는 일 이상 사십오 이하의 숫자로 이루어져 있다")
    @Test
    void containsNumbersInRange() {
        Lotto lotto = new Lotto();
        for (int number : lotto.getNumbers()) {
            assertThat(number).isGreaterThanOrEqualTo(1);
            assertThat(number).isLessThanOrEqualTo(45);
        }
    }

    @DisplayName("한 개의 로또의 모든 번호는 서로 중복되지 않는다.")
    @Test
    void vertifyNoDuplicationInOneLotto() {
        Lotto lotto = new Lotto();
        Set<Integer> uniqueLotto = new HashSet<>(lotto.getNumbers());
        assertThat(uniqueLotto.size()).isEqualTo(lotto.getNumbers().size());
    }
}