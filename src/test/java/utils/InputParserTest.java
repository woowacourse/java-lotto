package utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputParserTest {

    @DisplayName("입력값을 쉼표 기준으로 나누어 당첨 번호를 생성한다.")
    @Test
    void 입력값_파싱_후_당첨번호_생성(){
        String inputWinningNumbers="1,2,3,4,5,6";
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> parsedNumbers = InputParser.parseAndCreateWinningNumbers(inputWinningNumbers);

        assertThat(parsedNumbers).isEqualTo(expectedNumbers);
    }

}
