package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("Lottos를 생성하는 경우")
    void createLottos() {
        int lottoCount = 1;
        Lottos lottos = new Lottos(lottoCount, new RandomLottoNumberGenerator());

        assertThat(lottos).isNotNull();
    }

    @Test
    @DisplayName("보유하고 있는 로또들과 당첨 로또의 매칭 결과를 계산")
    void calculateLottoMatchResult() {
        List<LottoNumber> winningNumbers = Stream.of(12, 23, 6, 44, 17, 16)
            .map(LottoNumber::new)
            .collect(Collectors.toList());

        LottoNumberGenerator lottoNumberGenerator = () -> Stream.of(12, 23, 6, 44, 17, 16)
            .map(LottoNumber::new)
            .collect(Collectors.toList());

        Lottos lottos = new Lottos(3, lottoNumberGenerator);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, new LottoNumber(2));

        List<LottoReward> lottoRewards = lottos.calculateLottoReward(winningLotto);

        lottoRewards.forEach(lottoReward -> assertThat(lottoReward).isEqualTo(LottoReward.FIRST));
    }
}
