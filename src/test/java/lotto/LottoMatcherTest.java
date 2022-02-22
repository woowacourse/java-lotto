package lotto;

import lotto.model.Lotto;
import lotto.model.LottoMatcher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMatcherTest {

    @Test
    @DisplayName("당첨 번호를 비교한다")
    void matchNumber() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        LottoMatcher matcher = new LottoMatcher(Arrays.asList(1,2,3,4,44,45));
        int actual = matcher.match(lotto);

        assertThat(actual).isEqualTo(4);
    }

}
