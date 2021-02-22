package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    public static final String NUMBER_SIZE_ERROR = "[ERROR] 총 6개의 숫자를 입력해야 합니다.";
    public static final String DUPLICATE_ERROR = "[ERROR] 중복되는 숫자를 입력할 수 없습니다.";

    @Test
    @DisplayName("포함된 숫자가 6개인지 검증")
    void lottoNumbers_size() {
        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3)
            ));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NUMBER_SIZE_ERROR);
    }

    @Test
    @DisplayName("중복이 있을 때 에러 발생")
    void lottoNumbers_duplicate() {
        assertThatThrownBy(() -> {
            new Lotto(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
            ));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(DUPLICATE_ERROR);
    }
}
