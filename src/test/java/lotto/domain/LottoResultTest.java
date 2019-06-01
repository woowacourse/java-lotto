package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 우승자_생성() {
        Lottos lottos = new Lottos(Arrays.asList("1,2,3,4,5,6", "3,4,5,6,7,8"), 2);
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        LottoResult lottoResult = new LottoResult(lottos, winningLotto);
        assertThat(lottoResult).isEqualTo(new LottoResult(lottos, winningLotto));
    }
}
