package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    @Test
    void 로또숫자_생성_갯수() {
        //given
        int lottosSize = 14;
        //when
        List<List<Integer>> lottoNumbersNumbers = LottoNumbersGenerator.generate(lottosSize);
        //then

        for(List<Integer> lottoNumbers : lottoNumbersNumbers) {
            assertThat(lottoNumbers.size()).isEqualTo(LottoNumbersGenerator.LOTTO_NUMBER_SIZE);
        }
    }

    @Test
    void 로또숫자_유효범위() {
        //given
        int lottosSize = 14;
        //when
        List<List<Integer>> lottoNumbersNumbers = LottoNumbersGenerator.generate(lottosSize);
        //then
        for (List<Integer> lottoNumbers : lottoNumbersNumbers) {
            for (Integer lottoNumber : lottoNumbers) {
                assertThat(lottoNumber).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(45);
            }
        }
    }

    @Test
    void 로또티켓_중복_검사() {
        //given
        int lottosSize = 14;
        //when
        List<List<Integer>> lottoNumbersList = LottoNumbersGenerator.generate(lottosSize);
        Set<List<Integer>> lottoNumbersSet = new HashSet<>(lottoNumbersList);
        assertThat(lottoNumbersList.size()).isEqualTo(lottoNumbersSet.size());
    }
}
