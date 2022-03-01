import domain.LottoNumber;
import domain.LottoTicketNumbers;
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
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호 중복되면 예외")
    void validateDuplicateLottoNumberException() {
        List<LottoNumber> inputLottoNumbers = IntStream.of(1, 2, 3, 4, 5, 5)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoTicketNumbers(inputLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호 정렬")
    void sortLottoNumbers() {
        List<LottoNumber> inputLottoNumbers = IntStream.of(4, 3, 2, 1, 6, 5)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());

        LottoTicketNumbers lottoTicketNumbers = new LottoTicketNumbers(inputLottoNumbers);

        int[] lottoNumbersSorted = lottoTicketNumbers.getLottoNumbers().stream()
                .mapToInt(LottoNumber::getNumber)
                .toArray();

        assertThat(lottoNumbersSorted).isSorted();
    }

    @Test
    @DisplayName("로또 번호 중복 개수 구하기")
    void countDuplicateNumbersTest() {
        List<LottoNumber> inputLottoNumbers1 = IntStream.of(4, 3, 2, 1, 6, 5)
            .mapToObj(LottoNumber::getInstance)
            .collect(Collectors.toList());

        LottoTicketNumbers lottoTicketNumbers1 = new LottoTicketNumbers(inputLottoNumbers1);

        List<LottoNumber> inputLottoNumbers2 = IntStream.of(4, 3, 2, 1, 6, 5)
            .mapToObj(LottoNumber::getInstance)
            .collect(Collectors.toList());

        LottoTicketNumbers lottoTicketNumbers2 = new LottoTicketNumbers(inputLottoNumbers2);

        assertThat(lottoTicketNumbers1.countDuplicateNumbers(lottoTicketNumbers2))
            .isEqualTo(6);
    }

    @Test
    @DisplayName("방어적 복사 테스트")
    void defensiveCopyTest() {
        List<LottoNumber> inputLottoNumbers = IntStream.of(4, 3, 2, 1, 6, 5)
            .mapToObj(LottoNumber::getInstance)
            .collect(Collectors.toList());

        LottoTicketNumbers lottoTicketNumbers = new LottoTicketNumbers(inputLottoNumbers);

        List<LottoNumber> copiedLottoNumbers = lottoTicketNumbers.getLottoNumbers();

        assertThat(inputLottoNumbers).isNotSameAs(copiedLottoNumbers);
        assertThatThrownBy(() -> copiedLottoNumbers.add(LottoNumber.getInstance(45)))
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
