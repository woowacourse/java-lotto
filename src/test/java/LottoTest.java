import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoNumber;
import domain.Rewards;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또 번호를 생성한다.")
    void generateNumber() {
        Lotto lotto = new Lotto();
        assertThat(lotto.generateNumber().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("1등 당첨인지 확인하는 기능 테스트")
    void checkWinning() {
        Lotto lotto = new Lotto(Stream.of(3, 5, 6, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        List<LottoNumber> winningNumber = Stream.of(3, 5, 6, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = new LottoNumber(11);

        assertThat(lotto.checkWinning(winningNumber, bonusNumber))
                .isEqualTo(Rewards.FIRST_REWARD);
    }

    @Test
    @DisplayName("2등 당첨인지 확인하는 기능 테스트")
    void checkWinning2() {
        Lotto lotto = new Lotto(Stream.of(3, 5, 6, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        List<LottoNumber> winningNumber = Stream.of(3, 5, 6, 7, 8, 10)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = new LottoNumber(9);

        assertThat(lotto.checkWinning(winningNumber, bonusNumber))
                .isEqualTo(Rewards.SECOND_REWARD);
    }

    @Test
    @DisplayName("3등 당첨인지 확인하는 테스트 ")
    void checkWinning3() {
        Lotto lotto = new Lotto(Stream.of(3, 5, 6, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        List<LottoNumber> winningNumber = Stream.of(3, 5, 6, 7, 8, 10)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = new LottoNumber(12);

        assertThat(lotto.checkWinning(winningNumber, bonusNumber))
                .isEqualTo(Rewards.THIRD_REWARD);
    }

    @Test
    @DisplayName("당첨 안됐을때 확인하는 기능 테스트")
    void checkWinning4() {
        Lotto lotto = new Lotto(Stream.of(11, 12, 13, 14, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        List<LottoNumber> winningNumber = Stream.of(3, 5, 6, 7, 8, 10)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = new LottoNumber(9);

        assertThat(lotto.checkWinning(winningNumber, bonusNumber))
                .isEqualTo(Rewards.NO_REWARD);
    }
}
