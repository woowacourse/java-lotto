package lotto.domain.ticket;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    private final List<LottoNumber> lottoNumbers = new ArrayList<>(
            Arrays.asList(
                    LottoNumber.of(1),
                    LottoNumber.of(2),
                    LottoNumber.of(3),
                    LottoNumber.of(4),
                    LottoNumber.of(5),
                    LottoNumber.of(6)
            ));

    @DisplayName("로또 티켓 정상 생성 테스트")
    @Test
    void Should_Not_ThrowException_When_ValidNumbers() {
        assertThatCode(() -> new LottoTicket(
                        lottoNumbers
                )
        )
                .doesNotThrowAnyException();
    }

    @DisplayName("유효하지 않은 사이즈의 로또 티켓 테스트")
    @Test
    void Should_ThrowException_When_InvalidSize() {
        lottoNumbers.remove(0);

        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 로또 번호를 가지는 티켓 테스트")
    @Test
    void Should_ThrowException_When_LottoNumberDuplicate() {
        lottoNumbers.remove(0);
        lottoNumbers.add(0, LottoNumber.of(2));

        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
