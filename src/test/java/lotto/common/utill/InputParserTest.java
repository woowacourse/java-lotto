package lotto.common.utill;

import static lotto.common.constant.ErrorMessage.*;
import static lotto.common.utill.InputParser.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Nested
    @DisplayName("String 입력값을 Int 값으로 변환할 때")
    class ParseToIntTest {
        @DisplayName("정상적으로 작동한다.")
        @Test
        void test_ParseToInt_Corrected() {
            String str = "1000";
            assertThat(parseToInt(str)).isEqualTo(1000);
        }

        @DisplayName("입력값에 숫자가 아닌 문자를 포함할 경우 예외가 발생한다.")
        @Test
        void parseError_IncludeNotDigit() {
            String str = "1000익";
            assertThatThrownBy(() -> parseToInt(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }

        @DisplayName("입력값이 빈칸를 포함할 경우 예외가 발생한다.")
        @Test
        void parseError_IncludeBlank() {
            String str = " ";
            assertThatThrownBy(() -> parseToInt(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }

        @DisplayName("입력값이 Null일 경우, 예외가 발생한다.")
        @Test
        void parseError_IncludeNull() {
            String str = "";
            assertThatThrownBy(() -> parseToInt(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }
    }

    @Nested
    @DisplayName("String 입력값을 List<Integer> 값으로 변환할 때")
    class ParseToListTest {
        @DisplayName("구분자를 가진 String 입력값을 List로 변환한다.")
        @Test
        void test_ParseToList_Corrected() {
            String str = "1,2,3,4,5,6";
            assertThat(parseToList(str)).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        }

        @DisplayName("입력값에 숫자가 아닌 문자를 포함할 경우 예외가 발생한다.")
        @Test
        void parseError_IncludeNotDigit() {
            String str = "1000익,1,2,3,4,5";
            assertThatThrownBy(() -> parseToList(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }

        @DisplayName("입력값이 빈칸를 포함할 경우 예외가 발생한다.")
        @Test
        void parseError_IncludeBlank() {
            String str = " ";
            assertThatThrownBy(() -> parseToList(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }

        @DisplayName("입력값이 Null일 경우, 예외가 발생한다.")
        @Test
        void parseError_IncludeNull() {
            String str = "";
            assertThatThrownBy(() -> parseToList(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }
    }
}
