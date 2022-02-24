package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private static LottoReferee referee;
    private final LottoTicket firstPrizeLottoTicket = createNewLotto(1, 2, 3, 4, 5, 6);
    private final LottoTicket secondPrizeLottoTicket = createNewLotto(1, 2, 3, 4, 5, 7);
    private final LottoTicket thirdPrizeLottoTicket = createNewLotto(1, 2, 3, 4, 5, 16);
    private final LottoTicket fourthPrizeLottoTicket = createNewLotto(1, 2, 3, 4, 15, 16);
    private final LottoTicket fifthPrizeLottoTicket = createNewLotto(1, 2, 3, 14, 15, 16);
    private final LottoTicket noPrizeLottoTicket = createNewLotto(11, 12, 13, 14, 15, 16);

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
        LottoTickets lottoTickets = new LottoTickets(getLottosExample(firstPrizeLottoTicket, secondPrizeLottoTicket, noPrizeLottoTicket));
        LottoGame game = new LottoGame(lottoTickets, referee);

        Map<LottoResult, Integer> actual = game.getResultStatistics();

        assertThat(actual).containsOnlyKeys(LottoResult.values());
        assertThat(actual.get(LottoResult.FIRST)).isEqualTo(1);
        assertThat(actual.get(LottoResult.SECOND)).isEqualTo(1);
        assertThat(actual.get(LottoResult.THIRD)).isEqualTo(0);
        assertThat(actual.get(LottoResult.FOURTH)).isEqualTo(0);
        assertThat(actual.get(LottoResult.FIFTH)).isEqualTo(0);
    }

    @Test
    void calculateProfitRatio_zeroIfNoPrize() {
        LottoTickets lottoTickets = new LottoTickets(getLottosExample(noPrizeLottoTicket));
        LottoGame game = new LottoGame(lottoTickets, referee);

        float actual = game.calculateProfitRatio();

        assertThat(actual).isEqualTo(0f);
    }

    @Test
    void calculateProfitRatio_fifthPrizeEqualsFiveTimesThePrice() {
        LottoTickets lottoTickets = new LottoTickets(getLottosExample(fifthPrizeLottoTicket));
        LottoGame game = new LottoGame(lottoTickets, referee);

        float actual = game.calculateProfitRatio();

        assertThat(actual).isEqualTo((float) LottoResult.FIFTH.getPrize() / 1000);
    }

    private List<LottoTicket> getLottosExample(LottoTicket... lottoTickets) {
        List<LottoTicket> lottosExample = new ArrayList<>();
        Collections.addAll(lottosExample, lottoTickets);
        return lottosExample;
    }

    private LottoTicket createNewLotto(int... value) {
        List<LottoNumber> lottoNumbers = Arrays.stream(value)
                .boxed()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return LottoTicket.createManualLotto(lottoNumbers);
    }
}
