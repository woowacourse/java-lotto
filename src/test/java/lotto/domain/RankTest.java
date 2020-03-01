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

        assertThat(Rank.calculateEachRankCount(winningTicket).get(Rank.FIRST)).isEqualTo(3);
    }
}