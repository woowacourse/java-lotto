package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {

    private final WinningLotto winningLotto = new WinningLotto(convertLotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

    @ParameterizedTest
    @DisplayName("로또 1등부터 미당첨인 경우까지 모두 테스트")
    @CsvSource(value = {
        "1, 2, 3, 4, 5, 6:FIRST",
        "1, 2, 3, 4, 5, 7:SECOND",
        "1, 2, 3, 4, 5, 8:THIRD",
        "1, 2, 3, 4, 8, 9:FOURTH",
        "1, 2, 3, 8, 9, 10:FIFTH",
        "1, 2, 8, 9, 10, 11:LOSER"
    }, delimiter = ':')
    void rankTest(String input, String rank) {
        List<LottoNumber> list = Arrays.stream(input.split(", "))
            .mapToInt(Integer::parseInt)
            .boxed()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        Lotto lotto = new Lotto(list);

        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(Rank.valueOf(rank));
    }

    @Test
    @DisplayName("보너스 번호 포함 여부")
    void bonusContainTest() {
        List<LottoNumber> list = Stream.of(1,2,3,4,5,6)
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        Lotto lotto = new Lotto(list);

        assertThat(lotto.contains(new LottoNumber(1))).isTrue();
    }

    Lotto convertLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList()));
    }
}
