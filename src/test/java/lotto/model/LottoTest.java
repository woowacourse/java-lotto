package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {

    private final WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

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
        List<Integer> list = Arrays.stream(input.split(", "))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
        Lotto lotto = new Lotto(list);

        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(Rank.valueOf(rank));
    }
}
