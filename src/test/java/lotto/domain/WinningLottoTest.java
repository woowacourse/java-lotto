package lotto.domain;

import lotto.util.LottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {

    @DisplayName("당첨번호 생성 확인")
    @Test
    void correctWinningNumber() {
        List<Integer> winningNumber = Arrays.asList(1, 2, 3, 4, 5, 6);

        LottoNumber lottoNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumber), lottoNumber);


        assertThat(winningLotto).isEqualTo(new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(7)));
    }

    @DisplayName("로또 당첨결과 확인")
    @Test
    void testEntireLottoMatching() {
        List<Lotto> manualLottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 20, 21, 40)),
                new Lotto(Arrays.asList(1, 2, 20, 25, 29, 45))
        );

        LottoFactory manualLotto = LottoFactory.of(manualLottos);
        LottoFactory autoLotto = LottoFactory.of(0);

        WinningLotto winningLotto = new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(20));
        List<Result> results = winningLotto.getWinningResult(manualLotto, autoLotto);

        List<Result> expectedResults = Arrays.asList(Result.FIFTH, Result.NONE);

        assertThat(results).isEqualTo(expectedResults);
    }
}
