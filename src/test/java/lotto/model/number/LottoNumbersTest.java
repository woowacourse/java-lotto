package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {
    private List<String> standardNumbers;

    @BeforeEach
    public void initializeStandardNumbers() {
        standardNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            standardNumbers.add(String.valueOf(i));
        }
    }

    @DisplayName("숫자가 7개일 때 예외가 발생한다")
    @Test
    public void size_7_exception() {
        standardNumbers.add("7");
        assertThatThrownBy(() -> LottoNumbers.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다");
    }

    @DisplayName("숫자가 5개일 때 예외가 발생한다")
    @Test
    public void size_5_exception() {
        standardNumbers.remove(0);
        assertThatThrownBy(() -> LottoNumbers.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다");
    }

    @DisplayName("로또 번호끼리 숫자 5가 중복될 때 예외가 발생한다")
    @Test
    public void duplicate_exception() {
        final String duplicatedNumber = "5";

        standardNumbers.remove(standardNumbers.indexOf(duplicatedNumber) + 1);
        standardNumbers.add(duplicatedNumber);

        assertThatThrownBy(() -> LottoNumbers.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복되면 안됩니다");
    }

    @DisplayName("로또 번호에 포함된 숫자일 때 true를 반환한다")
    @Test
    public void contains_true() {
        LottoNumbers lottoNumbers = LottoNumbers.from(standardNumbers);
        assertThat(lottoNumbers.contains(LottoNumber.from("1"))).isTrue();
    }

    @DisplayName("로또 번호에 포함된 숫자가 아닐 때 false를 반환한다")
    @Test
    public void contains_false() {
        LottoNumbers lottoNumbers = LottoNumbers.from(standardNumbers);
        assertThat(lottoNumbers.contains(LottoNumber.from("7"))).isFalse();
    }

    @DisplayName("숫자 5개가 일치하면 match의 return값은 5이다")
    @Test
    public void match_5() {
        LottoNumbers lottoNumbers = LottoNumbers.from(standardNumbers);
        LottoNumbers comparingNumbers = LottoNumbers.from(List.of("1", "2", "3", "4", "5", "7"));
        assertThat(lottoNumbers.match(comparingNumbers)).isEqualTo(5);
    }

    @DisplayName("숫자 3개가 일치하면 match의 return값은 3이다")
    @Test
    public void match_3() {
        LottoNumbers lottoNumbers = LottoNumbers.from(standardNumbers);
        LottoNumbers comparingNumbers = LottoNumbers.from(List.of("1", "2", "3", "7", "8", "9"));
        assertThat(lottoNumbers.match(comparingNumbers)).isEqualTo(3);
    }
}
