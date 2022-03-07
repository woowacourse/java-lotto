package domain;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private Lottos lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    void setup() {
        List<LottoNumber> lottoNumbers = IntStream.rangeClosed(1, 7)
                .filter(number -> number != 6)
                .mapToObj(LottoNumber::getInstance)
                .collect(toList());
        lottos = new Lottos(List.of(new Lotto(lottoNumbers), new Lotto(lottoNumbers)));

        List<LottoNumber> winningNumbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::getInstance)
                .collect(toList());
        LottoNumber bonusNumber = LottoNumber.getInstance(7);
        winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNumber);
    }

    @Test
    @DisplayName("2등 로또 당첨 결과 2개 생성")
    void createLottoResult() {
        // given
        LottoResult lottoResult = LottoResult.of(winningLotto, lottos);

        // when
        LongSummaryStatistics secondPlaceSummary = lottoResult.getLottoResultByRank(Rank.SECOND);

        // then
        assertAll(
                () -> assertThat(secondPlaceSummary.getCount()).isEqualTo(2),
                () -> assertThat(secondPlaceSummary.getSum()).isEqualTo(Rank.SECOND.getPrize() * 2)
        );
    }
}
