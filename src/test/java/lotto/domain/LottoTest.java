package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTest {

    @Test
    @DisplayName("랜덤으로 생성할 때 숫자 6개를 만들어내는가")
    void Generate_6_Number() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getLotto().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("랜덤으로 생성한 모든 숫자가 1~45 사이에 위치하는가")
    void Is_Between_1_To_45() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getLotto().stream()
                .allMatch(lottoNumber -> lottoNumber.getNumber() >= 1 && lottoNumber.getNumber() <= 45)).isTrue();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("입력받은 당첨번호가 6개가 아니라면 예외처리하는가?")
    void No_6_Input_Numbers(List<Integer> inputValues) {
        assertThatThrownBy(() -> new Lotto(inputValues)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> No_6_Input_Numbers() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @Test
    @DisplayName("입력받은 당첨번호가 모두 범위내에 있는지")
    void All_Numbers_In_Range() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(0, 2, 3, 4, 5, 6))).isInstanceOf(
                IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력받은 당첨번호가 중복되는 값이 있는지")
    void Duplicate_Number_Exist() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 1, 3, 4, 5, 6))).isInstanceOf(
                IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("겹치는 숫자가 몇개인지 잘 찾아낼 수 있는지")
    @MethodSource
    void Count_Same_Number(List<Integer> inputValue, int correctNumber) {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto comparisonNumber = new Lotto(inputValue);
        assertThat(lotto.countSameNumber(comparisonNumber)).isEqualTo(correctNumber);
    }

    public static Stream<Arguments> Count_Same_Number() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 5, 7), 5),
                Arguments.arguments(Arrays.asList(1, 2, 3, 4, 7, 8), 4),
                Arguments.arguments(Arrays.asList(1, 2, 3, 7, 8, 9), 3),
                Arguments.arguments(Arrays.asList(1, 2, 7, 8, 9, 10), 2),
                Arguments.arguments(Arrays.asList(1, 7, 8, 9, 10, 11), 1),
                Arguments.arguments(Arrays.asList(7, 8, 9, 10, 11, 12), 0)
        );
    }
}
