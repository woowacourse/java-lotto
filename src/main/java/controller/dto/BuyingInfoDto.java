package controller.dto;

import java.util.List;

import domain.Lotto;
import domain.OrderForm;

public class BuyingInfoDto {
	private final OrderForm orderForm;
	private final List<Lotto> totalLottos;

	public BuyingInfoDto(OrderForm orderForm, List<Lotto> totalLottos) {
		this.orderForm = orderForm;
		this.totalLottos = totalLottos;
	}

	public OrderForm getOrderForm() {
		return orderForm;
	}

	public List<Lotto> getTotalLottos() {
		return totalLottos;
	}
}
