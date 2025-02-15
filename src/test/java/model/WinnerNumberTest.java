package model;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinnerNumberTest {

    @Test
    @DisplayName("각 로또들을 당첩 번호와 비교")
    void compareLottoToWinning() {

        List<Lotto> lotto = List.of(
                new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))),
                new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 7))),
                new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 8))),
                new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 10, 11))),
                new Lotto(new ArrayList<>(List.of(1, 2, 3, 10, 11, 12))),
                new Lotto(new ArrayList<>(List.of(1, 2, 10, 11, 12, 13)))
        );

        Lottos lottos = new Lottos(lotto);

        List<Integer> winnerNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 7;

        WinnerNumber winnerNumber = new WinnerNumber(winnerNumbers, bonusBall);
        winnerNumber.compareLottoToWinning(lottos);

        for (LottoResult result : LottoResult.values()) {
            Assertions.assertThat(result.getCount()).isEqualTo(1);
        }
    }
}