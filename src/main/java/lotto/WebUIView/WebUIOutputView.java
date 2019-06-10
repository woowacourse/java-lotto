package lotto.WebUIView;

import com.google.common.base.Joiner;
import lotto.domain.Lottos;
import lotto.domain.PurchaseInformation;

import java.util.List;
import java.util.stream.Collectors;

public class WebUIOutputView {
    private static final String START_BRACE = "[";
    private static final String END_BRACE = "]";
    private static final String JOINER = ", ";
    private static final String NEW_LINE = "\n";

    public static String outputLottosPurchaseMessage(PurchaseInformation purchaseInformation) {
        return "수동으로 " + purchaseInformation.getManualLottoCount()
                + "장, 자동으로" + purchaseInformation.getAutoLottoCount()+ "장 구매했습니다.";
    }

    public static List<String> outputLottos(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(lotto -> makeNumbersView(lotto.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static String makeNumbersView(List<Integer> numbers) {
        StringBuilder builder = new StringBuilder(START_BRACE);
        builder.append(Joiner.on(JOINER).join(numbers));
        builder.append(END_BRACE);
        builder.append(NEW_LINE);
        return builder.toString();
    }
}
