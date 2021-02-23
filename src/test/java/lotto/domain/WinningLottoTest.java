package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.model.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @DisplayName("6개의 당첨 번호는 서로 다른 번호여야한다.")
    @Test
    void 중복된_당첨_번호_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 1, 2, 3, 4, 5);
        int bonusNumber = 5;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }

    @DisplayName("당첨 번호는 6개로 구성되어있어야한다.")
    @Test
    void 당첨_번호_길이_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5);
        int bonusNumber = 5;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }

    @DisplayName("보너스 숫자는는 1부터 45사이의 번호여야한다.")
    @Test
    void 보너스_숫자_테스트() {
        // given, when
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = -1;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(Lotto.of(winningNumbers), bonusNumber);
        });
    }

    @DisplayName("로또 번호와 당첨 번호의 당첨 개수 확인")
    @Test
    void 로또_번호와_당첨_번호_확인_테스트() {
        // given, when
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLottoValue = Lotto.of(Arrays.asList(1, 3, 5, 14, 22, 45));
        int winningBonus = 7;
        WinningLotto winningLotto = new WinningLotto(winningLottoValue, winningBonus);

        // then
        LottoRank lottoResult = winningLotto.match(userLotto);
        assertThat(lottoResult).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("보너스 번호 확인 테스트")
    @Test
    void 보너스_번호_확인_테스트() {
        // given, when
        Lotto userLotto = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winningLottoValue = Lotto.of(Arrays.asList(1, 3, 5, 4, 2, 45));
        int winningBonus = 6;
        WinningLotto winningLotto = new WinningLotto(winningLottoValue, winningBonus);

        // then
        LottoRank lottoResult = winningLotto.match(userLotto);
        assertThat(lottoResult).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("보너스 번호는 당첨 번호는 없는 번호여야한다.")
    @Test
    void 보너스_번호와_당첨_번호_다른지_확인_테스트() {
        // given, when
        Lotto winningLottoValue = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        int winningBonus = 6;

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(winningLottoValue, winningBonus);
        });
    }
}
