package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {
    @Test
    void check_six(){
        LottoNumber lottoNumber = new LottoNumber();
        List<Integer> lottoNumbers = lottoNumber.make();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }
}