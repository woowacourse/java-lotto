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
    class ParseToIntTest {
        @DisplayName("String 입력값을 Int 값으로 변환한다.")
        @Test
        void parseToIntCorrected() {
            String str = "1000";
            assertThat(parseToInt(str)).isEqualTo(1000);
        }

        @DisplayName("입력값에 숫자가 아닌 문자를 포함할 경우 예외가 발생한다.")
        @Test
        void parseErrorWhenIncludeNotDigit() {
            String str = "1000익";
            assertThatThrownBy(() -> parseToInt(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }

        @DisplayName("입력값이 빈칸를 포함할 경우 예외가 발생한다.")
        @Test
        void parseToErrorWhenIncludeBlank() {
            String str = " ";
            assertThatThrownBy(() -> parseToInt(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }

        @DisplayName("입력값이 Null일 경우, 예외가 발생한다.")
        @Test
        void parseErrorWhenIncludeNull() {
            String str = "";
            assertThatThrownBy(() -> parseToInt(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }
    }

    @Nested
    class ParseToListTest {
        @DisplayName("List 변환 시, 구분자를 가진 String 입력값을 List로 변환한다.")
        @Test
        void parseToListCorrected() {
            String str = "1,2,3,4,5,6";
            assertThat(parseToList(str)).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        }

        @DisplayName("입력값에 숫자가 아닌 문자를 포함할 경우 예외가 발생한다.")
        @Test
        void parseErrorWhenIncludeNotDigit() {
            String str = "1000익,1,2,3,4,5";
            assertThatThrownBy(() -> parseToList(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }

        @DisplayName("입력값이 빈칸를 포함할 경우 예외가 발생한다.")
        @Test
        void parseToErrorWhenIncludeBlank() {
            String str = " ";
            assertThatThrownBy(() -> parseToList(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }

        @DisplayName("입력값이 Null일 경우, 예외가 발생한다.")
        @Test
        void parseErrorWhenIncludeNull() {
            String str = "";
            assertThatThrownBy(() -> parseToList(str))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_NOT_NUMBER_TYPE.getMessage());
        }
    }
}
