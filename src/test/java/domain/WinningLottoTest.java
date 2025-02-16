package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

    @Test
    void 보너스볼과_당첨번호가_중복된_경우_예외를_반환한다() {
        //given
        Number bonus = new Number(3);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        //when //then
        assertThatThrownBy(() -> new WinningLotto(lotto, bonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    void 당첨_번호와_몇개가_같은지_계산할_수_있다() {
        //given
        Number bonus = new Number(7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 10, 11, 12, 13));

        //when
        int matchCount = winningLotto.calculateMatchCount(purchasedLotto);

        //then
        assertThat(matchCount).isEqualTo(2);
    }


    @Test
    void 보너스번호와_일치하는지_판단한다() {
        //given
        Number bonus = new Number(7);
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        WinningLotto winningLotto = new WinningLotto(lotto1, bonus);
        //when
        boolean actual = winningLotto.containsBonusNumber(lotto2);
        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("generateLottos")
    void 당첨순위를_판단할_수_있다(List<Integer> numbers, Rank expected) {
        //given
        Number bonus = new Number(7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);
        Lotto purchasedLotto = new Lotto(numbers);
        //when
        Rank actual = winningLotto.calculateRank(purchasedLotto);
        //then
        assertThat(expected).isEqualTo(actual);
    }

    private static Stream<Arguments> generateLottos() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), Rank.FIRST
                ),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), Rank.SECOND
                ),
                Arguments.of(List.of(1, 2, 3, 4, 5, 8), Rank.THIRD
                ),
                Arguments.of(List.of(1, 2, 3, 4, 8, 9), Rank.FOURTH),
                Arguments.of(List.of(1, 2, 3, 8, 9, 10), Rank.FIFTH),
                Arguments.of(List.of(1, 2, 8, 9, 10, 11), Rank.MISS)
        );
    }
}
