package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    @Test
    void 로또숫자_생성_갯수() {
        //when
        List<Integer> lottoNumbers = LottoNumbersGenerator.generate();
        //then
        assertThat(lottoNumbers.size()).isEqualTo(LottoNumbersGenerator.LOTTO_NUMBER_SIZE);
    }

    @Test
    void 로또숫자_유효범위() {
        //when
        List<Integer> lottoNumbers = LottoNumbersGenerator.generate();
        //then
        for (Integer lottoNumber : lottoNumbers) {
            assertThat(lottoNumber).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(45);
        }
    }

    @Test
    void 로또숫자들_생성_중복_불가() {
        //when
        List<Integer> lottoNumbers = LottoNumbersGenerator.generate();
        //then
        int expectedSize = new HashSet<>(lottoNumbers).size();
        assertThat(expectedSize).isEqualTo(LottoNumbersGenerator.LOTTO_NUMBER_SIZE);
    }
}
