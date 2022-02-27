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
}
