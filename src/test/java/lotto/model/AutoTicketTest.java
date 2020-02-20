package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoTicketTest {
    @Test
    @DisplayName("AutoNumber 생성자 테스트")
    void AutoNumbers() {
        AutoTicket an = new AutoTicket();
        for(int i : an.getAutoTicket()) {
            System.out.println(i);
        }
    }
}
