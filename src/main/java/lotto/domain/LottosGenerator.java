package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {

    static List<Lotto> generate(int lottosSize, List<List<Integer>> lottoNumbersList) {
        //질문: final을 붙이는 것이 의미가 있는 것인 지 궁금합니다. 제 생각에는 이후에 초기화를 더 하지 못하게 막음으로써 메모리 누수를 막을 수 있다고 생각하는데요. 어떻게 생각하시나요?
        final List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottosSize; i++) {
            Lotto lotto = new Lotto(lottoNumbersList.get(i));
            lottos.add(lotto);
        }

        return lottos;
    }
}
