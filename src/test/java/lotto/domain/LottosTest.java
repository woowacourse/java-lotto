package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.LottoTest.createCustomLotto;
import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    /*TODO:
     *  생성자 관련 테스트 추가 (예외 처리)*/

    @DisplayName("가지고 있는 로또와 당첨번호를 비교해서 결과를 제대로 반환 하는지")
    @Test
    void testEntireLottoMatching() {
        Lotto lotto1 = createCustomLotto("1, 2, 3, 19, 20, 40");
        Lotto lotto2 = createCustomLotto("1, 2, 20, 25, 29, 45");

        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

        Lotto winningNumbers = createCustomLotto("1, 2, 3, 4, 5, 6");
        LottoNumber bonusNumber = new LottoNumber(20);
        List<Result> results = lottos.getResults(new WinningLotto(winningNumbers, bonusNumber));

        List<Result> expectedResults = Arrays.asList(Result.FIFTH, Result.NONE);

        assertThat(results).isEqualTo(expectedResults);
    }
}
