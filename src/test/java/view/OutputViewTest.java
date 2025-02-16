package view;



import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Lotto;
import dto.IssuedLottoDto;
import dto.IssuedLottosDto;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
    private static ByteArrayOutputStream outputMessage;

    @DisplayName("로또 정렬 출력 테스트")
    @Test
    public void alignmentTest() {
        // given
        IssuedLottoDto lottoDto1 = new IssuedLottoDto(List.of(5,4,3,2,1,6));
        IssuedLottoDto lottoDto2 = new IssuedLottoDto(List.of(1,3,4,2,5,6));
        IssuedLottosDto lottosDto = new IssuedLottosDto(List.of(lottoDto1, lottoDto2));

        outputMessage = new ByteArrayOutputStream();   // OutputStream 생성
        System.setOut(new PrintStream(outputMessage)); // 생성한 OutputStream 으로 설정
        // when & then
        OutputView.printLottoReceipt(lottosDto);
        assertEquals("2개를 구매했습니다.\r\n"
                + "[1, 2, 3, 4, 5, 6]\r\n"
                + "[1, 2, 3, 4, 5, 6]\r\n", outputMessage.toString());
    }
}