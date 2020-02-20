package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoNumbersTest {
    @DisplayName("셔플 기능 테스트")
    @Test
    public void shuffleLottoNumbersTest() {
        List<Integer> lottoNumber = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> expectedLottoNumber = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Collections.shuffle(lottoNumber);
        Assertions.assertThat(lottoNumber).isNotEqualTo(expectedLottoNumber);
    }

    @DisplayName("로또 번호 생성 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "10,10", "15,15"}, delimiter = ',')
    public void generateLottoTicketsTest(String input, String expected) {
        List<LottoTicket> lottoTickets = LottoNumbers.generateLottoTickets(Integer.parseInt(input));
        Assertions.assertThat(lottoTickets.size()).isEqualTo(Integer.parseInt(expected));
    }
}
