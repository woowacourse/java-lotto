package rule;

public enum Rule {
	LOTTO_SIZE(6),
	LOTTO_MIN_NUM(1),
	LOTTO_MAX_NUM(45);

	private final int ruleNum;

	Rule(int ruleNum) {
		this.ruleNum = ruleNum;
	}

	public int getRuleNum() {
		return ruleNum;
	}
}
