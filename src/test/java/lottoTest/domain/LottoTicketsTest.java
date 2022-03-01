package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoTickets;
import lotto.domain.lottonumbergenerator.LottoNumberAutoGenerator;
import lotto.domain.lottonumbergenerator.LottoNumberGenerator;
import lotto.domain.lottonumbergenerator.LottoNumberManualGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketsTest {

    @Test
    void 입력한_금액만큼_자동_로또를_생성하는_기능() {
        LottoTickets lottoTickets = new LottoTickets(new LottoNumberAutoGenerator(), 14);
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(14);
    }

    @Test
    void 입력한_숫자만큼_수동_로또를_생성하는_기능_테스트() {
        List<List<Integer>> numbers = new ArrayList<>();
        numbers.add(List.of(1, 2, 3, 4, 5, 6));
        numbers.add(List.of(7, 8, 9, 10, 11, 12));

        LottoNumberGenerator lottoNumberGenerator = new LottoNumberManualGenerator(numbers);
        LottoTickets lottoTickets = new LottoTickets(lottoNumberGenerator, numbers.size());
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(numbers.size());
    }
}
