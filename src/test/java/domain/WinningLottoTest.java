package domain;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    private Lotto lotto;
    private LottoNumber bonusNumber;

    @BeforeEach
    void setup() {
        lotto = new Lotto(IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::getInstance)
                .collect(toList()));

        bonusNumber = LottoNumber.getInstance(7);
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
        assertThatThrownBy(() -> new WinningLotto(lotto, LottoNumber.getInstance(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("보너스 번호는 로또 번호와 중복될 수 없습니다 : \\d+");
    }

    @Test
    @DisplayName("Lotto 를 전달받아 Rank를 반환한다")
    void getRankByLotto() {
        // given
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        // when
        Rank rank = winningLotto.getRankByLotto(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }
}
