package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("상금과 수익률을 올바르게 계산하는지 테스트한다.")
public class PrizeMachineTest {

    private PrizeMachine prizeMachine;
    private WinningLotto winningLotto;

    @BeforeEach
    void init() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(List.of(1, 2, 3, 43, 44, 45)));
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 44, 45)));
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 45)));
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 12)));
        lottos.add(new Lotto(List.of(21, 22, 23, 24, 25, 26)));

        prizeMachine = new PrizeMachine(lottos);
        winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 12)), 6);
    }

    @Test
    @DisplayName("올바른 수익률이 계산된다.")
    void lottos_calculateEarningRate() {
        prizeMachine.calculatePrizeResult(winningLotto);
        double expected = 2001555000.0 / 5000.0;
        assertThat(prizeMachine.calculateEarningRate()).isEqualTo(expected);
    }

}
