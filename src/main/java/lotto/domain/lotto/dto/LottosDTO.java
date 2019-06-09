package lotto.domain.lotto.dto;

import lotto.domain.lotto.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottosDTO {
    private final static String SEPERATOR = ",";
    List<Lotto> lottos = new ArrayList<>();

    public void set(String queryParams) {
        List<Integer> lottoNumbers = new ArrayList<>();
        String[] tokens = queryParams.split(SEPERATOR);
        for (String token : tokens) {
            lottoNumbers.add(Integer.parseInt(token));
        }
        lottos.add(new Lotto(lottoNumbers));
    }

    public void set(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
