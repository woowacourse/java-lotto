package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LottoFactoryTest {

    @Test
    @DisplayName("6자리 숫자가 생성되는지 확인")
    void createRandomLottoNumbers() {
        LottoFactory.getInstance();
        assertThat(LottoFactory.createRandomLottoNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("입력된 수동 번호를 로또 번호로 생성")
    void createManualLottoNumbers(){
        List<Integer> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(1);
        lottoNumbers.add(2);
        lottoNumbers.add(3);
        assertThat(LottoFactory.createManualLottoNumbers(lottoNumbers).size()).isEqualTo(lottoNumbers.size());
    }
}
