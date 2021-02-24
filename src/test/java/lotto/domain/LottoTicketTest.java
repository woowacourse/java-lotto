package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.utils.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {
    @Test
    @DisplayName("성공 - 로또 숫자 범위 내")
    void generate_six_numbers() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(6)
        );

        assertThatCode(() -> new LottoTicket(lottoNumbers))
            .doesNotThrowAnyException();
    }


    @Test
    @DisplayName("실패 - 로또 숫자 범위 미만")
    void generate_not_six_numbers() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4)
        );

        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 로또 숫자 범위 초과")
    void generate_not_six_numbers1() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(6),
            LottoNumber.valueOf(7)
        );

        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 중복되는 로또 숫자 존재")
    void duplicated_number() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(5)
        );

        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("성공 - 문자열로 LottoTicket 생성")
    void generate_by_string() {
        assertThatCode(() -> new LottoTicket("1,2,3,4,5,6"))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("성공 - 문자열로 LottoTicket 생성")
    void generate_by_string2() {
        LottoTicket lottoTicket1 = new LottoTicket("1,2,3,4,5,6");
        List<LottoNumber> lottoNumbers = Arrays.asList(
            LottoNumber.valueOf(1),
            LottoNumber.valueOf(2),
            LottoNumber.valueOf(3),
            LottoNumber.valueOf(4),
            LottoNumber.valueOf(5),
            LottoNumber.valueOf(6)
        );

        assertThat(lottoTicket1.getUnmodifiableList()).isEqualTo(lottoNumbers);
    }

}