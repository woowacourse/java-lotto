package lotto.domain.lottoTicket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberFactoryTest {

    @Test
    @DisplayName("로또 번호 생성")
    void createLottoNumbersTest(){
        new LottoNumberFactory();
        List<LottoNumber> lotto = LottoNumberFactory.createLottoNumbers();
        assertThat(lotto.size()).isEqualTo(6);
    }
}
