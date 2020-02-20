package lotto.domain.number;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AllLottoNumbersTest {

    @Test
    void 생성자에_Null_입력() {
        assertThatThrownBy(() -> {
            AllLottoNumbers allLottoNumbers = new AllLottoNumbers(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void 생성자에_empty_list_입력() {
        assertThatThrownBy(() -> {
            AllLottoNumbers allLottoNumbers = new AllLottoNumbers(Collections.emptyList());
        }).isInstanceOf(IllegalArgumentException.class);
    }
}