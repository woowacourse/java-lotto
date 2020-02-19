package domain.numberscontainer;

import domain.LottoNumber;
import domain.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("당첨 번호 객체 테스트")
public class WinningNumbersTest {
    @Test
    @DisplayName("당첨 번호 객체 생성")
    void winningNumberConstructor() {
        LottoNumbersDto lottoNumbersDto = createLottoNumberDto(1, 2, 3, 4, 5, 5, 7);
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1})
    @DisplayName("유효한 보너스 번호인지 검증")
    void validateBonusNumber(int input) {
        LottoNumbersDto lottoNumbersDto = createLottoNumberDto(1, 2, 3, 4, 5, 6, input);
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageEndingWith("보너스 번호를 입력해주세요.");
    }

    @Test
    @DisplayName("Ticket과 당첨 번호 비교")
    void findDuplicatedNumbers() {
        LottoNumbersDto lottoNumbersDto1 = createLottoNumberDto(1, 2, 3, 4, 5, 6, 7);
        LottoNumbersDto lottoNumbersDto2 = createLottoNumberDto(4, 5, 6, 7, 8, 9, 10);
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbersDto1);
        Ticket ticket = new Ticket(lottoNumbersDto2);
        assertThat((winningNumbers.findDuplicatedNumbers(ticket))).isEqualTo(3);
    }

    private LottoNumbersDto createLottoNumberDto(int number1, int number2, int number3, int number4, int number5, int number6, int number7) {
        Set<LottoNumber> sixNumbers = new HashSet<>(Arrays.asList(LottoNumber.getLottoNumber(number1),
                LottoNumber.getLottoNumber(number2),
                LottoNumber.getLottoNumber(number3),
                LottoNumber.getLottoNumber(number4),
                LottoNumber.getLottoNumber(number5),
                LottoNumber.getLottoNumber(number6)));
        LottoNumber bonusNumber = LottoNumber.getLottoNumber(number7);

        return new LottoNumbersDto(sixNumbers, bonusNumber);
    }

    @Test
    @DisplayName("당첨 결과 확인")
    void getLottoResultTest() {
        List<Ticket> tickets = Arrays.asList(
                new Ticket(createLottoNumberDto(3, 4, 5, 6, 7, 8, -1)), // 1등
                new Ticket(createLottoNumberDto(3, 4, 5, 6, 7, 9, -1)), // 2등
                new Ticket(createLottoNumberDto(3, 4, 5, 6, 7, 10, -1)), // 3등
                new Ticket(createLottoNumberDto(3, 4, 5, 6, 10, 11, -1)), // 4등
                new Ticket(createLottoNumberDto(3, 4, 5, 10, 11, 12, -1)), // 5등
                new Ticket(createLottoNumberDto(4, 5, 6, 7, 8, 9, -1)), // 2등
                new Ticket(createLottoNumberDto(4, 5, 6, 7, 8, 10, -1)), // 3등
                new Ticket(createLottoNumberDto(4, 5, 6, 7, 10, 11, -1)), // 4등
                new Ticket(createLottoNumberDto(10, 11, 12, 13, 14, 15, -1))); // 당첨X

        WinningNumbers winningNumbers = new WinningNumbers(createLottoNumberDto(3, 4, 5, 6, 7, 8, 9));

        Map<LottoResult, Long> map = tickets.stream()
                .collect(Collectors.groupingBy(ticket -> winningNumbers.getLottoResult(ticket), Collectors.counting()));

        assertThat(map.get(LottoResult.FIRST)).isEqualTo(1);
        assertThat(map.get(LottoResult.SECOND)).isEqualTo(2);
        assertThat(map.get(LottoResult.THIRD)).isEqualTo(2);
        assertThat(map.get(LottoResult.FOURTH)).isEqualTo(2);
        assertThat(map.get(LottoResult.FIFTH)).isEqualTo(1);
        assertThat(map.get(LottoResult.NO_WIN)).isEqualTo(1);
    }
}