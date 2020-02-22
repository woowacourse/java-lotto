package lotto.util;

import lotto.Exception.DuplicationException;
import lotto.Exception.NumberOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidateWinningTicketUtilsTest {

    @Test
    @DisplayName("당첨 번호가 숫자인지 확인")
    void validate_winning_balls_number() {
        String[] inputValue = { "1","2","3","4","5","6"};

        assertThatCode(()->ValidateWinningTicketUtils.validateWinningBallsNumber(inputValue))
                .doesNotThrowAnyException();
    }
    @Test
    @DisplayName("당첨 번호가 숫자가 아닐때 오류 확인")
    void throw_validate_winning_balls_number() {
        String[] inputValue = { "1","2","3","4","5","가"};

        assertThatThrownBy(()->ValidateWinningTicketUtils.validateWinningBallsNumber(inputValue))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    @DisplayName("숫자가 6개일때 테스트")
    void validate_winning_balls_length() {
        String[] inputValue = { "1","2","3","4","5","6"};

        assertThatCode(()->ValidateWinningTicketUtils.validateWinningBallsLength(inputValue))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자가 6개가 아닐때 테스트")
    void throw_validate_winning_balls_length() {
        String[] inputValue = { "1","2","3","4","5"};

        assertThatThrownBy(()->ValidateWinningTicketUtils.validateWinningBallsLength(inputValue))
                .isInstanceOf(NumberOutOfRangeException.class);
    }

    @Test
    @DisplayName("중복이 없을경우 테스트")
    void validate_duplicated_winning_balls() {
        String[] inputValue = { "1","2","3","4","5","6"};

        assertThatCode(()->ValidateWinningTicketUtils.validateDuplicatedWinningBalls(inputValue))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복이 있을경우 테스트")
    void throw_validate_duplicated_winning_balls() {
        String[] inputValue = { "1","2","3","4","5","5"};

        assertThatThrownBy(()->ValidateWinningTicketUtils.validateDuplicatedWinningBalls(inputValue))
                .isInstanceOf(DuplicationException.class);
    }
}