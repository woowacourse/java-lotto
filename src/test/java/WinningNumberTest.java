import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningNumberTest {

    @Test
    @DisplayName("당첨 번호가 6자리 숫자인지 테스트")
    public void checkWinningNumberIs6Test(){
        WinningNumber winningNumber = new WinningNumber(List.of(1,2,3,4,5,6));
        assertThat(winningNumber.size()).isEqualTo(6);
    }
}
