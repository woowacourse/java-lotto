package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoFactoryTest {

    @Test
    @DisplayName("생성된 로또의 길이가 6인지 확인한다.")
    void generateAutoLotto(){
        List<LottoNumber> actual = LottoFactory.generateAutoLotto();
        int expected = 6;
        assertThat(actual.size()).isEqualTo(expected);
    }
}