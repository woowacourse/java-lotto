let allPurchaseAmount = 0;
let allPurchaseCount = 0;
let manualLottos = [];
let winningLotto = [];
let bonusNo = 0;

const numberInputElement = `:input[type="number"]`;

const manualLottoNumsDiv = `<li><div class="manualLottoNums">` +
`<input type="number" class="manualLottoNum" min="1" max="45" />`.repeat(6) +
`<input type="button" value="추가" onClick="getManualLottoNums(this)" /></div></li>`;

const winningStatDiv = (result) => `<p>당첨 통계</p>
<ul>
  <li>3개 일치 (5,000원) - ${result.stat["5"]}개</li>
  <li>4개 일치 (50,000원) - ${result.stat["4"]}개</li>
  <li>5개 일치 (1,500,000원) - ${result.stat["3"]}개</li>
  <li>5개 일치, 보너스 볼 일치 (30,000,000원) - ${result.stat["2"]}개</li>
  <li>6개 일치 (2,000,000,000원) - ${result.stat["1"]}개</li>
</ul>
<p>총 수익률은 ${result.profitRate}%입니다.</p>`;

const manualLottoNumsDivApplied = (lottoNumsArray) => `<li>
<div class="manualLottoNums">${lottoNumsArray.join(", ")}</div>
</li>`;

const manualLottoList = $("#manualLottosDiv").first().find("ul").first();

const getManualLottoNums = (button) => {
    const parent = $(button).parent();
    const numbers = parent.find(numberInputElement)
        .map((index, element) => parseInt($(element).val()))
        .filter((index, number) => !isNaN(number))
        .get();
    applyManualLottoNums(checklottoNums(numbers), parent);
};

const checkLottoNum = (number) => {
    const min = 1;
    const max = 45;
    return (min <= number) && (number <= max)
};

const checklottoNums = (lottoNumArray) => {
    const unique = [...new Set(lottoNumArray)].sort((a, b) => a - b);
    const filtered = unique.filter(checkLottoNum);
    if (filtered.length === 6) return filtered;
    alert("올바르지 않은 로또 번호입니다.");
}

const applyManualLottoNums = (lottoNumsArray, buttonParent) => {
    if (lottoNumsArray) {
        $(buttonParent).html(manualLottoNumsDivApplied(lottoNumsArray));
        manualLottos.push(lottoNumsArray);
    }
};

const manualLottoCount = (value) => {
    const count = parseInt(value);
    if (allPurchaseCount - count < 0) {
        alert("구입 금액보다 수동 로또 개수가 더 많습니다.");
        return;
    }
    manualLottoList.html(manualLottoNumsDiv.repeat(count));
};

const changePurchaseAmount = (value) => {
    allPurchaseAmount = parseInt(value)
    allPurchaseCount = Math.ceil(allPurchaseAmount / 1000);
};

const getWinningLotto = () => {
    const lottoList = $("#winningLotto").first()
        .find(numberInputElement)
        .map((index, element) => parseInt($(element).val()))
        .filter((index, number) => !isNaN(number))
        .get();
    const bonus = parseInt($("#bonusLottoNum").first().val());
    winningLotto = checklottoNums(lottoList);
    bonusNo = checkLottoNum(bonus) ? bonus : bonusNo;
};

const queryToLottos = () => {
    getWinningLotto();
    if (allPurchaseAmount < 1 || winningLotto.length !== 6 || bonusNo < 1) {
        alert("입력한 내용을 다시 한번 살펴보시기 바랍니다.");
        return;
    }
    const query = {allPurchaseAmount, manualLottos, winningLotto, bonusNo};
    $.ajax({
        type: "POST",
        url: "/api/newLottos",
        data: JSON.stringify(query),
        dataType: "json",
        success: response => {
            const div = winningStatDiv(response.result);
            $("#winningStat").html(div);
        }
    });
};
