package lotto.view;

public class InputConvertor {

    private InputConvertor() {
    }

//    public static Money createMoney() {
//        try {
//            return new Money(InputView.inputMoney());
//        } catch (IllegalArgumentException e) {
//            printErrorMessage(e);
//            return createMoney();
//        }
//    }
//
//    public static Lottos createLottos(final int buyCounts) {
//        try {
//            return new Lottos(buyRandomLottos(buyCounts));
//        } catch (IllegalArgumentException e) {
//            printErrorMessage(e);
//            return createLottos(buyCounts);
//        }
//    }
//
//    private static List<Lotto> buyRandomLottos(final int buyCounts) {
//        return IntStream.range(0, buyCounts)
//                .mapToObj(index -> new Lotto(RandomLottoMachine.createRandomLottoNumbers()))
//                .collect(Collectors.toList());
//    }
//
//    public static WinLotto createWinLotto() {
//        try {
//            return new WinLotto(inputWinLotto(), inputBonusNumber());
//        } catch (IllegalArgumentException e) {
//            printErrorMessage(e);
//            return createWinLotto();
//        }
//    }
//
//    private static Lotto inputWinLotto() {
//        return new Lotto(InputView.inputWinLotto().stream()
//                .map(LottoNumber::valueOf)
//                .collect(Collectors.toList()));
//    }
//
//    private static LottoNumber inputBonusNumber() {
//        return LottoNumber.valueOf(InputView.inputBonusNumber());
//    }
}
