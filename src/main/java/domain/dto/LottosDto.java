package domain.dto;

import domain.Lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottosDto {

    private static final List<List<Integer>> viewLottos = new ArrayList<>();

    private LottosDto(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            viewLottos.add(lotto.getNumbers());
        }
    }

    public static LottosDto from(List<Lotto> lottos) {
        return new LottosDto(lottos);
    }

    public List<List<Integer>> getLottos() {
        return Collections.unmodifiableList(viewLottos);
    }
}
