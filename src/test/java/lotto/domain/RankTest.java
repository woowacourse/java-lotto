package lotto.domain;

import lotto.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @ParameterizedTest
    @DisplayName("1등부터 꼴등까지 골라내는 테스트")
    @CsvSource(value = {"1,2,3,4,5,6:FIRST",
            "1,2,3,4,5,7:SECOND",
            "1,2,3,4,5,10:THIRD",
            "1,2,3,4,44,45:FOURTH",
            "1,2,3,43,44,45:FIFTH",
            "1,2,40,41,42,43:NO_RANK"}, delimiter = ':')
    void calculate_each_rank_count_test(String input,Rank winningExpected) {
        String winningBallInput = "1,2,3,4,5,6";
        LottoBall bonusBall = new LottoBall("7");
        LottoTicket winningTicketInput = new LottoTicket(winningBallInput);

        WinningTicket winningTicket = new WinningTicket(winningTicketInput.getLottoTicket(),bonusBall);
        LottoTicket lottoTicket = new LottoTicket(input);

        long hitCount = winningTicket.hitLottoBall(lottoTicket);
        boolean hitBonus = winningTicket.hitBonusBall(lottoTicket);

        assertThat(Rank.determineRank(hitCount,hitBonus)).isEqualTo(winningExpected);
    }

    @Test
    @DisplayName("랭크마다 몇명의 사람이 있는지 테스트")
    void RankCount(){
        String winningBallInput = "1,2,3,4,5,6";
        LottoBall bonusBall = new LottoBall("7");
        LottoTicket winningTicketInput = new LottoTicket(winningBallInput);

        String input1 = "1,2,3,4,5,6";
        String input2 = "1,2,3,4,5,6";
        String input3 = "1,2,3,4,5,6";

        LottoTickets.insertLottoTicket(new LottoTicket(input1));
        LottoTickets.insertLottoTicket(new LottoTicket(input2));
        LottoTickets.insertLottoTicket(new LottoTicket(input3));

        WinningTicket winningTicket = new WinningTicket(winningTicketInput.getLottoTicket(),bonusBall);

        List<Rank> lottoTicketRank = LottoTickets.getLottoTickets()
                .stream()
                .map(lottoTicket->
                        Rank.determineRank(winningTicket.hitLottoBall(lottoTicket)
                                ,winningTicket.hitBonusBall(lottoTicket)))
                .collect(Collectors.toList());

        assertThat(Rank.calculateEachRankCount(lottoTicketRank).get(Rank.FIRST)).isEqualTo(3);
    }
}