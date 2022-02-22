public class OutputView {

    private final static String MESSAGE_LOTTOS_NUMBER = "%d개를 구매했습니다.";
    private final static String NUMBER_DELIMITER = ", ";

//    생성된 로또 번호와 개수 출력하는 기능
    public void printLottosInformations(Lottos lottos) {

        System.out.printf(MESSAGE_LOTTOS_NUMBER, lottos.getLottos().size());

        for (Lotto lotto : lottos.getLottos()) {
            System.out.println("[" + String.join(NUMBER_DELIMITER, (CharSequence) lotto.getLottoNumbers()) + "]");
        }
    }
}
