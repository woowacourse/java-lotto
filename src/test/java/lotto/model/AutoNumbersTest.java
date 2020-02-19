package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoNumbersTest {
    @Test
    @DisplayName("AutoNumbers 생성자 테스트")
    void AutoNumbers() {
        AutoNumbers an = new AutoNumbers();
        for(int i : an.getAutoNumbers()) {
            System.out.println(i);
        }
    }
}
