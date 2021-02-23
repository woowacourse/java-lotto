package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>(
            Arrays.asList(
                LottoNumbers.of(5),
                LottoNumbers.of(4),
                LottoNumbers.of(2),
                LottoNumbers.of(3),
                LottoNumbers.of(6),
                LottoNumbers.of(1)
            ));
    }

    @DisplayName("로또 티켓 정상 생성 테스트")
    @Test
    void Should_Not_ThrowException_When_ValidNumbers() {
        assertThatCode(() -> new LottoTicket(lottoNumbers))
            .doesNotThrowAnyException();
    }

    @DisplayName("로또 티켓 생성 시, 로또 번호 오름차순 정렬상태인지 테스트")
    @Test
    void Should_Be_Sorted_When_LottoTicketCreated() {
        List<LottoNumber> unsortedLottoNumbers = lottoNumbers;
        LottoTicket unsortedLottoTicket = new LottoTicket(unsortedLottoNumbers);

        for (int i = 0; i < unsortedLottoNumbers.size(); i++) {
            assertThat(unsortedLottoNumbers.get(i))
                .isNotEqualTo(unsortedLottoTicket.getLottoNumbers().get(i));
        }

        List<LottoNumber> sortedLottoNumbers = unsortedLottoNumbers.stream()
            .sorted(Comparator.comparingInt(LottoNumber::getNumber))
            .collect(Collectors.toList());

        for (int i = 0; i < sortedLottoNumbers.size(); i++) {
            assertThat(sortedLottoNumbers.get(i))
                .isEqualTo(unsortedLottoTicket.getLottoNumbers().get(i));
        }
    }

    @DisplayName("유효하지 않은 사이즈의 로또 티켓 테스트")
    @Test
    void Should_ThrowException_When_InvalidSize() {
        lottoNumbers.remove(0);

        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 로또 번호를 가지는 티켓 테스트")
    @Test
    void Should_ThrowException_When_LottoNumberDuplicate() {
        lottoNumbers.remove(0);
        lottoNumbers.add(0, LottoNumbers.of(1));

        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
