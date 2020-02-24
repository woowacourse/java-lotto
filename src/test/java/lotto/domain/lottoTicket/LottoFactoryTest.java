package lotto.domain.lottoTicket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {

    @Test
    @DisplayName("로또 번호 생성")
    void createLottoNumbersTest(){
        LottoFactory.getInstance();
        List<LottoNumber> lotto = LottoFactory.createLottoNumbers();
        assertThat(lotto.size()).isEqualTo(6);
    }
}
