const MIN_MONEY = 1000
const MAX_MONEY = 100000000
var moneyCondition


function onKeyDownMoney() {
    if (event.keyCode == 13) {
        clickMoneyInput()
    }
}

function clickMoneyInput() {
    moneyCondition = true
    var money = parseInt($("#money-input").val())
    generateLottoBuyCount(money)
}

function generateLottoBuyCount(money) {
    generateMoneyCondition(money)

    if (moneyCondition) {
        callLottoBuyCount(money);
    }
}

function generateMoneyCondition(money) {
    checkMoneyMin(money)
    checkMoneyMax(money)
}

function checkMoneyMin(money) {
    if (money < MIN_MONEY || isNaN(money)) {
        alert("최소 구매 금액은 1,000원 입니다.")
        moneyCondition = false
    }
}

function checkMoneyMax(money) {
    if (money > MAX_MONEY) {
        alert("최대 구매 금액은 100,000,000원 입니다.")
        moneyCondition = false
    }
}

function callLottoBuyCount(money) {
    var body = {money:money}
    var queryString = JSON.stringify(body);
    $.ajax({
        type : "POST",
        url : "/LottoBuyCount",
        data : queryString,
        contentType : 'application/json;charset=UTF-8;version=1.0',

        error : function(xhr, status, error){
            alert(error)
        },
        success : generateMoneyView
    })
}

function generateMoneyView(data) {
    var dataJson = JSON.parse(data)
    changeMoneyArea(dataJson.money, dataJson.count)
}

function changeMoneyArea(money, count) {
    $("#money-input").val(money)
    $("#money-input").attr("disabled","true")
    $("#money-btn").attr("disabled","true")
    $("#lottocount-text").text(count)
    $("#lottocount-area").show("slow")
    $("#lottoset-btn").focus()
}
