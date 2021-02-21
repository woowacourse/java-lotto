package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

//public class LottoControllerTest {

//    LottoController lottoController;

//    @BeforeEach
//    void setup() {
//        lottoController = new LottoController(new TestLottoMachine());
//    }

//    @Test
//    @DisplayName("로또 구매 결과 확인")
//    void buyLottoCheck() {
//        Ticket ticket = new Ticket(new Money(14000));
//        lottoController.generateLottos(ticket);
//        LottoRepository lottoRepositoryByLottoManager = lottoManager.buyLotto(ticket);

//        for (int i = 0; i < count; i++) {
//            List<Integer> expected = lottoRepository.toList()
//                                                    .get(i)
//                                                    .getNumbers();
//            List<Integer> actual = lottoRepositoryByLottoManager.toList()
//                                                                .get(i)
//                                                                .getNumbers();
//            assertThat(actual).isEqualTo(expected);
//        }
//    }

//    @Test
//    @DisplayName("로또 긁은 내역 확인")
//    void scratchLottoCheck() {
//        Ticket ticket = new Ticket(new Money(2000));

//        lottoManager.buyLotto(ticket);
//        WinningLotto winningLotto = new WinningLotto(Lotto.createByInteger(Arrays.asList(1, 2, 3, 4, 5, 6)),
//                new LottoNumber(7));
//        RatingCounter ratingCounter = lottoManager.scratchLotto(winningLotto);

//        assertThat(ratingCounter.get(Rating.FIRST)).isEqualTo(2);
//    }
//}
