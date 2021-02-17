package domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @DisplayName("유효한 값이면 객체 생성 성공")
    @Test
    void valueOf_validInput_success() {
        final List<LottoNumber> lottoNumbers = getValidNumbers();

        assertThatCode(() -> LottoTicket.valueOf(lottoNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 중복된 번호")
    @Test
    void valueOf_duplicateNumber_exceptionThrown() {
        final List<LottoNumber> lottoNumbers = getDuplicateNumbers();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoTicket.valueOf(lottoNumbers))
                .withMessageContaining("중복");
        assertThatCode(() -> LottoTicket.valueOf(lottoNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 숫자가 6개가 아닌 경우")
    @Test
    void valueOf_incorrectSize_exceptionThrown() {
        final List<LottoNumber> fewerLottoNumbers = getFewerNumbers();
        final List<LottoNumber> moreLottoNumbers = getMoreNumbers();

        assertAll(
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> LottoTicket.valueOf(fewerLottoNumbers))
                        .withMessageContaining("개수가 6이 아닙니다."),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> LottoTicket.valueOf(moreLottoNumbers))
                        .withMessageContaining("개수가 6이 아닙니다.")
        );
    }

    @DisplayName("6개의 숫자를 가진 로또 티켓 객체 생성")
    @Test
    void generate_success() {

    }

    private List<LottoNumber> getValidNumbers() {
        return Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(6),
                LottoNumber.valueOf(23),
                LottoNumber.valueOf(40),
                LottoNumber.valueOf(30),
                LottoNumber.valueOf(35)
        );
    }

    private List<LottoNumber> getDuplicateNumbers() {
        return Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(10),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(20)
        );
    }

    private List<LottoNumber> getFewerNumbers() {
        return Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(10),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(20)
        );
    }

    private List<LottoNumber> getMoreNumbers() {
        return Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(10),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(20),
                LottoNumber.valueOf(30)
        );
    }
}
