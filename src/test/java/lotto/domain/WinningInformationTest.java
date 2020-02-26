package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class WinningInformationTest {
    @Test
    void WinningInformation_정상_입력에_대한_생성자_동작_확인() {
        Lotto lotto = Lotto.createLottoManual(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = LottoNumber.of(7);
        assertThat(new WinningInformation(lotto, bonus))
            .isInstanceOf(WinningInformation.class);
    }

    @Test
    void WinningInformation_보너스번호와_정답에_중복이_있을_시() {
        Lotto lotto = Lotto.createLottoManual(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = LottoNumber.of(2);
        assertThatThrownBy(() -> new WinningInformation(lotto, bonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("보너스 번호는 당첨번호와 중복될 수 없습니다.");
    }

    @Test
    void WinningInformation_1등_계산 () {
        Lotto winningLotto = Lotto.createLottoManual(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = LottoNumber.of(7);

        WinningInformation winningInformation = new WinningInformation(winningLotto, bonus);
        assertThat(winningInformation.calculateRank(winningLotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    void WinningInformation_2등_계산 () {
        Lotto winningLotto = Lotto.createLottoManual(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = LottoNumber.of(7);

        Lotto secondRankLotto = Lotto.createLottoManual(Arrays.asList(1, 2, 3, 7, 5, 4));

        WinningInformation winningInformation = new WinningInformation(winningLotto, bonus);
        assertThat(winningInformation.calculateRank(secondRankLotto)).isEqualTo(Rank.SECOND);
    }

    @Test
    void WinningInformation_3등_계산 () {
        Lotto winningLotto = Lotto.createLottoManual(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = LottoNumber.of(7);

        Lotto thirdRankLotto = Lotto.createLottoManual(Arrays.asList(1, 2, 3, 4, 5, 10));

        WinningInformation winningInformation = new WinningInformation(winningLotto, bonus);
        assertThat(winningInformation.calculateRank(thirdRankLotto)).isEqualTo(Rank.THIRD);
    }
}
