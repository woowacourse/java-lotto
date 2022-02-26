package domain;

import static common.TestUtils.createCountsDto;
import static common.TestUtils.createNewLotto;
import static domain.LottoGame.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private static LottoReferee referee;
    private static final Lotto firstPrizeLotto = createNewLotto(1, 2, 3, 4, 5, 6);
    private static final Lotto secondPrizeLotto = createNewLotto(1, 2, 3, 4, 5, 7);
    private static final Lotto fifthPrizeLotto = createNewLotto(1, 2, 3, 14, 15, 16);
    private static final Lotto noPrizeLotto = createNewLotto(11, 12, 13, 14, 15, 16);

    @BeforeAll
    static void setup() {
        List<LottoNumber> winningNumbers = Stream
                .of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        LottoNumber bonusNumber = LottoNumber.of(7);

        referee = new LottoReferee(winningNumbers, bonusNumber);
    }

    @Test
    void getResultStatistics() {
        Lottos lottos = Lottos.of(getLottosExample(firstPrizeLotto, secondPrizeLotto, noPrizeLotto),
                createCountsDto(3, 0));
        LottoGame game = new LottoGame(lottos, referee);

        Map<LottoResult, Integer> actual = game.getResultStatistics();

        assertThat(actual).containsOnlyKeys(LottoResult.values());
        assertThat(actual.get(LottoResult.FIRST)).isEqualTo(1);
        assertThat(actual.get(LottoResult.SECOND)).isEqualTo(1);
        assertThat(actual.get(LottoResult.THIRD)).isEqualTo(0);
        assertThat(actual.get(LottoResult.FOURTH)).isEqualTo(0);
        assertThat(actual.get(LottoResult.FIFTH)).isEqualTo(0);
    }

    @Test
    void calculatePrizePriceRatio_zeroIfNoPrize() {
        Lottos lottos = Lottos.of(getLottosExample(noPrizeLotto), createCountsDto(1, 0));
        LottoGame game = new LottoGame(lottos, referee);

        float actual = game.calculatePrizePriceRatio();

        assertThat(actual).isEqualTo(0f);
    }

    @Test
    void calculatePrizePriceRatio_fifthPrizeEqualsFiveTimesThePrice() {
        Lottos lottos = Lottos.of(getLottosExample(fifthPrizeLotto), createCountsDto(1, 0));
        LottoGame game = new LottoGame(lottos, referee);

        float actual = game.calculatePrizePriceRatio();

        assertThat(actual).isEqualTo((float) LottoResult.FIFTH.getPrize() / LOTTO_PRICE);
    }

    private List<Lotto> getLottosExample(Lotto... lottos) {
        List<Lotto> lottosExample = new ArrayList<>();
        Collections.addAll(lottosExample, lottos);
        return lottosExample;
    }
}
