package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomLottoPaper {
    private List<Lotto> lottos;

    public CustomLottoPaper() {
        lottos = new ArrayList<>();
    }

    public void addCustomLotto(String[] userNumbers) {
        lottos.add(new Lotto(makeNumbers(userNumbers)));
    }

    public List<LottoNumber> makeNumbers(String[] userNumbers) {
        return Arrays.stream(userNumbers)
                .map(number -> new LottoNumber(Integer.parseInt(number)))
                .collect(Collectors.toList());
    }

    public LottoPaper getPaper() {
        return new LottoPaper(lottos);
    }
}
