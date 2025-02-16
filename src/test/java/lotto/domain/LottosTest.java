package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {
    @DisplayName("구입 개수만큼 로또를 생성한다")
    @Test
    void lottosCreationTest() {
        int quantity = 7;
        Lottos lottos = Lottos.ofSize(quantity);

        // TODO: 사이즈 체크하기
        assertThat(lottos).isNotNull()
                .isInstanceOf(Lottos.class);
    }

    @DisplayName("")
    @Test
    void calculatePrizesTest() {
        Lotto winningNumbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        Lotto lotto1 = Lotto.of(List.of(1, 2, 3, 4, 5, 6)); // 1등
        Lotto lotto2 = Lotto.of(List.of(1, 2, 3, 4, 5, 7)); // 2둥
        Lotto lotto3 = Lotto.of(List.of(1, 2, 3, 4, 5, 8)); // 3둥
        Lotto lotto4 = Lotto.of(List.of(1, 2, 3, 4, 9, 10)); // 4둥
        Lotto lotto5 = Lotto.of(List.of(11, 12, 13, 4, 5, 6)); // 5둥
        Lotto lotto6 = Lotto.of(List.of(11, 12, 13, 14, 5, 6)); // 6둥

        List<Lotto> myLotto = List.of(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6);
        Lottos lottos = Lottos.of(myLotto);

        List<Prize> prizes = lottos.calculatePrizes(winningLotto);

        assertThat(prizes).isEqualTo(Arrays.stream(Prize.values()).toList());
    }
}
