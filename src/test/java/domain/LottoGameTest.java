package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private static WinningLotto winningLotto;
    private final LottoTicket firstPrizeLottoTicket = createNewLotto(1, 2, 3, 4, 5, 6);
    private final LottoTicket secondPrizeLottoTicket = createNewLotto(1, 2, 3, 4, 5, 7);
    private final LottoTicket thirdPrizeLottoTicket = createNewLotto(1, 2, 3, 4, 5, 16);
    private final LottoTicket fourthPrizeLottoTicket = createNewLotto(1, 2, 3, 4, 15, 16);
    private final LottoTicket fifthPrizeLottoTicket = createNewLotto(1, 2, 3, 14, 15, 16);
    private final LottoTicket noPrizeLottoTicket = createNewLotto(11, 12, 13, 14, 15, 16);

    @BeforeAll
    static void setUp() {
        LottoTicket winningNumbers = createNewLotto(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = LottoNumber.of(7);

        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    private static LottoTicket createNewLotto(int... value) {
        List<LottoNumber> lottoNumbers = Arrays.stream(value)
                .boxed()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return LottoTicket.createManualLotto(lottoNumbers);
    }

    private List<LottoTicket> createLottoTicketsExample(LottoTicket... lottoTickets) {
        List<LottoTicket> lottoTicketsExample = new ArrayList<>();
        Collections.addAll(lottoTicketsExample, lottoTickets);
        return lottoTicketsExample;
    }

    @DisplayName("1등 한번, 2등 한번, 낙첨은 세지 않음")
    @Test
    void getResultStatistics_WinningFirstAndSecondAndNoNull_Successful() {
        LottoTickets lottoTickets = new LottoTickets(
                createLottoTicketsExample(firstPrizeLottoTicket, secondPrizeLottoTicket, noPrizeLottoTicket));
        LottoGame game = new LottoGame(lottoTickets, winningLotto);

        Map<LottoResult, Integer> actual = game.getResultStatistics();

        assertThat(actual).containsOnlyKeys(LottoResult.values());
        assertThat(actual.get(LottoResult.FIRST)).isEqualTo(1);
        assertThat(actual.get(LottoResult.SECOND)).isEqualTo(1);
        assertThat(actual.get(LottoResult.THIRD)).isEqualTo(0);
        assertThat(actual.get(LottoResult.FOURTH)).isEqualTo(0);
        assertThat(actual.get(LottoResult.FIFTH)).isEqualTo(0);
    }

    @DisplayName("3등 두번, 4등 한번")
    @Test
    void getResultStatistics_WinningTwoThirdAndFourth_Successful() {
        LottoTickets lottoTickets = new LottoTickets(
                createLottoTicketsExample(thirdPrizeLottoTicket, thirdPrizeLottoTicket, fourthPrizeLottoTicket));
        LottoGame game = new LottoGame(lottoTickets, winningLotto);

        Map<LottoResult, Integer> actual = game.getResultStatistics();

        assertThat(actual).containsOnlyKeys(LottoResult.values());
        assertThat(actual.get(LottoResult.FIRST)).isEqualTo(0);
        assertThat(actual.get(LottoResult.SECOND)).isEqualTo(0);
        assertThat(actual.get(LottoResult.THIRD)).isEqualTo(2);
        assertThat(actual.get(LottoResult.FOURTH)).isEqualTo(1);
        assertThat(actual.get(LottoResult.FIFTH)).isEqualTo(0);
    }

    @DisplayName("5등 당첨 시 수익률 500%")
    @Test
    void calculateProfitRatio_WinningFifth_ReturnsFiveTimesThePrice() {
        LottoTickets lottoTickets = new LottoTickets(createLottoTicketsExample(fifthPrizeLottoTicket));
        LottoGame game = new LottoGame(lottoTickets, winningLotto);

        float actual = game.calculateProfitRatio(1000);

        assertThat(actual).isEqualTo((float) LottoResult.FIFTH.getPrize() / 1000);
    }

    @DisplayName("낙첨 시 수익률 0%")
    @Test
    void calculateProfitRatio_LosingAll_ReturnsZero() {
        LottoTickets lottoTickets = new LottoTickets(createLottoTicketsExample(noPrizeLottoTicket));
        LottoGame game = new LottoGame(lottoTickets, winningLotto);

        float actual = game.calculateProfitRatio(1000);

        assertThat(actual).isEqualTo(0f);
    }
}
