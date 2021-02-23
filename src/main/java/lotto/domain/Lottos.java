package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {
    private static List<Lotto> purchaseLottos = new ArrayList<>();

    private Lottos(List<Lotto> lottos){
        this.purchaseLottos.addAll(lottos);
    }

    public static Lottos createAutoLotto(int count) {
        return new Lottos(Stream.generate(() -> new Lotto(LottoGenerator.makeLottoNumbers()))
                .limit(count)
                .collect(Collectors.toList()));
    }

    public static Lottos createManualLotto(List<String> manualPurchaseNumbers) {
        List<Lotto> manualLotto = new ArrayList<>();

        for (String input : manualPurchaseNumbers) {
            validateInputCheck(input);
            List<Integer> lottoNumbers = Arrays.stream(input.split(", "))
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            manualLotto.add(new Lotto(lottoNumbers));
        }
        return new Lottos(manualLotto);
    }

    private static void validateInputCheck(String input) {
        if (!Arrays.stream(input.split(", "))
                .allMatch(s -> s.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException("숫자와 , 를 이용하여 입력해주세요.");
        }
    }

    public static List<Lotto> getPurchaseLottos() {
        return purchaseLottos;
    }
}
