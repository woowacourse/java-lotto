package domain;

import controller.LottoController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LottoTicketsTest {
    @DisplayName("LottoController 를 이용해 LottoTickets 가 제대로 생성되는지 테스트")
    @Test
    void concatManualLottoTicketsWithAutoLottoTicketsTest() {
        String numbers = "1, 2, 3, 4, 5, 6\n2, 3, 4, 5, 6, 7\n3, 4, 5, 6, 7, 8";
        InputStream in = new ByteArrayInputStream(numbers.getBytes());
        System.setIn(in);

        ManualLottoTickets manualLottoTickets = new ManualLottoTickets(
                ManualLottoTicketsGenerator.generateManualLottoTickets(
                        new ArrayList(Arrays.asList(
                                "1, 2, 3, 4, 5, 6",
                                "2, 3, 4, 5, 6, 7",
                                "3, 4, 5, 6, 7, 8"
                        ))
                ));

        LottoTickets lottoTickets = LottoController.getLottoTickets(3, 2);
        Assertions.assertThat(lottoTickets.getTickets().size()).isEqualTo(5);
        Assertions.assertThat(lottoTickets.getTickets()).containsSequence(manualLottoTickets.getTickets());
    }
}
