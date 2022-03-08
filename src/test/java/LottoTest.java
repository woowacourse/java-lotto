import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Lotto;
import domain.LottoNumber;
import domain.ManualLottoGenerator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private Lotto lotto;

    @BeforeEach
    void init() {
        lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet()));
    }

    @Test
    @DisplayName("당첨 로또와 비교 기능 테스트_6개")
    void countDuplicatedNumberTest() {
        Set<LottoNumber> winningLotto = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet());

        assertThat(lotto.countDuplicatedNumber(winningLotto)).isEqualTo(6);
    }

    @Test
    @DisplayName("보너스 넘버 포함 확인 기능 테스트_포함")
    void isBonusNumberContainTest() {
        LottoNumber bonusNumber = LottoNumber.valueOf(3);

        assertThat(lotto.contains(bonusNumber)).isEqualTo(true);
    }

    @Test
    @DisplayName("6개의 숫자가 아닌 로또 테스트")
    void buyLottoTest4() {
        assertThatThrownBy(() -> Lotto.generateLotto(new ManualLottoGenerator(List.of(1, 2, 3, 4, 5, 6, 7))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1-45 외의 수가 존재하는 로또 테스트")
    void buyLottoTest5() {
        assertThatThrownBy(() -> Lotto.generateLotto(new ManualLottoGenerator(List.of(1, 2, 3, 4, 5, 46))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
