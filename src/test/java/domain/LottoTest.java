package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testUtil.StaticNumberPicker;
import util.NumberPicker;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    
    @Test
    void 로또_번호가_6개가_아니면_예외가_발생한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        
        assertThatThrownBy(() -> new Lotto(numbers))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개가 되어야 합니다.");
    }
    
    @Test
    void 로또_번호에_중복이_있으면_예외가_발생한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);
        
        assertThatThrownBy(() -> new Lotto(numbers))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복되면 안됩니다.");
    }
    
    @Test
    void 구입_금액만큼_로또를_구입한다() {
        // given
        int money = 4000;
        NumberPicker numberPicker = new StaticNumberPicker(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(14, 15, 16, 13, 12, 9),
                List.of(43, 41, 40, 23, 35, 22),
                List.of(9, 7, 13, 14, 16, 2)
        ));
        
        // when
        List<Lotto> result = Lotto.purchase(money, numberPicker);
        
        // then
        
        assertThat(result).hasSize(4);
        assertThat(result.stream().map(Lotto::getNumbers).toList())
                .containsAll(List.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(14, 15, 16, 13, 12, 9),
                        List.of(43, 41, 40, 23, 35, 22),
                        List.of(9, 7, 13, 14, 16, 2)
                ));
    }
    
    @Test
    void 금액이_1000원_미만이면_예외를_발생한다() {
        int money = 999;
        
        NumberPicker numberPicker = new StaticNumberPicker(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(14, 15, 16, 13, 12, 9),
                List.of(43, 41, 40, 23, 35, 22),
                List.of(9, 7, 13, 14, 16, 2)
        ));
        assertThatThrownBy(() -> Lotto.purchase(money, numberPicker))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage("금액은 1000원 이상이여아 합니다.");
    }
    
    @ParameterizedTest
    @MethodSource("provideMatchNumbers")
    void 당첨된_로또번호_개수를_계산한다(List<Integer> matchNumbers, int expected) {
        //given
        Lotto sut = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        
        //when
        int result = sut.getMatchCount(matchNumbers);
        
        //than
        assertThat(result).isEqualTo(expected);
    }
    
    @Test
    void 로또_당첨_번호에_중복이_있으면_예외를_발생한다() {
        // given
        Lotto sut = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> duplicatedMatchNumbers = List.of(1, 2, 3, 4, 5, 5);
        
        // expected
        assertThatThrownBy(() -> sut.getMatchCount(duplicatedMatchNumbers))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 당첨 번호는 중복되면 안됩니다.");
    }
    
    @ParameterizedTest
    @MethodSource("provideMatchBonusNumbers")
    void 보너스번호_당첨_여부를_판단한다(int bonusNumber, boolean expected) {
        //given
        Lotto sut = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        
        //when
        boolean result = sut.isBonusMatch(bonusNumber);
        
        //than
        assertThat(result).isEqualTo(expected);
    }
    
    public static Stream<Arguments> provideMatchNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), 5),
                Arguments.of(List.of(1, 2, 3, 4, 8, 9), 4),
                Arguments.of(List.of(21, 23, 13, 11, 19, 20), 0)
        );
    }
    
    public static Stream<Arguments> provideMatchBonusNumbers() {
        return Stream.of(
                Arguments.of(6, true),
                Arguments.of(5, true),
                Arguments.of(7, false),
                Arguments.of(8, false)
        );
    }
}
