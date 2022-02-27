package lotterymachine.controller;

import lotterymachine.domain.LotteryNumber;
import lotterymachine.utils.LotteryNumbersGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LotteryControllerTest {
    @Test
    void getLotteryTicketResult() {
        List<LotteryNumber> generate = LotteryNumbersGenerator.generate();
        for (LotteryNumber lotteryNumber: generate) {
            System.out.println(lotteryNumber.getNumber());
        }
    }

}