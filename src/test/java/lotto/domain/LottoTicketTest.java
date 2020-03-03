package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import lotto.generator.NumberGenerator;
import lotto.generator.UserInputNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @Test
    void validateDistinctNumberTest_중복숫자가_있을_때() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> invalidNumbers = numberGenerator.generateNumbers("1,2,3,4,5,5");
        assertThatThrownBy(() -> new LottoTicket(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_NUMBER.getMessage());
    }

    @Test
    void validateNumberCountTest_로또_숫자가_6개가_아닐_때() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> invalidNumbers = numberGenerator.generateNumbers("1,2,3,4,5");

        assertThatThrownBy(() -> new LottoTicket(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_COUNT_NOT_SIX.getMessage());
    }

    @Test
    void validateDistinctNumberTest_올바른_로또_숫자일_때() {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        List<LottoNumber> validNumbers = numberGenerator.generateNumbers("1,2,3,4,5,45");

        new LottoTicket(validNumbers);
    }
}
