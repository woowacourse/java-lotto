package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    private static final List<Integer> dummyLottoNumber = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    @DisplayName("로또 티켓이 정상적으로 생성됐는지 검사한다.")
    void createLottoTicketsTest() {
        int purchaseMoney = 17000;
        GenerateStrategy generateStrategy = () -> new ArrayList<>(dummyLottoNumber);
        LottoTickets lottoTickets = new LottoTickets(purchaseMoney, generateStrategy);
        lottoTickets.tickets()
                .forEach(lottoTicket -> assertThat(lottoTicket.lottoNumbers()).isEqualTo(dummyLottoNumber));
    }
}