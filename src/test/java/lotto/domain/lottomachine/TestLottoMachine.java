package lotto.domain.lottomachine;

import java.util.Arrays;
import java.util.List;

public class TestLottoMachine implements LottoMachine{
    @Override
    public List<Integer> generate() {
        return Arrays.asList(1,2,3,4,5,6);
    }
}
