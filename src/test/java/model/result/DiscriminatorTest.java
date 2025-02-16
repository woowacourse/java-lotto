package model.result;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import model.LottoWinningNumbers;
import model.PurchasedLotto;
import model.lotto.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscriminatorTest {
    private PurchasedLotto purchasedLotto;
    private LottoWinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        List<Lotto> lottoList = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)),
                new Lotto(Arrays.asList(8, 9, 10, 11, 12, 18))
        );
        purchasedLotto = new PurchasedLotto(lottoList);

        winningNumbers = new LottoWinningNumbers(
                Arrays.asList(1, 2, 3, 4, 5, 6), 7
        );
    }

    @DisplayName("judge메서드_Rank에맞는개수계산")
    @Test
    void testJudge() {
        PrizeResult result = WinningDiscriminator.judge(purchasedLotto, winningNumbers);

        EnumMap<Rank, Integer> expectedCounts = new EnumMap<>(Rank.class);
        expectedCounts.put(Rank.RANK1, 1);
        expectedCounts.put(Rank.RANK2, 1);
        expectedCounts.put(Rank.MISS, 1);

        assertEquals(expectedCounts, result.getResult());
    }
}