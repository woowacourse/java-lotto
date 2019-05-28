package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class SelfLottoFactory {

    public static Lotto generateSelfLotto(List<String> selfInputs){
        List<Integer> selfNumbers = convertSelfLotto(selfInputs);
        return new Lotto(selfNumbers);
    }

    private static List<Integer> convertSelfLotto(List<String> selfInputs) {
        return selfInputs.stream().map(Integer::parseInt).collect(Collectors.toList());
    }


}
