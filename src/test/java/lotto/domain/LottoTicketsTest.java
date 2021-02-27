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

    @DisplayName("lottoTickets를 넘겼을때 병합 테스트")
    @Test
    void selfLottoCreateTest() {
        LottoTickets lottoTickets = new LottoTickets(
            Arrays.asList("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 7")
        );
        lottoTickets = new LottoTickets(0, lottoTickets);
        List<Lotto> expectedLottoTickets = new ArrayList<>();
        expectedLottoTickets.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        expectedLottoTickets.add(new Lotto(Arrays.asList(2, 3, 4, 5, 6, 7)));
        assertThat(lottoTickets.getLottoTickets()).isEqualTo(expectedLottoTickets);
    }

    @DisplayName("문자열을 이용한 생성과 정렬 테스트")
    @Test
    void numberSort() {
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList("1, 3, 2, 6, 5, 4"));
        List<Lotto> expectedLottoTickets = new ArrayList<>();
        expectedLottoTickets.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(lottoTickets.getLottoTickets()).isEqualTo(expectedLottoTickets);
    }
}
