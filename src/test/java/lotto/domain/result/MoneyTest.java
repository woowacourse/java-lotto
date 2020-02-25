package lotto.domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자테스트() {
        assertThat(new Money(1000)).isInstanceOf(Money.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {999, 0})
    void 최소_구매_금액보다_작은_입력의_생성자가_실행될_경우(int value) {
        Assertions.assertThatThrownBy(() -> {
            Money money = new Money(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 수익률_계산하기() {
        Money money = new Money(7000);
        // given
        GameResult round1 = GameResult.FIRST_RANK; // 2000000000
        GameResult round2 = GameResult.FIFTH_RANK; // 5000
        GameResult round3 = GameResult.SECOND_RANK; // 30000000
        GameResult round4 = GameResult.FIRST_RANK;// 2000000000
        GameResult round5 = GameResult.NO_RANK; // 0
        GameResult round6 = GameResult.NO_RANK; // 0
        GameResult round7 = GameResult.FIRST_RANK;// 6030005000

        List<GameResult> rounds = new ArrayList<>();
        rounds.add(round1);
        rounds.add(round2);
        rounds.add(round3);
        rounds.add(round4);
        rounds.add(round5);
        rounds.add(round6);
        rounds.add(round7);
        GameResults gameResults = new GameResults(rounds);
        // when
        double result = money.calculateYield(gameResults);
        double sumOfBenefit = 6030005000.0;
        // then
        assertThat(result).isEqualTo((sumOfBenefit / (7 * Money.TICKET_PRICE)) * Money.MULTIPLE_PERCENTAGE);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void calculateRound_테스트() {
        Money money = new Money(9400);
        Assertions.assertThat(money.calculateRound())
                .isEqualTo(9);
    }
}