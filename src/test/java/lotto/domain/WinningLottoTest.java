package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {


    private List<LottoNumber> convertNumbersToLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .map(number -> LottoNumber.of(number))
                .collect(Collectors.toList());
    }

    @Test
    public void 당첨로또가_1등을_검사하는지() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.of(convertNumbersToLottoNumbers(numbers));
        WinningLotto winningLotto = WinningLotto.of(lotto, LottoNumber.of(8));
        assertThat(winningLotto.match(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    public void 당첨로또가_2등로또를_검사하는지() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = Arrays.asList(1, 2, 3, 4, 5, 8);

        Lotto lotto = Lotto.of(convertNumbersToLottoNumbers(numbers));
        WinningLotto winningLotto = WinningLotto.of(lotto, LottoNumber.of(8));

        Lotto anotherLotto = Lotto.of(convertNumbersToLottoNumbers(anotherNumbers));

        assertThat(winningLotto.match(anotherLotto)).isEqualTo(Rank.SECOND);
    }
}
