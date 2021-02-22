package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.Prize;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.WinningLottoTicket;
import lotto.view.InputView;
import lotto.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoControllerTest {
    LottoController lottoController;

    @BeforeEach
    void setup() {
        InputView inputView = new InputView(new Scanner(System.in));
        lottoController = new LottoController(inputView, new OutputView(), new Money(1000));
    }

    @DisplayName("수동, 자동 로또 생성 메소드 확인")
    @Test
    void createAllLottoTest(){
        List<Integer> rowLottoNumbers = Arrays.asList(6,5,4,3,2,1);
        LottoNumbers lottoNumbers = new LottoNumbers(rowLottoNumbers);

        List<LottoNumbers> lottoNumbersBundle = Arrays.asList(lottoNumbers);
        LottoTickets allLottoTickets = lottoController.createAllLottoTickets(new Money(2000), 1, lottoNumbersBundle);

        assertThat(allLottoTickets.size()).isEqualTo(2);
        assertThat(allLottoTickets.list().get(0).list()).isEqualTo(lottoNumbers.list());
    }

    @DisplayName("당첨로또 생성 메소드 확인")
    @Test
    void createWinningLotto() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLottoTicket winningLottoTicket = lottoController.createWinningLotto(winningNumbers, bonusNumber);

        LottoTicket expectedFirstPrize = LottoTicket.createLottoTicket(winningNumbers);
        assertThat(winningLottoTicket.compareNumbers(expectedFirstPrize)).isEqualTo(Prize.FIRST);
    }

    @DisplayName("로또 결과물을 잘 생성하는지 확인")
    @Test
    void calculateLottoResult() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLottoTicket winningLottoTicket = lottoController.createWinningLotto(winningNumbers, bonusNumber);

        LottoTicket expectedFirstPrize = LottoTicket.createLottoTicket(winningNumbers);
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(expectedFirstPrize));
        LottoResult lottoResult = lottoController.calculateLottoResult(lottoTickets, winningLottoTicket);

        assertThat(lottoResult.calculatePrizeMoney()).isEqualTo(2000000000);
    }
}