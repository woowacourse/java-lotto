package domain.dto;

import domain.Lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottosViewDto {

    private static final List<List<Integer>> viewLottos = new ArrayList<>();

    private LottosViewDto(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            viewLottos.add(lotto.getNumbers());
        }
    }

    public static LottosViewDto from(List<Lotto> lottos) {
        return new LottosViewDto(lottos);
    }

    public List<List<Integer>> getLottos() {
        return Collections.unmodifiableList(viewLottos);
    }
}
