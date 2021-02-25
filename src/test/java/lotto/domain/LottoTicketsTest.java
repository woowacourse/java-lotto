package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketsTest {

    @DisplayName("자동 로또 개수에 맞게 자동 로또 티켓을 구입했는지 확인한다.")
    @Test
    void makeAutoLottoTickets() {
        int autoTicketCounts = 3;
        LottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();

        LottoTickets lottoTickets = LottoTickets.generateLottoTickets(autoTicketCounts, randomLottoNumberGenerator);

        assertThat(lottoTickets.size()).isEqualTo(3);
    }

    @DisplayName("수동 로또 개수에 맞게 수동 로또 티켓을 구입했는지 확인한다.")
    @Test
    void makeManualLottoTickets() {
        List<List<Integer>> numberGroup = new ArrayList<>();
        numberGroup.add(Arrays.asList(1, 2, 3, 4, 5, 6));
        numberGroup.add(Arrays.asList(7, 8, 9, 10, 11, 12));

        LottoTickets lottoTickets = LottoTickets.generateLottoTickets(numberGroup.size(), new ManualLottoNumberGenerator(numberGroup));

        assertThat(lottoTickets.size()).isEqualTo(2);
    }

    @DisplayName("수동 로또 번호가 잘못되었을 때 에러 확인")
    @Test
    void cannotMakeManualLottoTickets() {
        List<List<Integer>> numberGroup = new ArrayList<>();
        numberGroup.add(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        numberGroup.add(Arrays.asList(7, 8, 9, 10, 11, 11));
        numberGroup.add(Arrays.asList(7, 8, 11));

        assertThatThrownBy(() -> {
            LottoTickets.generateLottoTickets(numberGroup.size(), new ManualLottoNumberGenerator(numberGroup));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓은 중복되지 않은 6자리의 숫자로 구성되어야 합니다.");
    }

    @DisplayName("당첨 번호와 구매한 로또 티켓을 비교하여 결과를 반환한다.")
    @Test
    void getLottoResult() {
        LottoNumberGenerator lottoNumberGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoTickets lottoTickets = LottoTickets.generateLottoTickets(3, lottoNumberGenerator);
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(Arrays.asList(1, 2, 3, 4, 5, 7), 6);

        LottoStatistics lottoStatistics = lottoTickets.getStatistics(winningLottoTicket);

        assertThat(lottoStatistics.getCounts(LottoRank.SECOND)).isEqualTo(3);
    }
}