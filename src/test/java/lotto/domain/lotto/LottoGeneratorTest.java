package lotto.domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.number.PayOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoGeneratorTest {

    @Test
    @DisplayName("로또 그룹 생성")
    void generateAutoLottoGroup() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        assertThat(lottoGenerator.generateLottos(3).getCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 로또번호를 입력하면, 수동번호의 로또그를을 생성한다")
    void generateManualLottos() {
        List<String> manualStringLottoNumbers = Arrays.asList(
                "1,2,3,4,5,6",
                "1,3,4,7,34,6",
                "5,3,8,6,4,9"
        );

        PayOut zeroPayOut = PayOut.ZERO;

        LottoGenerator lottoGenerator = new LottoGenerator();
        assertThat(
                lottoGenerator.generateLottosWithManualLottoNumbers(
                        manualStringLottoNumbers,
                        zeroPayOut
                ).getCount()
        ).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 로또번호를 입력하면, 수동번호의 로또그를과 남은 금액만큼 자동 로또 번호를 생성한다")
    void generateManualLottosWithAutoLottos() {
        List<String> manualStringLottoNumbers = Arrays.asList(
                "1,2,3,4,5,6",
                "1,3,4,7,34,6",
                "5,3,8,6,4,9"
        );


        PayOut payOut = new PayOut(3000);

        LottoGenerator lottoGenerator = new LottoGenerator();
        assertThat(
                lottoGenerator.generateLottosWithManualLottoNumbers(
                        manualStringLottoNumbers,
                        payOut
                ).getCount()
        ).isEqualTo(6);
    }
}