package lotto.view;

import lotto.model.Lotto;

import java.util.List;

public class ResultView {

    public static void printGeneratedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.stream()
                .map(Lotto::getLottoNumbers)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {

    }
}
