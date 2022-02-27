package domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class LottosTest {
    private List<Lotto> lottos = new ArrayList<>();
    private WinningLotto winningLotto;

    @BeforeEach
    void setupLottos() {
        Lotto lottoNumbers1 = new Lotto(Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        Lotto lottoNumbers2 = new Lotto(Set.of(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15),
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)));
        Lotto lottoNumbers3 = new Lotto(Set.of(new LottoNumber(21), new LottoNumber(22), new LottoNumber(23),
                new LottoNumber(28), new LottoNumber(25), new LottoNumber(45)));

        winningLotto = new WinningLotto(lottoNumbers1, new LottoNumber(7));

        lottos = List.of(lottoNumbers1, lottoNumbers2, lottoNumbers3);
    }

    @Test
    @DisplayName("List<Lotto> 를 전달받아 Lottos 생성")
    void createLottos() {
        // given
        Lottos validLottos = new Lottos(lottos);

        // when & then
        assertThat(validLottos).isNotNull();
    }

    @ParameterizedTest(name = "Lottos 생성자에 {0} 가 전달됐을 때, IAE 발생")
    @NullAndEmptySource
    void createLottosWithNullAndEmptyShouldFail(List<Lotto> lottos) {
        assertThatThrownBy(() -> new Lottos(lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("Lotto 목록이 비었습니다");
    }

    @Test
    @DisplayName("Winning Lotto를 전달 받아 당첨 결과 반환")
    void getLottoResultByWinningLotto() {
        // given
        Lottos lottos = new Lottos(this.lottos);
        Map<Rank, WinningCount> map = lottos.getResultByWinningLotto(winningLotto);

        // when
        WinningCount winningCount = map.get(Rank.FIRST);

        // then
        assertThat(winningCount).isEqualTo(new WinningCount(1));
    }
}
