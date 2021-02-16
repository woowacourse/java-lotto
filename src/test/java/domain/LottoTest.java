package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("Lotto를 생성하는 기능")
    @Test
    void generate() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        Lotto lotto = new Lotto(lottoNumbers);

        //then
        assertThat(lotto).isNotNull();
    }

    @DisplayName("Lotto 번호에 중복이 있는 경우")
    @Test
    void generateWithDuplicatedLottoNumbers() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 1, 3, 4, 5, 6);

        //when //then
        assertThatThrownBy(() -> new Lotto(lottoNumbers)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Lotto 번호의 길이가 6이 아닌 경우")
    @ParameterizedTest
    @MethodSource
    void generateIfLottoNumbersLengthNotSatisfied(List<Integer> input) {
        //when //then
        assertThatThrownBy(() -> new Lotto(input)).isInstanceOf(IllegalArgumentException.class);

    }

    private static Stream<List<Integer>> generateIfLottoNumbersLengthNotSatisfied() {
        return Stream.of(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    }
}
