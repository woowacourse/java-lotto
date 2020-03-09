package lotto.domain;

import lotto.util.Splitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    List<Integer> lottoNumbers = new ArrayList<>();
    Set<LottoTicket> lottoTickets = new HashSet<>();
    LottoResult lottoResult = new LottoResult();

    @BeforeEach
    void setUp() {
        lottoNumbers.add(1);
        lottoNumbers.add(2);
        lottoNumbers.add(3);
        lottoNumbers.add(4);
        lottoNumbers.add(5);
        lottoNumbers.add(6);
    }

    @BeforeEach
    void setLottoTicket() {
        String[] inputs = new String[]{"1,2,3,4,5,6", "1,2,3,4,5,7", "1,2,3,4,5,8", "1,2,3,7,8,9", "1,21,11,12,13,14"};
        for (String input : inputs) {
            ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(Splitter.splitInput(input));
            lottoTickets.add(new LottoTicket(manualLottoGenerator.generateNumbers()));
        }
//        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(Splitter.splitInput("1,2,3,4,5,6"));
//        lottoTickets.add(new LottoTicket()LottoGenerator.createManualLottoNumbers(Splitter.splitInput("1,2,3,4,5,6"))));
//        lottoTickets.add(new LottoTicket(LottoGenerator.createManualLottoNumbers(Splitter.splitInput("1,2,3,4,5,7"))));
//        lottoTickets.add(new LottoTicket(LottoGenerator.createManualLottoNumbers(Splitter.splitInput("1,2,3,4,5,8"))));
//        lottoTickets.add(new LottoTicket(LottoGenerator.createManualLottoNumbers(Splitter.splitInput("1,2,3,7,8,9"))));
//        lottoTickets.add(new LottoTicket(LottoGenerator.createManualLottoNumbers(Splitter.splitInput("1,21,11,12,13,14"))));
    }

    @ParameterizedTest
    @CsvSource(value = {"'FIRST'", "SECOND", "THIRD", "FIFTH", "ZERO"})
    @DisplayName("맞은 번호 개수에 따른 등수 계산")
    void analyzeRank(WinningValue rank) {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(manualLottoGenerator.generateNumbers(), 7);
        lottoResult.analyzeRank(lottoTickets, winningLotto);
        assertThat(lottoResult.getLottoResult().get(rank)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산")
    void calculateRewardRate() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(lottoNumbers);
        WinningLotto winningLotto = new WinningLotto(manualLottoGenerator.generateNumbers(), 7);
        lottoResult.analyzeRank(lottoTickets, winningLotto);
        assertThat(lottoResult.calculateRewardRate(5000)).isEqualTo(40630100);
    }

}
