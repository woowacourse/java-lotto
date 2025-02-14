package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoBundleTest {

    @DisplayName("매칭되는 수의 갯수에 따라 당첨 통계를 확인한다.")
    @Test
    void 매칭되는_수의_갯수에_따라_당첨_통계를_확인한다() {

        LottoBundle lottoBundle = new LottoBundle(List.of(new Lotto(List.of(1,2,3,4,5,6)), new Lotto(List.of(1,2,3,4,5,7))));

        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,6),8);

        EnumMap<Rank, Integer> lottoResult = lottoBundle.makeStatistics(winningNumbers);

        assertThat(lottoResult.get(Rank.FIRST_PRIDE)).isEqualTo(1);
        assertThat(lottoResult.get(Rank.SECOND_PRIDE)).isEqualTo(0);
        assertThat(lottoResult.get(Rank.THIRD_PRIDE)).isEqualTo(1);
    }


}
