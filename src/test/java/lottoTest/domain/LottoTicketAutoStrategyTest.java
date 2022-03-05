package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketStrategy;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberAutoGeneratorTest {

    @Test
    void 로또번호를_6개_생성하는_기능() {
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6)
                        .stream()
                        .map(LottoNumber::valueOf)
                        .collect(Collectors.toList());

        LottoTicketStrategy lottoTicketStrategy = () -> new LottoTicket(lottoNumbers);

        assertThat(lottoTicketStrategy.generate().getNumbers()).isEqualTo(lottoNumbers);
    }
}
