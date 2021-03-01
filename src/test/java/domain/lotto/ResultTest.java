package domain.lotto;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("객체 생성 성공")
    @Test
    void generate_validInput_successful() {
        assertThatCode(() -> new Result(getLottoNumbers(), getValidBonusNumber()))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 보너스 번호가 당첨 번호에 포함")
    @Test
    void generate_duplicateNumber_exceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Result(getLottoNumbers(), getInvalidBonusNumber()))
                .withMessageContaining("포함");
    }

    @DisplayName("당첨 번호와 보너스 번호를 이용하여 계산 성공")
    @Test
    void calculate_successful() {
        final Result result = new Result(getLottoNumbers(), getValidBonusNumber());
        assertThatCode(() -> result.calculate(getValidLottoTickets()))
                .doesNotThrowAnyException();
    }

    private List<Integer> getLottoNumbers() {
        return Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    private int getValidBonusNumber() {
        return 10;
    }

    private int getInvalidBonusNumber() {
        return 1;
    }

    private List<List<Integer>> getLottoTicketsNumbers() {
        return Arrays.asList(
                getLottoNumbers()
        );
    }

    private LottoTickets getValidLottoTickets() {
        return new LottoTickets(getLottoTicketsNumbers(), 1);
    }
}
