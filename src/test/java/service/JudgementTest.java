package service;

import static org.junit.jupiter.api.Assertions.*;

import domain.Lotto;
import domain.Lottos;
import domain.PrizeResult;
import domain.Rank;
import domain.WinningLotto;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgementTest {

    @Test
    @DisplayName("로또 당첨 로직을 테스트한다.")
    void lotto_judgement_test() {
        // given
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7))
        ));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        PrizeResult actual = Judgement.judge(lottos, winningLotto);

        // then
        Assertions.assertThat(actual)
                .extracting("result")
                .isEqualTo(
                        Map.of(Rank.RANK1, 1,
                                Rank.RANK2, 1)
                );
    }
}