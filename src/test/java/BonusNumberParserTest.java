import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusNumberParserTest {

    @Test
    @DisplayName("1 ~ 45 인 보너스 숫자 파싱하기 테스트")
    void parseValidBonusNumber() {
        BonusNumberParser parser = new BonusNumberParser();
        int actual =parser.parse("1");
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("1 ~ 45 사이에 없는 보너스 숫자 예외 테스트")
    void parseInvalidBonusNumber() {
        BonusNumberParser parser = new BonusNumberParser();
        assertThatThrownBy(() -> parser.parse("46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 1 ~ 45사이의 숫자만 가능합니다.");
    }
}
