package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoNumberTest {
    @Test
    @DisplayName("AutoNumber 생성자 테스트")
    void AutoNumbers() {
        AutoNumber an = new AutoNumber();
        for(int i : an.getAutoNumber()) {
            System.out.println(i);
        }
    }
}
