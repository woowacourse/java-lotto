package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoGeneratorTest {

    @Test
    @DisplayName("생성된 로또의 길이가 6인지 확인한다.")
    void generateAutoLotto(){
        Lotto actual = new AutoLottoGenerator().generateLotto(LottoFactory.makeBoundary());
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력된 문자열 리스트의 길이와 변환된 LottoNumber 리스트의 길이가 같은지 확인한다.")
    void generateWinningLotto() {
        List<String> lottoNumbers = Arrays.asList("1","2","3","4","5","6");
        Lotto actual = new WinningLottoGenerator().generateLotto(LottoFactory.from(lottoNumbers));
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

}