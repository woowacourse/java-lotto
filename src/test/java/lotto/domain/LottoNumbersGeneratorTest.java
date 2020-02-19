package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    @Test
    void generate() {
        //when
        List<Integer> lottoNumbers = LottoNumbersGenerator.generate();
        //then
        assertThat(lottoNumbers.size()).isEqualTo(LottoNumberConfig.SIZE);
    }

    @Test
    void 로또숫자들_생성_중복_불가() {
        //when
        List<Integer> lottoNumbers = LottoNumbersGenerator.generate();
        //then
        int expectedSize = new HashSet<>(lottoNumbers).size();
        assertThat(expectedSize).isEqualTo(LottoNumberConfig.SIZE);
    }
}
