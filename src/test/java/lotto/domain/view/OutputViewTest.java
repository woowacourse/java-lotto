package lotto.domain.view;

import lotto.domain.Rank;
import lotto.view.OutputView;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("None을 제외한 Rank를 거꾸로 정렬하여 반환한다.")
    void getReverseOrdered() {
        OutputView.printRanks(List.of(Rank.FIFTH, Rank.NONE, Rank.FIRST));
        assertThat(outputStreamCaptor.toString())
                .isEqualTo("당첨 통계\n" +
                        "---------\n" +
                        "3개 일치 (5000원)- 1개\n" +
                        "4개 일치 (50000원)- 0개\n" +
                        "5개 일치 (1500000원)- 0개\n" +
                        "5개 일치, 보너스 볼 일치(30000000원) - 0개\n" +
                        "6개 일치 (2000000000원)- 1개\n");
    }
}
