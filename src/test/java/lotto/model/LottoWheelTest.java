package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoWheelTest {
    private List<String> standardNumbers;

    @BeforeEach
    public void initializeStandardNumbers() {
        standardNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            standardNumbers.add(String.valueOf(i));
        }
    }

    @DisplayName("로또 번호가 숫자가 아니면 예외가 발생한다")
    @Test
    void type_exception() {
        assertThatThrownBy(() -> LottoWheel.getNumber("일"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 숫자로만 입력해주세요");
    }

    @ParameterizedTest(name = "{0}에 해당하는 LottoNumber를 반환한다")
    @ValueSource(ints = {1, 15, 40, 23})
    void get_number(int number) {
        assertThat(LottoWheel.getNumber(String.valueOf(number)).getValue()).isEqualTo(number);
    }

    @DisplayName("랜덤 로또를 추출한 후에도 알맞는 LottoNumber를 반환한다")
    @ParameterizedTest(name = "{0}에 해당하는 LottoNumber를 반환한다")
    @ValueSource(ints = {1, 15, 40, 23})
    void get_number_after_shuffle(int number) {
        LottoWheel.draw();

        assertThat(LottoWheel.getNumber(String.valueOf(number)).getValue()).isEqualTo(number);
    }

    @DisplayName("6개의 숫자를 생성한다")
    @Test
    void generate_six_number() {
        Lotto lotto = LottoWheel.draw();

        assertThat(lotto.getValues().size()).isEqualTo(6);
    }

    @DisplayName("6개의 숫자가 오름차순으로 정렬된다")
    @Test
    void sort_ascending() {
        Lotto lotto = LottoWheel.draw();
        List<Integer> numberValues = lotto.getValues();

        for (int index = 0; index < (numberValues.size() - 1); index++) {
            assertThat(numberValues.get(index)).isLessThan(numberValues.get(index + 1));
        }
    }

    @DisplayName("숫자가 7개일 때 예외가 발생한다")
    @Test
    public void size_7_exception() {
        standardNumbers.add("7");
        assertThatThrownBy(() -> LottoWheel.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다");
    }

    @DisplayName("숫자가 5개일 때 예외가 발생한다")
    @Test
    public void size_5_exception() {
        standardNumbers.remove(0);
        assertThatThrownBy(() -> LottoWheel.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다");
    }

    @DisplayName("로또 번호끼리 숫자 5가 중복될 때 예외가 발생한다")
    @Test
    public void duplicate_exception() {
        final String duplicatedNumber = "5";

        standardNumbers.remove(standardNumbers.indexOf(duplicatedNumber) + 1);
        standardNumbers.add(duplicatedNumber);

        assertThatThrownBy(() -> LottoWheel.from(standardNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 중복되면 안됩니다");
    }
}
