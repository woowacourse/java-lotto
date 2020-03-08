package lotto.domain;

import lotto.util.Splitter;
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
        RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(lottoNumbers);
        assertThatCode(() -> new LottoTicket(randomLottoGenerator.generateNumbers()))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> new LottoTicket(manualLottoGenerator.generateNumbers()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두 로또를 비교해 맞은 개수 반환 테스트")
    void countSameNumbers() {
        ManualLottoGenerator manualLottoGenerator1 = new ManualLottoGenerator(Splitter.splitInput("1,2,3,4,5,6"));
        ManualLottoGenerator manualLottoGenerator2 = new ManualLottoGenerator(Splitter.splitInput("1,2,3,4,10,8"));
        LottoTicket lotto = new LottoTicket(manualLottoGenerator1.generateNumbers());
        LottoTicket lottoToCompare = new LottoTicket(manualLottoGenerator2.generateNumbers());
        assertThat(lotto.countSameNumbers(lottoToCompare)).isEqualTo(4);
    }

}
