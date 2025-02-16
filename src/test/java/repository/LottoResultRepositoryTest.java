package repository;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.Rank;
import domain.WinningNumber;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoResultRepositoryTest {

    private final LottoResultRepository lottoResultRepository = LottoResultRepository.create();
    private WinningNumber winningNumber;
    private List<Lotto> lottos;

    @BeforeEach
    void init() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        Lotto lotto = Lotto.from(numbers);
        winningNumber = WinningNumber.of(lotto, bonusNumber);
    }

    @Nested
    @DisplayName("로또 번호와 당첨 번호 비교 로직 검증 테스트 코드")
    class validateLottoCalculate {

        @DisplayName("숫자가 6개 적중할 경우, 1등에 당첨되어야 한다.")
        @Test
        void validate_first_and_fifth() {
            List<Integer> givenNumbers = List.of(1, 2, 3, 4, 5, 6);
            assertValidateResult(givenNumbers, Rank.FIRST, 1);
        }

        @DisplayName("숫자 5개 적중 및 보너스 번호가 적중할 경우, 2등에 당첨되어야 한다")
        @Test
        void validate_second() {
            List<Integer> givenNumbers = List.of(1, 2, 3, 4, 5, 7);
            assertValidateResult(givenNumbers, Rank.SECOND, 1);
        }

        @DisplayName("숫자 5개 적중 및 보너스 번호 미적중일 경우, 3등에 당첨되어야 한다")
        @Test
        void validate_third() {
            List<Integer> givenNumbers = List.of(1, 2, 3, 4, 5, 8);
            assertValidateResult(givenNumbers, Rank.THIRD, 1);
        }

        @DisplayName("숫자 4개가 적줄할 경우, 4등에 당첨되어야 한다")
        @Test
        void validate_fourth() {
            List<Integer> givenNumbers = List.of(1, 2, 3, 4, 10, 11);
            assertValidateResult(givenNumbers, Rank.FOURTH, 1);
        }

        @DisplayName("숫자 3개가 적줄할 경우, 5등에 당첨되어야 한다")
        @Test
        void validate_fifth() {
            List<Integer> givenNumbers = List.of(1, 2, 3, 10, 11, 12);
            assertValidateResult(givenNumbers, Rank.FIFTH, 1);
        }
    }

    private void assertValidateResult(List<Integer> givenNumbers, Rank expectedRank,
        int expectedValue) {
        lottos = new ArrayList<>();
        lottos.add(Lotto.from(givenNumbers));
        lottoResultRepository.add(winningNumber, lottos);
        Map<Rank, Integer> calculateResult = lottoResultRepository.getCalculateResult();
        assertThat(calculateResult.get(expectedRank)).isEqualTo(expectedValue);
    }
}
