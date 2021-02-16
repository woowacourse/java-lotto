package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {

    @Test
    @DisplayName("잘못된 개수의 로또 번호")
    void generateIllegalNumberCountLotto() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("잘못된 개수");
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("잘못된 개수");
    }

    @Test
    @DisplayName("범위 초과 로또 번호")
    void generateOutRangeLotto() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5, 46)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("범위가 초과");
    }

    @Test
    @DisplayName("중복 로또 번호")
    void generateDuplicateLotto() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 1, 3, 4, 5, 45)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("중복");
    }

    @ParameterizedTest
    @DisplayName("일치하는 로또 번호 개수 확인")
    @CsvSource(value = {"1,2,3,4,5,6:6", "1,2,3,4,5,7:5", "1,2,3,4,7,8:4", "1,2,3,7,8,9:3",
        "1,2,7,8,9,10:2", "1,7,8,9,10,11:1", "7,8,9,10,11,12:0"}, delimiter = ':')
    void compareLottoNumber(String input, int expected) {
        Lotto init = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Integer[] arr = Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

        Lotto lotto = new Lotto(Arrays.asList(arr));
        int actual = init.countCommonValue(lotto);
        assertThat(actual).isEqualTo(expected);
    }

}
