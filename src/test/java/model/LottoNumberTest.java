package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("생성된 로또 번호가 46 이상일시 에러를 발생한다.")
    void checkLottoNumberOverThan46() {
        assertThatThrownBy(
                () -> new LottoNumber(46)
        ).isInstanceOf(RuntimeException.class);
    }
}
