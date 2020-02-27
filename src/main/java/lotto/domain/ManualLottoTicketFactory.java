package lotto.domain;

import lotto.view.InputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManualLottoTicketFactory {

	private static final int WINNING_NUMBER_SIZE = 6;
	private static final int TICKET_PRICE = 1000;

	private final int manualLottoTicketsAmount;
	private final LottoTickets lottoTickets;

	public ManualLottoTicketFactory(String manualLottoTicketsAmountValue, PurchasingAmount purchasingAmount) {
		checkValidManualTicketsValue(manualLottoTicketsAmountValue, purchasingAmount);
		this.manualLottoTicketsAmount = Integer.parseInt(manualLottoTicketsAmountValue);
		this.lottoTickets = generateManualLottoTickets(manualLottoTicketsAmount);
	}

	private LottoTickets generateManualLottoTickets(int manualLottoTicketsSize) {
		List<LottoTicket> manualLottoTickets = new ArrayList<>();
		for(int ticketIndex=0; ticketIndex<manualLottoTicketsSize; ticketIndex++) {
			generateManualLottoTickets(manualLottoTickets, ticketIndex);
		}
		return new LottoTickets(manualLottoTickets);
	}

	private void generateManualLottoTickets(List<LottoTicket> manualLottoTickets, int ticketIndex) {
		List<String> manualLottoValue = Arrays.asList(InputView.inputManualNumbers(ticketIndex).split(", "));
		checkValidationOf(manualLottoValue);
		LottoTicket manualLottoTicket = generateManualLottoTicket(manualLottoValue);
		manualLottoTickets.add(manualLottoTicket);
	}

	private LottoTicket generateManualLottoTicket(List<String> manualLottoValue) {
		List<LottoNumber> manualLottoNumbers;
		manualLottoNumbers = manualLottoValue.stream()
				.mapToInt(Integer::parseInt)
				.mapToObj(LottoNumber::new)
				.collect(Collectors.toList());
		return new LottoTicket(manualLottoNumbers);
	}

	private void checkValidationOf(List<String> manualLottoNumbers) {
		if (isNotMatchSize(manualLottoNumbers)) {
			throw new IllegalArgumentException("번호는 6자리 이어야 합니다");
		}
		if (isNotNumberFormat(manualLottoNumbers)) {
			throw new IllegalArgumentException("번호는 정수만 가능합니다");
		}
		if (isDuplicatedNumber(manualLottoNumbers)) {
			throw new IllegalArgumentException("번호는 허용하지 않습니다");
		}
	}

	private boolean isDuplicatedNumber(final List<String> winningNumbersValue) {
		final HashSet<String> checkDuplicateSet = new HashSet<>(winningNumbersValue);
		return isNotMatchSize(new ArrayList<>(checkDuplicateSet));
	}

	private boolean isNotMatchSize(final List<String> winningNumbersValue) {
		return winningNumbersValue.size() != WINNING_NUMBER_SIZE;
	}

	private boolean isNotNumberFormat(final List<String> winningNumbersValue) {
		return winningNumbersValue.stream()
				.anyMatch(isNotDigit());
	}

	private Predicate<String> isNotDigit() {
		return str -> str.chars()
				.anyMatch(ch -> !Character.isDigit(ch));
	}

	private void checkValidManualTicketsValue(String manualLottoTicketsAmount, PurchasingAmount purchasingAmount) {
		if(isNotNumber(manualLottoTicketsAmount)) {
			throw new IllegalArgumentException("정수만 입력하십시오");
		}
		if(purchasingAmount.isOverPurchasingAmount(Integer.parseInt(manualLottoTicketsAmount) * TICKET_PRICE)) {
			throw new IllegalArgumentException("구매 금액 초과의 티켓은 구매 불가합니다");
		}
	}

	private boolean isNotNumber(final String lottoMoneyValue) {
		return lottoMoneyValue.chars()
				.anyMatch(c -> !Character.isDigit(c));
	}

	public LottoTickets buyManualLottoTickets(PurchasingAmount purchasingAmount) {
		purchasingAmount.calculateManualLottoTicketMoney(manualLottoTicketsAmount * TICKET_PRICE);
		return this.lottoTickets;
	}
}

