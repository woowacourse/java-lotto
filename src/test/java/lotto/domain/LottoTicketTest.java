package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {
    private List<LottoNumber> lottoNumbers;
    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>(
            Arrays.asList(
                LottoNumbers.get(5),
                LottoNumbers.get(4),
                LottoNumbers.get(2),
                LottoNumbers.get(3),
                LottoNumbers.get(6),
                LottoNumbers.get(1)
            ));
        lottoTicket = new LottoTicket(lottoNumbers);
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
        LottoTicket unsortedLottoTicket = new LottoTicket(lottoNumbers);
        assertThat(unsortedLottoTicket.getNumbers())
            .isSortedAccordingTo(Comparator.comparingInt(LottoNumber::getNumber));
    }

    @DisplayName("유효하지 않은 사이즈의 로또 티켓 테스트")
    @Test
    void Should_ThrowException_When_InvalidSize() {
        lottoNumbers.remove(0);

        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 로또 번호를 가지는 티켓 생성 테스트")
    @Test
    void Should_ThrowException_When_LottoNumberDuplicate() {
        List<LottoNumber> duplicateLottoNumbers = new ArrayList<>(lottoNumbers);
        duplicateLottoNumbers.remove(0);
        duplicateLottoNumbers.add(0, LottoNumbers.get(1));

        assertThatThrownBy(() -> new LottoTicket(duplicateLottoNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 로또 번호들을 반환하는지 테스트")
    @Test
    void Should_Return_AllLottoNumbers_When_Get() {
        assertThat(lottoTicket.getNumbers())
            .containsExactly(
                LottoNumbers.get(1),
                LottoNumbers.get(2),
                LottoNumbers.get(3),
                LottoNumbers.get(4),
                LottoNumbers.get(5),
                LottoNumbers.get(6)
            );
    }

    @DisplayName("로또티켓에 특정 로또번호가 포함되어있으면 true 반환")
    @Test
    void Should_Return_True_When_TicketContainsNumber() {
        assertThat(lottoTicket.contains(LottoNumbers.get(3))).isTrue();
    }

    @DisplayName("로또티켓에 특정 로또번호가 포함되어있지 않으면 false 반환")
    @Test
    void Should_Return_False_When_TicketNotContainsNumber() {
        assertThat(lottoTicket.contains(LottoNumbers.get(7))).isFalse();
    }

    @DisplayName("두 로또티켓에 모두 포함되어있는 로또번호들을 반환하는지 테스트")
    @Test
    void Should_Return_ExpectedNumbers_When_TwoTicketContainNumbers() {
        List<LottoNumber> lottoNumbers2 = new ArrayList<>(
            Arrays.asList(
                LottoNumbers.get(5),
                LottoNumbers.get(4),
                LottoNumbers.get(22),
                LottoNumbers.get(23),
                LottoNumbers.get(6),
                LottoNumbers.get(21)
            ));
        LottoTicket lottoTicket2 = new LottoTicket(lottoNumbers2);
        assertThat(lottoTicket.getMatchedLottoNumbers(lottoTicket2))
            .containsExactly(
                LottoNumbers.get(4),
                LottoNumbers.get(5),
                LottoNumbers.get(6)
            );
    }
}
