package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class LottoFactoryTest {

    @Test
    @DisplayName("로또 번호 생성")
    void createLottoNumbersTest(){
        new LottoFactory();
        List<Integer> lotto = LottoFactory.createLottoNumbers();
        assertThat(lotto.size()).isEqualTo(6);
    }
}
