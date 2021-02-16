package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTicketTest {

    @DisplayName("당첨 번호 비교 결과 테스트")
    @Test
    void 당첨_번호_비교_결과_테스트(){
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 8, 9, 10);
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        WinningLottoTicket winningTicket = new WinningLottoTicket(winningNumbers, bonusNumber);

        assertThat(winningTicket.compareNumbers(lottoTicket))
            .isEqualTo("5등!");
    }


}