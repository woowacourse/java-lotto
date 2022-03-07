package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("WinningLotto 테스트")
public class WinningLottoTest {
    private Lotto lotto;
    private LottoNumber bonusNumber;

    @BeforeEach
    void setup() {
        lotto = Lotto.fromRawValues(Set.of(1, 2, 3, 4, 5, 6));
        bonusNumber = LottoNumber.from(7);
    }

    @Test
    @DisplayName("생성자에 Lotto 와 LottoNumber 인스턴스를 전달하여 WinningLotto 인스턴스 생성")
    void createWinningLottoTest() {
        // given
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        // when & then
        assertThat(winningLotto).isNotNull();
    }

    @Test
    @DisplayName("생성자에 전달된 bonusNumber 가 lotto 에 포함되어 있을 경우 IAE 발생")
    void createWinningLottoWithDuplicateBonusNumberShouldFail() {
        assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.from(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WinningLotto.ERROR_MESSAGE_FOR_DUPLICATE_BONUS_NUMBER);
    }
}
