package lotto.domain.ticketFactory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FixedNumbersGeneratorTest {

    @DisplayName("로또 번호를 생성했을 때, 서로 다른 6개 번호로 구성된 티켓이 반환되어야한다.")
    @Test
    void makeTicket() {
        List<Integer> lottoNumbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        FixedNumbersGenerator fixedNumbersGenerator = new FixedNumbersGenerator(lottoNumbers);
        LottoTicket lottoTicket = new LottoTicket(fixedNumbersGenerator.generateNumbers());

        Set<Integer> actualLottoNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lottoTicket.getLottoNumbers()).isEqualTo(actualLottoNumbers);
    }
}
