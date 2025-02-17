package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoWinRankTest {
    List<Lotto> lottoList = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 8)),
            new Lotto(List.of(1, 2, 3, 4, 7, 8)),
            new Lotto(List.of(1, 2, 3, 10, 8, 9)),
            new Lotto(List.of(14, 15, 10, 11, 12, 13))
    );
    WinLotto winLotto = new WinLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    List<LottoWinRank> resultList = lottoList.stream()
            .map((purchasedLotto) -> LottoWinRank.calculateLottoWinRank(purchasedLotto, winLotto))
            .toList();

    @Test
    @DisplayName("1등 당첨 결과 메서드 테스트")
    public void test1() {
        assertThat(resultList.getFirst()).isEqualTo(LottoWinRank.FIRST);
    }

    @Test
    @DisplayName("2등 당첨 결과 메서드 테스트")
    void test2() {
        assertThat(resultList.get(1)).isEqualTo(LottoWinRank.SECOND);
    }

    @Test
    @DisplayName("3등 당첨 결과 메서드 테스트")
    void test3() {
        assertThat(resultList.get(2)).isEqualTo(LottoWinRank.THIRD);
    }

    @Test
    @DisplayName("4등 당첨 결과 메서드 테스트")
    void test4() {
        assertThat(resultList.get(3)).isEqualTo(LottoWinRank.FOURTH);
    }

    @Test
    @DisplayName("5등 당첨 결과 메서드 테스트")
    void test5() {
        assertThat(resultList.get(4)).isEqualTo(LottoWinRank.FIFTH);
    }

    @Test
    @DisplayName("꽝 결과 메서드 테스트")
    void test6() {
        assertThat(resultList.get(5)).isEqualTo(LottoWinRank.NONE);
    }
}
