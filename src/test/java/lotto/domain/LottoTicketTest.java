package lotto.domain;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {
    private final List<LottoNumber> lottoNumbers = new ArrayList<>(
        Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
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

    @DisplayName("셔플된 로또 티켓 정상 생성 테스트")
    @Test
    void Should_SizeEqualToSix_When_CreatedWithoutConstructorParameter() {
        LottoTicket lottoTicket = new LottoTicket();
        assertThat(lottoTicket.getLottoTicketNumbers().size()).isEqualTo(6);
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
        lottoNumbers.add(0, new LottoNumber(2));

        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
