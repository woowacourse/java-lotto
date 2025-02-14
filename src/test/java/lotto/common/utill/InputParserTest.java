package lotto.common.utill;

import static lotto.common.utill.InputParser.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputParserTest {

    @DisplayName("String 입력값을 Int로 변환 가능하다.")
    @Test
    void testParseToInt() {
        String str = "1000";
        assertThat(parseToInt(str)).isEqualTo(1000);
    }

    @DisplayName("문자를 포함할 때, 예외가 발생한다.")
    @Test
    void testParseToIntError() {
        String str = "1000익";
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈칸를 포함할 때, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void testBlankError(String str) {
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Null를 포함할 때, 예외가 발생한다.")
    @Test
    void testNullError() {
        String str = null;
        assertThatThrownBy(() -> parseToInt(str))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("문자열을 리스트로 정상적으로 변환한다.")
    @Test
    void testPaserToList() {
        String str = "1,2,3,4,5,6";
        List<Integer> list = parseToList(str);

        assertThat(list).isNotNull();
    }

    @DisplayName("리스트로 변환할 때 잘못된 입력에 대해서 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ",
            "1,2,3,4,,5,6", "1.2,3,4,5,6"})
    void testParseToList_notValid(String str) {
        assertThatThrownBy(() -> parseToList(str))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
