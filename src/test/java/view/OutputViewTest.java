package view;

import static org.assertj.core.api.Assertions.assertThat;

import domain.LottoNumbers;
import domain.Rank;
import domain.Result;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class OutputViewTest {

    @Test
    void 로또_티켓_검사() {
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        List<LottoNumbers> lottoTickets = new ArrayList<>();
        lottoTickets.add(new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoTickets.add(new LottoNumbers(Arrays.asList(7, 8, 9, 10, 11, 12)));
        OutputView.printLottoTickets(lottoTickets);

        assertThat(out.toString()).isEqualTo("2개를 구매했습니다."
                + System.lineSeparator()
                + "[1, 2, 3, 4, 5, 6]"
                + System.lineSeparator()
                + "[7, 8, 9, 10, 11, 12]"
                + System.lineSeparator()
        );
    }

    @Test
    void 로또_당첨결과_출력_검사(){
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Result result = new Result();
        result.add(Rank.FIRST);
        result.add(Rank.THIRD);

        OutputView.printResult(result);

        assertThat(out.toString()).isEqualTo("당첨 통계\n"
                + "---------\n"
                + "3개 일치 (5000원)- 0개\n"
                + "4개 일치 (50000원)- 0개\n"
                + "5개 일치 (1500000원)- 1개\n"
                + "5개 일치, 보너스 볼 일치(30000000원)- 0개\n"
                + "6개 일치 (2000000000원)- 1개\n");
    }
}