package lotto.domain.generator;

import lotto.domain.LottoNo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNosManualGeneratorTest {
    @Test
    void 번호_생성_확인() {
        List<Integer> nums = Arrays.asList(8, 21, 23, 41, 42, 43);

        List<LottoNo> actual = new ArrayList<>();
        nums.forEach(integer -> actual.add(LottoNo.from(integer)));

        String input = "8, 21, 23, 41, 42, 43";
        List<LottoNo> expected = new LottoNosManualGenerator(input).generate();

        assertThat(actual).isEqualTo(expected);
    }
}