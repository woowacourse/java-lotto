package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserLottoTest {
    EnumMap<Rank, Integer> ranks;
    UserLotto userLotto;

    @BeforeEach
    void setUp() {
        userLotto = new UserLotto(new FixedNumberGenerator(), 1000);
        ranks = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            ranks.put(rank, 0);
        }
    }

    @DisplayName("구매 금액이 1000원 단위이면 로또를 생성한다")
    @Test
    void purchaseLottoSuccess() {
        int purchaseAmount = 10000;
        UserLotto userLotto = new UserLotto(new FixedNumberGenerator(), purchaseAmount);

        assertEquals(userLotto.toDto().size(), 10);
    }

    @DisplayName("구매 금액이 1000원 단위가 아니면 예외를 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1234, 0, -900})
    void purchaseLottoFailure(int purchaseAmount) {
        assertThrows(IllegalArgumentException.class, () -> new UserLotto(new LottoNumberGenerator(), purchaseAmount));
    }

    @DisplayName("당첨 번호가 로또 번호와 6개 매치되면 1등이다")
    @Test
    void firstRankTest() {
        WinningLotto winningLotto = new WinningLotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)), 45);

        userLotto.calculateLottoResult(ranks, winningLotto);

        assertThat(ranks).containsEntry(Rank.FIRST, 1);
    }

    @DisplayName("당첨 번호가 로또 번호와 5개 매치되고, 보너스 번호가 매치되면 2등이다")
    @Test
    void secondRankTest() {
        WinningLotto winningLotto = new WinningLotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 7)), 6);

        userLotto.calculateLottoResult(ranks, winningLotto);

        assertThat(ranks).containsEntry(Rank.SECOND, 1);
    }

    @DisplayName("당첨 번호가 로또 번호와 5개 매치되고, 보너스 번호가 매치되지 않으면 3등이다")
    @Test
    void thirdRankTest() {
        WinningLotto winningLotto = new WinningLotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 7)), 45);

        userLotto.calculateLottoResult(ranks, winningLotto);

        assertThat(ranks).containsEntry(Rank.THIRD, 1)
                .containsEntry(Rank.SECOND, 0);
    }

    @DisplayName("당첨 번호가 로또 번호와 4개 매치되면 4등이다")
    @Test
    void fourthRankTest() {
        WinningLotto winningLotto = new WinningLotto(new ArrayList<>(List.of(1, 2, 3, 4, 7, 8)), 45);

        userLotto.calculateLottoResult(ranks, winningLotto);

        assertThat(ranks).containsEntry(Rank.FOURTH, 1);
    }

    @DisplayName("당첨 번호가 로또 번호와 3개 매치되면 5등이다")
    @Test
    void fifthRankTest() {
        WinningLotto winningLotto = new WinningLotto(new ArrayList<>(List.of(1, 2, 3, 7, 8, 9)), 45);

        userLotto.calculateLottoResult(ranks, winningLotto);

        assertThat(ranks).containsEntry(Rank.FIFTH, 1);
    }

    @DisplayName("당첨 번호가 로또 번호와 3개 미만으로 매치되면 낙첨이다")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,7,8,9,10", "1,7,8,9,10,11"})
    void failRankTest(String numbers) {
        WinningLotto winningLotto = new WinningLotto(new ArrayList<>(List.of(1, 2, 10, 7, 8, 9)), 45);

        userLotto.calculateLottoResult(ranks, winningLotto);

        assertThat(ranks).containsEntry(Rank.FAIL, 1);
    }

    private static class FixedNumberGenerator implements RandomNumberGenerator {
        @Override
        public Set<Integer> generateNumbers() {
            return Set.of(1, 2, 3, 4, 5, 6);
        }
    }
}