package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketTest {


    @Test
    @DisplayName("생성된 로또티켓 숫자가 6개인지 확인")
    void LottoTicket() {
        LottoFactory.getInstance();
        List<Integer> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(1);
        lottoNumbers.add(1);
        lottoNumbers.add(2);
        lottoNumbers.add(3);
        lottoNumbers.add(4);
        lottoNumbers.add(5);
        assertThatCode(() -> new LottoTicket(LottoFactory.createRandomLottoNumbers()))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> new LottoTicket(LottoFactory.createManualLottoNumbers(lottoNumbers)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
