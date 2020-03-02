package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @Test
    void validateDistinctNumberTest_중복숫자가_있을_때() {
        List<LottoNumber> invalidNumbers = new ArrayList<LottoNumber>(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("5")));

        assertThatThrownBy(() -> new LottoTicket(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_NUMBER.getMessage());
    }

    @Test
    void validateNumberCountTest_로또_숫자가_6개가_아닐_때() {
        List<LottoNumber> invalidNumbers = new ArrayList<LottoNumber>(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5")));

        assertThatThrownBy(() -> new LottoTicket(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_COUNT_NOT_SIX.getMessage());
    }

    @Test
    void validateDistinctNumberTest_올바른_로또_숫자일_때() {
        List<LottoNumber> validNumbers = new ArrayList<LottoNumber>(Arrays.asList(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("45")));

        new LottoTicket(validNumbers);
    }


}
