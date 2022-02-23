package lotto.view;

import lotto.model.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



public class ResultViewTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("생성된 로또 출력 확인")
    void printGeneratedLottosTest() {
        Lotto l1 = new Lotto(Arrays.asList(1,2,3,4,5,7));
        Lotto l2 = new Lotto(Arrays.asList(1,2,33,41,5,7));
        List<Lotto> lottos = Arrays.asList(l1, l2);

        ResultView.printGeneratedLottos(lottos);

        assertThat(outputStreamCaptor.toString()).contains("2개를 ");
        assertThat(outputStreamCaptor.toString()).contains("[1, 2, 5, 7, 33, 41]");
    }
}
