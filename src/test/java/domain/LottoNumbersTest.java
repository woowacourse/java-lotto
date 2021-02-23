package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumbersTest {

    @DisplayName("Lottonumbers를 생성하는 기능")
    @Test
    void generate() {
        //given
        List<LottoNumber> lottoNumberValues = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        //when
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberValues);

        //then
        assertThat(lottoNumbers).isNotNull();
    }

    @DisplayName("LottoNumbers에 중복이 있는 경우")
    @Test
    void generateWithDuplicatedLottoNumbers() {
        //given
        List<LottoNumber> lottoNumbers = parseToLottoNumberList(Arrays.asList(1, 1, 3, 4, 5, 6));

        //when //then
        assertThatThrownBy(() -> new LottoNumbers(lottoNumbers)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<List<LottoNumber>> numbersLengthNotSatisfied() {
        return Stream.of(parseToLottoNumberList(Arrays.asList(1, 2, 3, 4, 5))
                , parseToLottoNumberList(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
    }

    private static List<LottoNumber> parseToLottoNumberList(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @DisplayName("LottoNumbers 길이가 6이 아닌 경우")
    @ParameterizedTest
    @MethodSource("numbersLengthNotSatisfied")
    void generateIfLottoNumberLengthNotSatisfied(List<LottoNumber> input) {
        //when //then
        assertThatThrownBy(() -> new LottoNumbers(input)).isInstanceOf(IllegalArgumentException.class);
    }
}