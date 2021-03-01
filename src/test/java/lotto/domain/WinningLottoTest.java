package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {

    @DisplayName("당첨번호 생성 확인")
    @Test
    void correctWinningNumber() {
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(new Lotto("1, 2, 3, 4, 5, 6"), bonusNumber);


        assertThat(winningLotto).isEqualTo(new WinningLotto(new Lotto("1, 2, 3, 4, 5, 6"), new LottoNumber(7)));
    }

    @DisplayName("로또 당첨결과 확인")
    @Test
    void testEntireLottoMatching() {
        List<Lotto> manualLottos = Arrays.asList(
                new Lotto("1, 2, 3, 20, 21, 40"),
                new Lotto("1, 2, 20, 25, 29, 45")
        );

        Lottos lottos = new Lottos();
        lottos.buyLotto(new LottoManualGenerator(), "1, 2, 3, 20, 21, 40");
        lottos.buyLotto(new LottoManualGenerator(), "1, 2, 20, 25, 29, 45");


        WinningLotto winningLotto = new WinningLotto(new Lotto("1, 2, 3, 4, 5, 6"), new LottoNumber(20));
        List<Result> results = winningLotto.getWinningResult(lottos);

        List<Result> expectedResults = Arrays.asList(Result.FIFTH, Result.NONE);

        assertThat(results).isEqualTo(expectedResults);
    }
}
