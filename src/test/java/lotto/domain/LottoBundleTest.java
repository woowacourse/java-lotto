package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoBundleTest {

    @DisplayName("매칭되는 수의 갯수에 따라 당첨 통계를 확인한다.")
    @Test
    void 매칭되는_수의_갯수에_따라_당첨_통계를_확인한다() {

        //given
        Lotto lotto1 = new Lotto(
                Set.of(LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber.of(5),
                        LottoNumber.of(6)));

        Lotto lotto2 = new Lotto(
                Set.of(LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber.of(5),
                        LottoNumber.of(7)));

        LottoBundle lottoBundle = new LottoBundle(List.of(lotto1, lotto2));

        WinningNumbers winningNumbers = new WinningNumbers(lotto1, LottoNumber.of(8));

        //when
        Map<Rank, Integer> lottoResult = lottoBundle.makeStatistics(winningNumbers);

        //then
        assertThat(lottoResult.get(Rank.FIRST_PRIDE)).isEqualTo(1);
        assertThat(lottoResult.get(Rank.SECOND_PRIDE)).isEqualTo(0);
        assertThat(lottoResult.get(Rank.THIRD_PRIDE)).isEqualTo(1);
    }

    @DisplayName("로또의 갯수를 반환한다.")
    @Test
    void 로또의_갯수를_반환한다() {

        //given
        Lotto lotto1 = new Lotto(
                Set.of(LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber.of(5),
                        LottoNumber.of(6)));

        Lotto lotto2 = new Lotto(
                Set.of(LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber.of(5),
                        LottoNumber.of(6)));

        LottoBundle lottoBundle = new LottoBundle(List.of(lotto1, lotto2));

        //when & then
        assertThat(lottoBundle.getLottoQuantity()).isEqualTo(2);
    }

}
