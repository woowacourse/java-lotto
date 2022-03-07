package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import lotto.model.exception.DuplicatedNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinnerLottoTest {

    private static final LottoNumber BONUS = new LottoNumber(7);
    private static final Lotto WINNING_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
    private static final Lotto FIRST_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
    private static final Lotto SECOND_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 5, 7));
    private static final Lotto THIRD_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 5, 8));
    private static final Lotto FOURTH_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 4, 10, 11));
    private static final Lotto FIFTH_RANK_LOTTO = Lotto.create(List.of(1, 2, 3, 10, 11, 12));
    private static final Lotto NOTHING_RANK_LOTTO = Lotto.create(List.of(1, 2, 9, 11, 12, 13));

    private WinnerLotto winnerLotto;

    @BeforeEach
    void setUp() {
        winnerLotto = new WinnerLotto(WINNING_LOTTO, BONUS);
    }


    @Test
    @DisplayName("당첨번호와 보너스 번호 정상적으로 생성")
    void createValidWinningLottoNumbers() {
        Lotto lotto = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = new LottoNumber(7);
        assertThatCode(() -> new WinnerLotto(lotto, bonus)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호 중복 생성 예외 발생")
    void duplicatedWinningLottoNumbers() {
        Lotto lotto = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = new LottoNumber(1);
        assertThatThrownBy(() -> new WinnerLotto(lotto, bonus))
            .isInstanceOf(DuplicatedNumberException.class);
    }

    @Test
    @DisplayName("1등 판독 테스트")
    void firstPrize() {
        Collection<Rank> ranks = winnerLotto.classify(new Lottoes(List.of(FIRST_RANK_LOTTO)));
        assertThat(ranks).contains(Rank.FIRST);
    }

    @Test
    @DisplayName("2등 판독 테스트")
    void secondPrize() {
        Collection<Rank> ranks = winnerLotto.classify(new Lottoes(List.of(SECOND_RANK_LOTTO)));
        assertThat(ranks).contains(Rank.SECOND);
    }

    @Test
    @DisplayName("3등 판독 테스트")
    void thirdPrize() {
        Collection<Rank> ranks = winnerLotto.classify(new Lottoes(List.of(THIRD_RANK_LOTTO)));
        assertThat(ranks).contains(Rank.THIRD);
    }

    @Test
    @DisplayName("4등 판독 테스트")
    void fourthPrize() {
        Collection<Rank> ranks = winnerLotto.classify(new Lottoes(List.of(FOURTH_RANK_LOTTO)));
        assertThat(ranks).contains(Rank.FOURTH);
    }

    @Test
    @DisplayName("5등 판독 테스트")
    void fifthPrize() {
        Collection<Rank> ranks = winnerLotto.classify(new Lottoes(List.of(FIFTH_RANK_LOTTO)));
        assertThat(ranks).contains(Rank.FIFTH);
    }

    @ParameterizedTest(name = "꽝 판독 테스트 : 로또 번호 - {0}")
    @MethodSource("provideLottoNumbersList")
    @DisplayName("꽝 판독 테스트")
    void nothingPrize(Lotto lottoNumbers) {
        Collection<Rank> ranks = winnerLotto.classify(new Lottoes(List.of(lottoNumbers)));
        assertThat(ranks).contains(Rank.NOTHING);
    }

    private static Stream<Arguments> provideLottoNumbersList() {
        return Stream.of(
            Arguments.of(Lotto.create(List.of(1, 2, 8, 9, 10, 11))),
            Arguments.of(Lotto.create(List.of(1, 2, 7, 9, 10, 11)))
        );
    }

    @Test
    @DisplayName("다양한 로또 순위 구하기")
    void summarize() {
        Collection<Rank> ranks = winnerLotto.classify(
            new Lottoes(List.of(FIRST_RANK_LOTTO, FIRST_RANK_LOTTO,
                SECOND_RANK_LOTTO, THIRD_RANK_LOTTO,
                THIRD_RANK_LOTTO, NOTHING_RANK_LOTTO)));

        assertThat(ranks).hasSize(6);
        assertThat(ranks).containsExactlyInAnyOrder(Rank.FIRST, Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.THIRD, Rank.NOTHING);
    }
}
