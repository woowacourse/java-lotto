package lotto.domain.lotto;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    @Test
    void 로또_결과_초기화_테스트() {
        LottoResult lottoResult = new LottoResult();

        System.out.println(lottoResult.getResult().toString());
    }

    @Test
    void 로또_일치_갯수_테스트() {
        Lotto testLotto = Lotto.generatedBy(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto testLotto2 = Lotto.generatedBy(Arrays.asList(6, 7, 3, 9, 10, 11));

        int count = (int) testLotto.getNumbers().stream()
            .filter(number -> testLotto2.getNumbers().contains(number)).count();

        assertThat(count).isEqualTo(2);
    }
}
