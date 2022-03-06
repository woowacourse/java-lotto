package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class InputViewTest {

    @ParameterizedTest
    @ValueSource(strings = {"--!", " ", "."})
    void 로또_구입_금액_숫자_아닌_경우(String input) {
        //given
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(InputView::inputMoney).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"999", "100", "20"})
    void 로또_구입_금액_1000_미만_경우(String input) {
        //given
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(InputView::inputMoney).isInstanceOf(Exception.class);
    }


    @ParameterizedTest
    @ValueSource(strings = {"1001", "2050", "3100"})
    void 로또_구입_금액_1000_으로_나누어떨어지지_않는_경우(String input) {
        //given
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(InputView::inputMoney).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "10"})
    void 구매_금액_이상의_로또_구매_에러(String input) {
        //given
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(()->InputView.inputLottoAmount(1000)).isInstanceOf(Exception.class);    }



    @ParameterizedTest
    @ValueSource(strings = {"--!", " ", "."})
    void 수동_로또_개수_숫자_아닌_경우(String input) {
        //given
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(() -> InputView.inputLottoAmount(1000)).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3", "1, 2, 3, 4, 5, 6, ", ", 1, 2, 3, 4, 5, 6",
        "1, 2, 3, 4, 5, 6, 7"})
    void 당첨_번호_패턴에_맞지_않는_경우_예외처리(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThatThrownBy(InputView::inputWinLottoNumbers).isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"--!", " ", "."})
    void 보너스볼_숫자_아닌_경우(String input) {
        //given
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertThatThrownBy(InputView::inputBonusNumber).isInstanceOf(Exception.class);
    }


}