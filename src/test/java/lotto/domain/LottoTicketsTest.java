package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("LottoTickets 생성 테스트")
    @Test
    void create() {
        LottoTickets lottoTickets = new LottoTickets(14);
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(14);
    }

    @DisplayName("같은 번호가 생성되었는지 테스트(정렬")
    @Test
    void numberSort() {
        List<List<Integer>> numbers = new ArrayList<>();
        numbers.add(Arrays.asList(2, 4, 6, 5, 3, 1));
        LottoTickets lottoTickets = new LottoTickets(1, numbers);
        List<Lotto> expectedLottoTickets = new ArrayList<>();

        expectedLottoTickets.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(lottoTickets.getLottoTickets()).isEqualTo(expectedLottoTickets);
    }
}
