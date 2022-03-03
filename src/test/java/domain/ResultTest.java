package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import service.LottoService;

class ResultTest {

    @Test
    void result_message_valid_test() {
        final List<Lotto> lottos = new LottoService().issueManualLottoGroup(
            List.of(
                Arrays.asList("1", "2", "3", "4", "5", "6"),
                Arrays.asList("1", "2", "3", "4", "5", "7")
            )
        );

        final WinLotto winLotto = new WinLotto(Lotto.fromInput(Arrays.asList("1", "2", "3", "4", "5", "6")),
            new LottoNumber(7));

        final Result result = new Result(lottos, winLotto);

        assertThat(result.getStatistics()).contains(
            "5개 일치, 보너스 볼 일치(30000000원)- 1개"
                + System.lineSeparator()
                + "6개 일치 (2000000000원)- 1개"
        );
    }
}
