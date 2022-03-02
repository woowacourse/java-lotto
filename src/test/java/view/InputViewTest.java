package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class InputViewTest {

    @Test
    void 로또_구입_금액_출력() {
        //given
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String input = "14000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        InputView.inputMoney();

        //then
        assertThat(out.toString()).isEqualTo("구입금액을 입력해 주세요." + System.lineSeparator());
    }

    @Test
    void 로또_구입_금액_결과() {
        //given
        String input = "14000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        int money = InputView.inputMoney();

        //the
        assertThat(money).isEqualTo(14000);
    }

    @Test
    void 로또_수동_구매_개수_입력_정상작동() {
        //given
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        int manualTicketCount = InputView.inputManualTicketCount();

        //then
        assertThat(manualTicketCount).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a"," "})
    void 로또_수동_구매_개수_숫자아닐시_예외처리(String input) {
        //given
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThatThrownBy(InputView::inputManualTicketCount)
                .isInstanceOf(Exception.class);
    }

    @Test
    void 로또_구입_금액_숫자_아닌_경우() {
        //given
        String input = "---!!";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(InputView::inputMoney).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3", "1, 2, 3, 4, 5, 6, ", ", 1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6, 7"})
    void 수동_번호_패턴에_맞지_않는_경우_예외처리(String input) {
        //given
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThatThrownBy(() -> InputView.inputManualTicketGroup(3))
                .isInstanceOf(Exception.class);
    }

    @Test
    void 당첨_번호_입력_정상작동() {
        //given
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String input = "1, 2, 3, 4, 5, 6";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        InputView.inputWinLottoNums();

        //then
        assertThat(out.toString()).isEqualTo("\n지난 주 당첨 번호를 입력해 주세요." + System.lineSeparator());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3", "1, 2, 3, 4, 5, 6, ", ", 1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6, 7"})
    void 당첨_번호_패턴에_맞지_않는_경우_예외처리(String input) {
        //given
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThatThrownBy(InputView::inputWinLottoNums)
                .isInstanceOf(Exception.class);
    }

    @Test
    void 보너스볼_입력() {
        //given
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String input = "11";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        InputView.inputBonusNumber();

        //then
        assertThat(out.toString()).isEqualTo("보너스 볼을 입력해 주세요." + System.lineSeparator());
    }


    @Test
    void 보너스볼_숫자_아닌_경우() {
        //given
        String input = "---!!";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(InputView::inputBonusNumber).isInstanceOf(Exception.class);
    }
}