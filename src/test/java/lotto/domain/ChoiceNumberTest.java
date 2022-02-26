package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ChoiceNumberTest {
    @Test
    @DisplayName("숫자 6개를 뽑았는가")
    void Generate_6_Number() {
        ChoiceNumber choiceNumber = new ChoiceNumber();
        assertThat(choiceNumber.getChoiceNumbers().stream().count()).isEqualTo(6);
    }

    @Test
    @DisplayName("모든 숫자가 1~45 사이에 위치하는가")
    void Is_Between_1_To_45() {
        ChoiceNumber choiceNumber = new ChoiceNumber();
        assertThat(choiceNumber.getChoiceNumbers().stream().allMatch(i -> i >= 1 && i <= 45)).isTrue();
    }

    @Test
    @DisplayName("입력받은 당첨번호가 숫자가 아니면 예외처리")
    void Is_Number_Format() {
        assertThatThrownBy(() -> {
            ChoiceNumber choiceNumber = new ChoiceNumber("1,2,3,a,b,c");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력받은 당첨번호가 6개인지 검증")
    void Get_6_Input() {
        ChoiceNumber choiceNumber = new ChoiceNumber("1,2,3,4,5,6");
        assertThat(choiceNumber.getChoiceNumbers().stream().count()).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    @DisplayName("입력받은 당첨번호가 6개가 아니라면 예외처리하는가?")
    void No_6_Input_Numbers(String input) {
        assertThatThrownBy(() -> {
            new ChoiceNumber(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력받은 당첨번호가 모두 범위내에 있는지")
    void All_Numbers_In_Range() {
        assertThatThrownBy(() -> {
            new ChoiceNumber("0,2,3,4,5,6");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력받은 당첨번호가 중복되는 값이 있는지")
    void Duplicate_Number_Exist() {
        assertThatThrownBy(() -> {
            new ChoiceNumber("1,1,3,4,5,6");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("겹치는 숫자가 몇개인지 잘 찾아낼 수 있는지")
    @CsvSource(value = {"1,2,3,4,5,6:6", "1,2,3,4,5,7:5", "1,2,3,4,7,8:4",
            "1,2,3,7,8,9:3", "1,2,7,8,9,10:2", "1,7,8,9,10,11:1", "7,8,9,10,11,12:0"}, delimiter = ':')
    void Count_Same_Number(String input, int correctNumber) {
        ChoiceNumber choiceNumber = new ChoiceNumber("1,2,3,4,5,6");
        ChoiceNumber comparisonNumber = new ChoiceNumber(input);
        assertThat(choiceNumber.countSameNumber(comparisonNumber)).isEqualTo(correctNumber);
    }
}
