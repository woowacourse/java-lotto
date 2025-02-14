package lotto.utill;

import static lotto.common.constant.Constant.LOTTO_NUMBER_COUNT;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import lotto.domain.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

    @DisplayName("로또 번호 개수대로 생성되는지 테스트")
    @Test
    void testGeneratedCount() {
        LottoNumberGenerator numberGenerator = new LottoNumberGenerator();
        List<Integer> lottoNumbers = numberGenerator.generate();

        assertThat(lottoNumbers.size()).isEqualTo(LOTTO_NUMBER_COUNT);
    }


    @DisplayName("로또 숫자가 항상 1~45 범위 내에서 생성되는지 테스트 (100번 반복)")
    @RepeatedTest(100) // ✅ JUnit이 알아서 10,000번 반복 실행
    void testGeneratedNumbersRange() {
        LottoNumberGenerator numberGenerator = new LottoNumberGenerator();
        List<Integer> lottoNumbers = numberGenerator.generate();

        boolean inRange = lottoNumbers.stream().allMatch(n -> n >= 1 && n <= 45);

        assertThat(inRange).isTrue();
    }

}