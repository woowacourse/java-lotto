package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    /*TODO:
    *  생성자 관련 테스트 추가 (예외 처리)*/

    @DisplayName("가지고 있는 로또와 당첨번호를 비교해서 결과를 제대로 반환 하는지")
    @Test
    void testEntireLottoMatching() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40));
        Lotto lotto2 = new Lotto(Arrays.asList(1, 2, 20, 25, 29, 45));
        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 20;
        List<Result> results = lottos.getResults(new WinningLotto(winningNumbers, bonusNumber));

        List<Result> expectedResults = Arrays.asList(Result.FIFTH, Result.NONE);

        assertThat(results).isEqualTo(expectedResults);
    }

    @DisplayName("로또들을 만들어 주는 테스트")
    @Test
    void makeLottos() {
        Lottos lottos = new Lottos(3);

        assertThat(lottos.getNumberOfLotto()).isEqualTo(3);
    }
}
