package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelfLottoFactory {
    public static List<Lotto> generateSelfLottos(List<String> selfInputs){
        List<Lotto> selfLottos = new ArrayList<>();
        for(String self : selfInputs) {
            selfLottos.add(generateSelfLotto(self));
        }
        return selfLottos;
    }

    private static Lotto generateSelfLotto(String selfInput){
        List<Integer> selfNumbers = convertSelfLotto(Arrays.asList(selfInput.split(",")));
        return new Lotto(selfNumbers);
    }

    private static List<Integer> convertSelfLotto(List<String> selfInputs) {
        return selfInputs.stream().map(Integer::parseInt).collect(Collectors.toList());
    }


}
