package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lotto.domain.LottoRanking;
import lotto.domain.Result;
import lotto.domain.factory.LottoFactory;

public class LottosTest {

    @Nested
    @DisplayName("순위 결과를 알려주는 기능은")
    class GetResult {
        @Nested
        @DisplayName("당첨 번호가 주어지면")
        class Context_with_winning_lotto {
            @Test
            @DisplayName("순위 결과를 알려준다.")
            void it_returns_result() {
                Lotto winninglotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                Number bonusNumber = new Number(7);
                WinningLotto winningLotto = new WinningLotto(winninglotto, bonusNumber);
                List<Lotto> lottoList = new ArrayList<>();
                lottoList.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
                lottoList.add(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
                Lottos lottos = new Lottos(lottoList);
                Result result = lottos.getResult(winningLotto);
                assertThat(result.getCount(LottoRanking.FIRST)).isEqualTo(1);
                assertThat(result.getCount(LottoRanking.SECOND)).isEqualTo(1);

            }
        }
    }
}
