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
        final List<Integer> lottoNumbers = getValidNumbers();

        assertThatCode(() -> LottoTicket.generateManual(lottoNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 중복된 번호")
    @Test
    void valueOf_duplicateNumber_exceptionThrown() {
        final List<Integer> lottoNumbers = getDuplicateNumbers();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoTicket.generateManual(lottoNumbers))
                .withMessageContaining("중복");
    }

    @DisplayName("객체 생성 실패 : 숫자가 6개가 아닌 경우")
    @Test
    void valueOf_incorrectSize_exceptionThrown() {
        final List<Integer> fewerLottoNumbers = getFewerNumbers();
        final List<Integer> moreLottoNumbers = getMoreNumbers();

        assertAll(
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> LottoTicket.generateManual(fewerLottoNumbers))
                        .withMessageContaining("개수가 6이 아닙니다."),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> LottoTicket.generateManual(moreLottoNumbers))
                        .withMessageContaining("개수가 6이 아닙니다.")
        );
    }

    private List<Integer> getValidNumbers() {
        return Arrays.asList(1, 6, 23, 40, 30, 35);
    }

    private List<Integer> getDuplicateNumbers() {
        return Arrays.asList(1, 1, 5, 10, 13, 29);
    }

    private List<Integer> getFewerNumbers() {
        return Arrays.asList(1, 5, 10, 13, 20);
    }

    private List<Integer> getMoreNumbers() {
        return Arrays.asList(1, 3, 5, 10, 13, 20, 30);
    }
}
