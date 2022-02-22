package lotto;

import lotto.model.Lotto;
import lotto.model.LottoMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMatcherTest {

    @Test
    @DisplayName("당첨 번호를 비교한다")
    void matchNumber() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        LottoMatcher matcher = new LottoMatcher(Arrays.asList(1,2,3,4,44,45), 6);
        int actual = matcher.match(lotto);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("보너스 번호 당첨 여부를 확인한다")
    void matchBonusNumberTest() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        LottoMatcher matcher = new LottoMatcher(Arrays.asList(1,2,3,4,44,45), 5);
        boolean actual = matcher.matchBonus(lotto);

        assertThat(actual).isTrue();
    }
}
