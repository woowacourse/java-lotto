package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketNumbersTest {

    @ParameterizedTest
    @ValueSource(ints = {5, 7})
    @DisplayName("로또 번호 6개 초과시 예외")
    void exceededLottoNumberException(int source) {
        List<LottoNumber> inputLottoNumbers = IntStream.rangeClosed(1, source)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoTicketNumbers(inputLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6자리");
    }

    @Test
    @DisplayName("로또 번호 중복되면 예외")
    void validateDuplicateLottoNumberException() {
        List<LottoNumber> inputLottoNumbers = IntStream.of(1, 2, 3, 4, 5, 5)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoTicketNumbers(inputLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복");
    }

    @Test
    @DisplayName("로또 번호 정렬")
    void sortLottoNumbers() {
        List<LottoNumber> inputLottoNumbers = IntStream.of(4, 3, 2, 1, 6, 5)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());

        LottoTicketNumbers lottoTicketNumbers = new LottoTicketNumbers(inputLottoNumbers);

        List<LottoNumber> lottoNumbers = lottoTicketNumbers.getLottoNumbers();

        assertThat(lottoNumbers).extracting(LottoNumber::getNumber)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("로또 번호 비교")
    void countMatchingNumbers() {
        List<LottoNumber> inputLottoNumbers = IntStream.of(4, 3, 2, 1, 6, 5)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());
        LottoTicketNumbers lottoTicketNumbers = new LottoTicketNumbers(inputLottoNumbers);

        assertThat(lottoTicketNumbers.countDuplicateNumbers(lottoTicketNumbers)).isEqualTo(6);
    }
}
