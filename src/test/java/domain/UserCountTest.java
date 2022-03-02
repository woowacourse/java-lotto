package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserCountTest {

    @Test
    @DisplayName("금액 기준 구매 가능한 게임 수보다 입력한 수동 게임 수량이 많은 경우 예외 발생")
    public void userCountTest() {
        int maxCount = 14;
        int manualCount = 15;

        assertThrows(IllegalArgumentException.class, () -> new UserCount(maxCount, manualCount));
    }

    @Test
    @DisplayName("수동 구매 수량에 음수를 입력한 경우 경우 예외 발생")
    public void onlyNaturalNumberTest() {
        int maxCount = 14;
        int manualCount = -1;

        assertThrows(IllegalArgumentException.class, () -> new UserCount(maxCount, manualCount));
    }
}
