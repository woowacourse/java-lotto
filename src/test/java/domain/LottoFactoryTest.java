package domain;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

    @Test
    @DisplayName("로또 발행 개수 테스트")
    public void success_1() {
        int input = 14000;
        Money money = new Money(input);
        LottoFactory lottoFactory = new LottoFactory(money);
        Assertions.assertThat(lottoFactory.getLottoSize()).isEqualTo(14);
    }

    @Test
    @DisplayName("로또 당첨 개수 세기 테스트")
    void lottoFactory_countLottos() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        // 로또 3개 생성 (1등, 3등, 꽝)
        LottoFactory lottoFactory = new LottoFactory(new Money(3000)) {
            @Override
            public List<Lotto> createLottos(Money money) {
                return List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 1등
                    new Lotto(List.of(1, 2, 3, 4, 5, 10)), // 3등
                    new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 꽝
                );
            }
        };

        Map<LottoRank, Integer> result = lottoFactory.countLottos(winningLotto);

        Assertions.assertThat(result.get(LottoRank.FIRST_PLACE)).isEqualTo(1);
        Assertions.assertThat(result.get(LottoRank.THIRD_PLACE)).isEqualTo(1);
        Assertions.assertThat(result.get(LottoRank.BOOM)).isEqualTo(1);
    }
}
