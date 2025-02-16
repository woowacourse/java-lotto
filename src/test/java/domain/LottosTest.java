package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.LottoTest.FixedNumberGenerator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 당첨_등수를_종합하여_반환한다() {
        NumberGenerator fixedNumberGenerator = new FixedNumberGenerator(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = WinningNumbers.createByWinningLottoAndBonusNumber(
                Lotto.issueByNumbers(List.of(
                        LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber.of(5),
                        LottoNumber.of(6))
                ), 7);

        Lottos lottos = Lottos.issueByMoney(5000, fixedNumberGenerator);

        assertThat(lottos.getRankCount(winningNumbers))
                .isEqualTo(new EnumMap<>(Map.ofEntries(Map.entry(Rank.SIX, 5))));
    }

    @Test
    void 구입_금액에_해당하는_개수의_로또를_발행한다() {
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        Lottos lottos = Lottos.issueByMoney(2000, randomNumberGenerator);

        assertThat(lottos.getQuantity()).isEqualTo(2);
    }

}