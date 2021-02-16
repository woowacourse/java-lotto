package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {
    @Test
    @DisplayName("성공 - 로또 숫자 범위 내")
    void generate_six_numbers() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        );

        assertThatCode(() -> {
            LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        }).doesNotThrowAnyException();
    }


    @Test
    @DisplayName("실패 - 로또 숫자 범위 미만")
    void generate_not_six_numbers() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4)
        );

        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("실패 - 로또 숫자 범위 초과")
    void generate_not_six_numbers1() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6),
            new LottoNumber(7)
        );

        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("실패 - 중복되는 로또 숫자 존재")
    void duplicated_number() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(5)
        );

        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
            .isInstanceOf(RuntimeException.class);
    }

}