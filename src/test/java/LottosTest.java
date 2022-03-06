import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.ManualLottoGenerator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lottos makeLottos() {
        Lottos lottos = Lottos.init();
        lottos.add(Lotto.generateLotto(new ManualLottoGenerator(List.of(1, 2, 3, 4, 5, 6))));
        lottos.add(Lotto.generateLotto(new ManualLottoGenerator(List.of(1, 2, 3, 4, 5, 6))));
        lottos.add(Lotto.generateLotto(new ManualLottoGenerator(List.of(11, 12, 13, 14, 15, 16))));
        return lottos;
    }

    @Test
    @DisplayName("모든 로또 우승 로또와 비교하는 기능 로또 수만큼 카운트 세는지 확인하는 테스트")
    void compareAllLottosWithWinningLottoTest() {
        Lottos lottos = makeLottos();
        Set<LottoNumber> winningLotto = Set.of(3, 4, 5, 6, 8, 9)
                .stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet());

        assertThat(lottos.compareAllLottosWithWinningLotto(winningLotto).size()).isEqualTo(3);
    }

    @Test
    @DisplayName("모든 로또 보너스넘버 포함 확인 기능 로또 수만큼 카운트 세는지 확인하는 테스트")
    void compareAllLottosWithBonusNumberTest() {
        Lottos lottos = makeLottos();
        LottoNumber bonusNumber = LottoNumber.valueOf(3);

        assertThat(lottos.checkAllLottosContainNumber(bonusNumber).size()).isEqualTo(3);
    }
}
