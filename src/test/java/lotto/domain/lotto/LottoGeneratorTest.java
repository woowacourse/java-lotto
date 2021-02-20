package lotto.domain.lotto;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.PayOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoGeneratorTest {

    @Test
    @DisplayName("로또 그룹 생성")
    void generateLottoGroup() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        assertThat(lottoGenerator.generateLottos(3).getCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 로또번호를 입력하면, 수동번호의 로또그를을 생성한다")
    void generateLottos() {
        List<String> manualStringLottoNumbers = Arrays.asList(
                "1,2,3,4,5,6",
                "1,3,4,7,34,6",
                "5,3,8,6,4,9"
        );

        List<LottoNumbers> manualLottoNumbers = manualStringLottoNumbers.stream()
                .map(LottoNumbers::valueOf)
                .collect(toList());;

        LottoGenerator lottoGenerator = new LottoGenerator();
        assertThat(lottoGenerator.generateLottosWithManualLottoNumbers(manualLottoNumbers).getCount()).isEqualTo(3);
    }
}