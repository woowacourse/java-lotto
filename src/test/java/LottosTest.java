import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumberGenerator;
import domain.Lottos;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lottos makeLottos() {
        List<List<Integer>> pickLottoNumbersBucket = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
        return Lottos.buyLottos(LottoNumberGenerator.build(3000, pickLottoNumbersBucket), 3);
    }

    @Test
    @DisplayName("모든 로또 우승 로또와 비교하는 기능 로또 수만큼 카운트 세는지 확인하는 테스트")
    void compareAllLottosWithWinningLottoTest() {
        Lottos lottos = makeLottos();
        Lotto winningLotto = new Lotto(Stream.of(3, 4, 5, 6, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));

        assertThat(lottos.compareAllLottosWithWinningLotto(winningLotto).size()).isEqualTo(3);
    }

    @Test
    @DisplayName("모든 로또 보너스넘버 포함 확인 기능 로또 수만큼 카운트 세는지 확인하는 테스트")
    void compareAllLottosWithBonusNumberTest() {
        Lottos lottos = makeLottos();
        LottoNumber bonusNumber = new LottoNumber(3);

        assertThat(lottos.checkAllLottosContainNumber(bonusNumber).size()).isEqualTo(3);
    }
}
