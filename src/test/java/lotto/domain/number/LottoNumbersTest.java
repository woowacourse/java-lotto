package lotto.domain.number;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumbersTest {
    List<Integer> rowLottoNumbers;
    LottoNumbers lottoNumbers;

    @BeforeEach
    void setUp(){
        rowLottoNumbers = Arrays.asList(6,5,4,3,2,1);
        lottoNumbers = new LottoNumbers(rowLottoNumbers);
    }

    @DisplayName("로또번호 일급컬렉션 생성테스트")
    @Test
    void createLottoNumbersTest() {
        List<LottoNumber> lottoNumberList = rowLottoNumbers.stream()
                .map(LottoNumberFactory::of)
                .collect(Collectors.toList());

        assertThat(lottoNumbers.list()).containsAll(lottoNumberList);
    }

    @DisplayName("정렬 테스트")
    @Test
    void sort() {
        lottoNumbers.sort();
        assertThat(lottoNumbers.list()).isSorted();
    }
}