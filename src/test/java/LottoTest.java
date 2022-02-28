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
    @DisplayName("당첨 로또와 비교 기능 테스트_6개")
    void countDuplicatedNumberTest() {
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));
        Lotto winningLotto = new Lotto(Stream.of(1,2,3,4,5,6)
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));

        assertThat(lotto.countDuplicatedNumber(winningLotto)).isEqualTo(6);
    }

    @Test
    @DisplayName("보너스 넘버 포함 확인 기능 테스트_포함")
    void isBonusNumberContainTest() {
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));
        LottoNumber bonusNumber = new LottoNumber(3);

        assertThat(lotto.isBonusNumberContain(bonusNumber)).isEqualTo(true);
    }
}
