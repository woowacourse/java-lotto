package view;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class InputParserTest {

    private InputParser inputParser = new InputParser();

    @Test
    void 입력한_당첨번호들을_정수_리스트로_반환한다() {
        //given
        String rawWinningNumbers = "1, 2, 3, 4, 5, 6";
        //when
        List<Integer> parsedWinningNumbers = inputParser.parseWinningNumbers(rawWinningNumbers);
        //then
        assertThat(parsedWinningNumbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 입력한_보너스_번호를_정수로_반환한다() {
        //given
        String rawBonusNumber = "1";
        //when
        int parsedBonusNumber = inputParser.parseBonusNumber(rawBonusNumber);
        //then
        assertThat(parsedBonusNumber).isEqualTo(1);
    }
}
