package lotto.domain;

import lotto.exceptions.LottoNumberDuplicatedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void initTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 2, 3, 4, 5, 6, 6));
        assertThat(lotto).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("로또 generate할 때 잘못된 입력을 넣는 경우")
    @MethodSource("generateLottoNumber")
    void wrongInitTest(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(LottoNumberDuplicatedException.class);
    }

    static Stream<Arguments> generateLottoNumber() {
        return Stream.of(Arguments.of(Arrays.asList(1, 2, 2, 3, 4, 5)),
                Arguments.of(Arrays.asList(1, 2, 2, 3, 3, 4, 5, 6, 7)),
                Arguments.of(Arrays.asList(15, 26, 37, 18, 19, 20, 1)),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5)));
    }
}