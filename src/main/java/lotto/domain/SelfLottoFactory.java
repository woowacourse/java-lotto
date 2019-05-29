package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelfLottoFactory {
    private static final int MIN_SELF_SIZE = 0;
    private final int selfSize;

    public SelfLottoFactory(int selfSize , int lottoCount) {
        checkSelfSize(selfSize,lottoCount);
        this.selfSize = selfSize;
    }

    private void checkSelfSize(int selfSize ,int lottoCount) {
        if(selfSize < MIN_SELF_SIZE || selfSize > lottoCount){
            throw new IllegalArgumentException("수동으로 입력할 횟수가 올바르지 않습니다.");
        }
    }

    public int getSelfSize() {
        return selfSize;
    }

    public Lotto generateSelfLotto(String selfInput){
        List<Integer> selfNumbers = convertSelfLotto(getSplitSelfInputs(selfInput));
        return new Lotto(selfNumbers);
    }

    private List<String> getSplitSelfInputs(String selfInput) {
        return Arrays.asList(selfInput.split(","));
    }

    private List<Integer> convertSelfLotto(List<String> selfInputs) {
        return selfInputs.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

}
