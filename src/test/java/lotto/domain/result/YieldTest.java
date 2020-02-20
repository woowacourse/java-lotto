package lotto.domain.result;

import lotto.domain.number.PurchaseNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class YieldTest {

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 생성자테스트() {
        assertThat(new Yield(10)).isInstanceOf(Yield.class);
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 수익률_계산하기() {
        PurchaseNumber purchaseNumber = PurchaseNumber.calculate(5000);
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
        Yield result = Yield.calculateYield(purchaseNumber, gameResults);
        // then
        assertThat(result).extracting("yield").isEqualTo((6030005000.0 / 5000) * 100.0);
    }
}
