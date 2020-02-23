package lotto;

import domain.AllLottoNumbers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AllLottoNumbersTest {

    @Test
    void 생성된_로또번호_길이가_45인지_검증(){
        List<Integer> allLottoNumbers = AllLottoNumbers.getLottoNumbersKeySet();
        assertThat(allLottoNumbers.size()).isEqualTo(45);
    }
}
