package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoGeneratorTest {

    @Test
    @DisplayName("입력된 수동 번호를 로또 번호로 생성")
    void createManualLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(1);
        lottoNumbers.add(2);
        lottoNumbers.add(3);
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(lottoNumbers);
        assertThat(manualLottoGenerator.generateNumbers().size()).isEqualTo(lottoNumbers.size());
    }
}
