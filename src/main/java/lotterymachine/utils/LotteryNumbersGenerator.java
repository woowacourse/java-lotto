package lotterymachine.utils;

import lotterymachine.domain.LotteryNumber;

import java.util.List;

public interface LotteryNumbersGenerator {
    List<LotteryNumber> generate();
}
