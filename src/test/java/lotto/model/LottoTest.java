package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import lotto.model.number.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    private List<String> standardNumbers;

    @BeforeEach
    public void initializeStandardNumbers() {
        standardNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            standardNumbers.add(String.valueOf(i));
        }
    }

    @DisplayName("6개의 숫자를 생성한다")
    @Test
    void generate_six_number() {
        Lotto lotto = new Lotto();

        assertThat(lotto.getValues().size()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자가 오름차순으로 정렬된다")
    @Test
    void sort_ascending() {
        Lotto lotto = new Lotto();
        List<Integer> numberValues = lotto.getValues();

        for (int index = 0; index < (numberValues.size() - 1); index++) {
            assertThat(numberValues.get(index)).isLessThan(numberValues.get(index + 1));
        }
    }

    @DisplayName("숫자가 7개일 때 예외가 발생한다")
    @Test
    public void size_7_exception() {
        standardNumbers.add("7");
        assertThatThrownBy(() -> Lotto.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다");
    }

    @DisplayName("숫자가 5개일 때 예외가 발생한다")
    @Test
    public void size_5_exception() {
        standardNumbers.remove(0);
        assertThatThrownBy(() -> Lotto.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다");
    }

    @DisplayName("로또 번호끼리 숫자 5가 중복될 때 예외가 발생한다")
    @Test
    public void duplicate_exception() {
        final String duplicatedNumber = "5";

        standardNumbers.remove(standardNumbers.indexOf(duplicatedNumber) + 1);
        standardNumbers.add(duplicatedNumber);

        assertThatThrownBy(() -> Lotto.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복되면 안됩니다");
    }

    @DisplayName("로또 번호에 포함된 숫자일 때 true를 반환한다")
    @Test
    public void contains_true() {
        Lotto lotto = Lotto.from(standardNumbers);
        assertThat(lotto.contains(LottoNumber.from("1"))).isTrue();
    }

    @DisplayName("로또 번호에 포함된 숫자가 아닐 때 false를 반환한다")
    @Test
    public void contains_false() {
        Lotto lotto = Lotto.from(standardNumbers);
        assertThat(lotto.contains(LottoNumber.from("7"))).isFalse();
    }

    @DisplayName("숫자 5개가 일치하면 match의 return값은 5이다")
    @Test
    public void match_5() {
        Lotto lotto = Lotto.from(standardNumbers);
        Lotto comparingLotto = Lotto.from(List.of("1", "2", "3", "4", "5", "7"));
        assertThat(lotto.match(comparingLotto)).isEqualTo(5);
    }

    @DisplayName("숫자 3개가 일치하면 match의 return값은 3이다")
    @Test
    public void match_3() {
        Lotto lotto = Lotto.from(standardNumbers);
        Lotto comparingLotto = Lotto.from(List.of("1", "2", "3", "7", "8", "9"));
        assertThat(lotto.match(comparingLotto)).isEqualTo(3);
    }
}
