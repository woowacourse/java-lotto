package view;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.Lotto;
import domain.lotto.LottoFactory;
import domain.lotto.LottoNumber;
import domain.lotto.LottoTicketCount;
import domain.lotto.WinNumbers;
import domain.result.Result;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.NumsGenerator;

@SuppressWarnings("NonAsciiCharacters")
class OutputViewTest {
    private WinNumbers winNumbers;

    @BeforeEach
    void setup() {
        winNumbers = LottoFactory.createWinNums(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoNumber.getInstance(10));
    }

    @Test
    void 로또_티켓_검사() {
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        List<Lotto> lottoTickets = new ArrayList<>();
        Lotto lotto1 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoTickets.add(lotto1);
        Lotto lotto2 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(7, 8, 9, 10, 11, 12)));
        lottoTickets.add(lotto2);

        LottoTicketCount count = LottoTicketCount.of(2, 1);

        OutputView.printLottoTickets(count, lottoTickets);

        assertThat(out.toString()).isEqualTo("\n수동으로 1장, 자동으로 1개를 구매했습니다."
                + System.lineSeparator()
                + "[1, 2, 3, 4, 5, 6]"
                + System.lineSeparator()
                + "[7, 8, 9, 10, 11, 12]"
                + System.lineSeparator()
        );
    }

    @Test
    void 로또_당첨결과_출력_검사() {
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        //given
        Lotto lotto1 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Lotto lotto3 = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 9)));

        //when
        Result result = Result.of(Arrays.asList(lotto1, lotto3), winNumbers);

        OutputView.printLottosResult(result);

        assertThat(out.toString()).isEqualTo("\n당첨 통계\n"
                + "---------\n"
                + "3개 일치 (5000원)- 0개\n"
                + "4개 일치 (50000원)- 0개\n"
                + "5개 일치 (1500000원)- 1개\n"
                + "5개 일치, 보너스 볼 일치(30000000원)- 0개\n"
                + "6개 일치 (2000000000원)- 1개\n");
    }

    @Test
    void 로또_수익률_결과_출력_손해_검사() {
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        OutputView.printProfit((float) 0.765);

        assertThat(out.toString()).isEqualTo("총 수익률은 0.76입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n");
    }

    @Test
    void 로또_수익률_결과_출력_이득_검사() {
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        OutputView.printProfit((float) 1.123);

        assertThat(out.toString()).isEqualTo("총 수익률은 1.12입니다.(기준이 1이기 때문에 결과적으로 손해 아니라는 의미임)\n");
    }

    @Test
    void 로또_수익률_결과_출력_이득_1_검사() {
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        OutputView.printProfit((float) 1);

        assertThat(out.toString()).isEqualTo("총 수익률은 1.00입니다.(기준이 1이기 때문에 결과적으로 손해 아니라는 의미임)\n");
    }
}