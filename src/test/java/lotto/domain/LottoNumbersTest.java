package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumbersTest {
    @Test
    @DisplayName("로또 번호들 간에 중복이 있으면 예외를 발생시킨다.")
    void create_ExceptionByDuplicatedLottoNumbers() {
        //given
        final List<String> duplicatedNumberValues = Arrays.asList("1", "1", "2", "3", "4", "5");
        final String expectedExceptionMessage = "같은 줄 로또 번호 간에 중복이 존재합니다.";
        //when then
        assertThatThrownBy(() -> new LottoNumbers(duplicatedNumberValues))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
