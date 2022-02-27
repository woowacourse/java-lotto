import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Rewards;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("로또 당첨 1등 성공")
    void compareAllLottoTest1() {
        Lotto lotto1 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        Lotto lotto2 = new Lotto(Stream.of(1, 2, 3, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

        List<LottoNumber> winningNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = new LottoNumber(30);

        lottos.compareAllLotto(winningNumbers, bonusNumber);

        assertThat(Rewards.getCount(Rewards.FIRST_REWARD)).isEqualTo(1);
    }

    @Test
    @DisplayName("모든 로또 우승 로또와 비교하는 기능 로또 수만큼 카운트 세는지 확인하는 테스트")
    void compareAllLottosWithWinningLottoTest() {
        Lotto lotto1 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        Lotto lotto2 = new Lotto(Stream.of(1, 2, 3, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

        Lotto winningLotto = new Lotto(Stream.of(3, 4, 5, 6, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        assertThat(lottos.compareAllLottosWithWinningLotto(winningLotto).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("모든 로또 보너스넘버 포함 확인 기능 로또 수만큼 카운트 세는지 확인하는 테스트")
    void compareAllLottosWithBonusNumberTest() {
        Lotto lotto1 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        Lotto lotto2 = new Lotto(Stream.of(1, 2, 3, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

        LottoNumber bonusNumber = new LottoNumber(3);

        assertThat(lottos.compareAllLottosWithBonusNumber(bonusNumber).size()).isEqualTo(2);
    }
}
