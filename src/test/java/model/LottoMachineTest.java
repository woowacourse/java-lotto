package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    private static final LottoNumbers WINNING_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
    private static final LottoNumber BONUS = new LottoNumber(7);
    private static final LottoNumbers FIRST_PRIZE_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
    private static final LottoNumbers SECOND_PRIZE_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 3, 4, 5, 7));
    private static final LottoNumbers THIRD_PRIZE_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 3, 4, 5, 8));
    private static final LottoNumbers NOTHING_PRIZE_LOTTO_NUMBERS = new LottoNumbers(List.of(1, 2, 9, 11, 12, 13));
    private static final Money FIRST_PRIZE = LottoRank.FIRST.getPrize();
    private static final Money SECOND_PRIZE = LottoRank.SECOND.getPrize();
    private static final Money THIRD_PRIZE = LottoRank.THIRD.getPrize();


    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(new WinningLottoNumbers(WINNING_LOTTO_NUMBERS, BONUS),
            new LottoNumbersGenerator() {
                LottoNumbers[] lottoNumbers = {
                    FIRST_PRIZE_LOTTO_NUMBERS, FIRST_PRIZE_LOTTO_NUMBERS,
                    SECOND_PRIZE_LOTTO_NUMBERS, THIRD_PRIZE_LOTTO_NUMBERS,
                    THIRD_PRIZE_LOTTO_NUMBERS, NOTHING_PRIZE_LOTTO_NUMBERS
                };
                int index = 0;

                @Override
                public LottoNumbers createLottoNumbers() {
                    return lottoNumbers[index++];
                }
            });
    }
}
