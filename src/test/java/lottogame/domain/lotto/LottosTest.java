package lottogame.domain.lotto;

import lottogame.domain.dto.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    @DisplayName("로또들이 Lotts 객체 안에 잘 들어갔는 지 테스트")
    @Test
    void 로또들을_관리하는_로또_일급컬렉션() {
        List<Lotto> lottoGroup = Arrays.asList(
                new Lotto(new LottoNumber(Arrays.asList(1, 2, 3, 4, 5, 6))),
                new Lotto(new LottoNumber(Arrays.asList(2, 3, 4, 5, 6, 7))),
                new Lotto(new LottoNumber(Arrays.asList(3, 4, 5, 6, 7, 8))),
                new Lotto(new LottoNumber(Arrays.asList(4, 5, 6, 7, 8, 9))),
                new Lotto(new LottoNumber(Arrays.asList(5, 6, 7, 8, 9, 10))));
        Lottos lottos = new Lottos(lottoGroup);
        assertThat(lottos).isEqualTo(new Lottos(lottoGroup));
    }

    @DisplayName("순위별 당첨 통계가 제대로 저장되는 지 확인")
    @Test
    void 로또_순위별_당첨_통계() {
        List<Lotto> lottoGroup = Arrays.asList(
                new Lotto(new LottoNumber(Arrays.asList(1, 2, 3, 4, 45, 44))),
                new Lotto(new LottoNumber(Arrays.asList(1, 2, 3, 43, 44, 45))),
                new Lotto(new LottoNumber(Arrays.asList(1, 2, 3, 4, 5, 7))),
                new Lotto(new LottoNumber(Arrays.asList(1, 2, 3, 4, 5, 44))));
        Lottos lottos = new Lottos(lottoGroup);
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

        List<LottoResult> expected = Arrays.asList(
                new LottoResult(4, false),
                new LottoResult(3, false),
                new LottoResult(5, true),
                new LottoResult(5, false));

        assertThat(lottos.matchesLottos(winningLotto)).isEqualTo(expected);
    }

    @DisplayName("순위별 당첨 통계가 제대로 저장되는 지 확인 ver2")
    @Test
    void 로또_순위별_당첨_통계_ver2() {
        List<Lotto> lottoGroup = Arrays.asList(
                new Lotto(new LottoNumber(Arrays.asList(8, 21, 23, 41, 42, 43))),
                new Lotto(new LottoNumber(Arrays.asList(3, 5, 11, 16, 32, 38))),
                new Lotto(new LottoNumber(Arrays.asList(7, 11, 16, 35, 36, 44))),
                new Lotto(new LottoNumber(Arrays.asList(1, 8, 11, 31, 41, 42))),
                new Lotto(new LottoNumber(Arrays.asList(13, 14, 16, 38, 42, 45))),
                new Lotto(new LottoNumber(Arrays.asList(7, 11, 30, 40, 42, 43))),
                new Lotto(new LottoNumber(Arrays.asList(2, 13, 22, 32, 38, 45))),
                new Lotto(new LottoNumber(Arrays.asList(23, 25, 33, 36, 39, 41))),
                new Lotto(new LottoNumber(Arrays.asList(1, 3, 5, 14, 22, 45))),
                new Lotto(new LottoNumber(Arrays.asList(5, 9, 38, 41, 43, 44))),
                new Lotto(new LottoNumber(Arrays.asList(2, 8, 9, 18, 19, 21))),
                new Lotto(new LottoNumber(Arrays.asList(13, 14, 18, 21, 23, 35))),
                new Lotto(new LottoNumber(Arrays.asList(17, 21, 29, 37, 42, 45))),
                new Lotto(new LottoNumber(Arrays.asList(3, 8, 27, 30, 35, 44))));
        Lottos lottos = new Lottos(lottoGroup);
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

        List<LottoResult> expected = Arrays.asList(
                new LottoResult(0, false),
                new LottoResult(2, false),
                new LottoResult(0, true),
                new LottoResult(1, false),
                new LottoResult(0, false),
                new LottoResult(0, true),
                new LottoResult(1, false),
                new LottoResult(0, false),
                new LottoResult(3, false),
                new LottoResult(1, false),
                new LottoResult(1, false),
                new LottoResult(0, false),
                new LottoResult(0, false),
                new LottoResult(1, false));

        assertThat(lottos.matchesLottos(winningLotto)).isEqualTo(expected);
    }
}
