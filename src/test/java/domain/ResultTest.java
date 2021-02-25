package domain;

import java.util.Arrays;
import java.util.List;

class ResultTest {

    private Result result;

//    @BeforeEach
//    void setUp() {
//        result = new Result(createValidWinningNumbers(), createValidLottoTickets());
//    }

//    @DisplayName("총 수익률 계산 성공")
//    @Test
//    void getProfitRate_success() {
//        assertThat(result.calculateProfitRate()).isEqualTo(2_030_005_000.0 / 3000.0);
//    }

    List<LottoTicket> createValidLottoTickets() {
        return Arrays.asList(
                LottoTicket.generateManual(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoTicket.generateManual(Arrays.asList(4, 5, 6, 7, 8, 9)),
                LottoTicket.generateManual(Arrays.asList(1, 2, 3, 4, 5, 9))
        );
    }
}
