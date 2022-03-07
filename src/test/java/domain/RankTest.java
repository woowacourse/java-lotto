package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Rank 테스트")
public class RankTest {
    @ParameterizedTest(name = "구매 로또: [{0}, {1}, {2}, {3}, {4}, {5}], 기대 순위: {6}")
    @CsvSource(value = {
            "39,40,41,42,43,44,NO_MATCH",
            "1,2,3,9,10,11,FIFTH",
            "1,2,3,4,9,10,FOURTH",
            "1,2,3,4,5,10,THIRD",
            "1,2,3,4,5,7,SECOND",
            "1,2,3,4,5,6,FIRST"
    })
    @DisplayName("Rank 는 Lotto 와 WinningLotto 를 전달받아 일치하는 Rank 를 반환할 수 있다.")
    void createByLottoAndWinningLotto(
            int firstNumber,
            int secondNumber,
            int thirdNumber,
            int fourthNumber,
            int fifthNumber,
            int sixthNumber,
            String rankText
    ) {
        // given
        Lotto lotto = Lotto.fromRawValues(
                Set.of(firstNumber, secondNumber, thirdNumber, fourthNumber, fifthNumber, sixthNumber));
        WinningLotto winningLotto = new WinningLotto(
                Lotto.fromRawValues(Set.of(1, 2, 3, 4, 5, 6)),
                LottoNumber.from(7)
        );

        // when
        Rank actual = Rank.createByLottoAndWinningLotto(lotto, winningLotto);
        Rank expected = Rank.valueOf(rankText);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
