package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    private static final LottoNumbers WINNING_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
    private static final LottoNumber BONUS = new LottoNumber(7);
    private static final LottoNumbers FIRST_PRIZE_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
    private static final LottoNumbers SECOND_PRIZE_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 3, 4, 5, 7));
    private static final LottoNumbers THIRD_PRIZE_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 3, 4, 5, 8));
    private static final LottoNumbers NOTHING_PRIZE_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 9, 11, 12, 13));
    private static final Prize FIRST_PRIZE = LottoRank.FIRST.getPrize();
    private static final Prize SECOND_PRIZE = LottoRank.SECOND.getPrize();
    private static final Prize THIRD_PRIZE = LottoRank.THIRD.getPrize();


    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(new WinningLottoNumbers(WINNING_LOTTO_NUMBERS, BONUS));
    }

    @Test
    @DisplayName("1등 로또 한개의 결과 구하기")
    void summarizeOnlyOneFirstRankLotto() {
        LottoResult lottoResult = lottoMachine.summarize(List.of(FIRST_PRIZE_LOTTO_NUMBERS));

        assertThat(lottoResult.getTotalPrize()).isEqualTo(FIRST_PRIZE);
        assertThat(lottoResult.getCountByRank(LottoRank.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("다양한 로또 순위들의 결과 구하기")
    void summarizeMultiRankLotto() {
        LottoResult result = lottoMachine
            .summarize(List.of(FIRST_PRIZE_LOTTO_NUMBERS, FIRST_PRIZE_LOTTO_NUMBERS,
                SECOND_PRIZE_LOTTO_NUMBERS, THIRD_PRIZE_LOTTO_NUMBERS,
                THIRD_PRIZE_LOTTO_NUMBERS, NOTHING_PRIZE_LOTTO_NUMBERS));

        Prize expectedPrize = FIRST_PRIZE.add(FIRST_PRIZE).add(SECOND_PRIZE).add(THIRD_PRIZE)
            .add(THIRD_PRIZE);
        assertThat(result.getTotalPrize()).isEqualTo(expectedPrize);
        assertThat(result.getCountByRank(LottoRank.FIRST)).isEqualTo(2);
        assertThat(result.getCountByRank(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.getCountByRank(LottoRank.THIRD)).isEqualTo(2);
        assertThat(result.getCountByRank(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(result.getCountByRank(LottoRank.FIFTH)).isEqualTo(0);
        assertThat(result.getCountByRank(LottoRank.NOTHING)).isEqualTo(1);
    }
}
