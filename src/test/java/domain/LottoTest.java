package domain;

import static org.junit.jupiter.api.Assertions.*;

import constant.LottoConstants;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @ParameterizedTest
    @MethodSource("getLottoNumbers")
    @DisplayName("로또 번호 범위 예외 테스트")
    void lotto_number_out_of_range(List<Integer> numbers) {
        Assertions.assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또의 숫자가 %d~%d의 유효 범위를 벗어납니다.", LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END));
    }

    static Stream<Arguments> getLottoNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 46)),
                Arguments.of(List.of(-3, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(0, 1, 2, 3, 4, 5))
        );
    }

}
