package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class LottoMachineTest {

    @DisplayName("자동 로또 티켓 생성")
    @Test
    void valueOf_validPrice_success() {
        assertThatCode(() -> LottoMachine.generateAutoLottoTickets(3))
            .doesNotThrowAnyException();
        assertThat(LottoMachine.generateAutoLottoTickets(14).size()).isEqualTo(14);
    }

    @DisplayName("수동 로또 생성 실패 : 중복 숫자가 들어가 있는 번호 입력")
    @Test
    void generate_duplicatedNumber_ExceptionThrown() {
        List<String> lottoNumbers = Stream.<String>builder()
            .add("1, 2, 3, 4, 5, 4")
            .add("7, 8, 9, 10, 11, 12")
            .add("13, 14, 15, 16, 17, 18")
            .build()
            .collect(Collectors.toList());

        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoMachine.generateManualLottoTickets(lottoNumbers))
            .withMessageContaining("개수가 6이 아닙니다.");
    }
}