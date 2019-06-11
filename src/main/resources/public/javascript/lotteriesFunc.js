const LOTTO_SIZE = 6
const WIN_LOTTO_SIZE = 7
var now_mode_size = LOTTO_SIZE
var lotto_available_size = now_mode_size;
var count = 0
var manual_count = 0
var lotto = []
var lotteries = []
var temp

function checkWrongApproach() {
	if (count < 1) {
		alert("잘못된 접근입니다.")
		return
	}
}

function clickShowMakeLottoArea() {
	count = $("#lottocount-text").text()
	$("#lottoset-btn").hide()
	$("#lotto-area").show("slow", bindLottoElementToggle())
	drawLotteriesArea()
}


function bindLottoElementToggle() {
    var number = 1
    $("#lottoelement-area-old > button").each(function() {
        $(this).attr("number",number)
        $(this).attr("select", "off")
        $(this).attr("type","button")
        $(this).bind("click", function(){
            lottoElementToggle($(this))
        })
        number += 1
    })
}

function lottoElementToggle(element) {
	if (element.attr("select") === "off") {
		clickToggle(element)
		return
	}
	unClickToggle(element)
}

function clickToggle(element) {
	if (lotto_available_size == 0) {
		alert("로또는 "+now_mode_size+"개의 번호를 눌러야 합니다.")
		return
	}
	element.attr("select", "on")
	element.css("color", "red")
	var number = element.attr("number")
	lotto.push(number)
	lotto_available_size -= 1
}

function unClickToggle(element) {
	element.attr("select", "off")
	element.css("color", "black")
	var number = element.attr("number")
	lotto.splice(lotto.indexOf(number),1)
	lotto_available_size += 1;
}

function drawLotteriesArea() {
	var lottoDom = $("#lotto_number");
	var lottoClone
	for (var i = 1; i <= count ; i++) {
		lottoClone = lottoDom.clone()
		lottoClone.attr("id", "lotto_"+i)
		lottoClone.appendTo(lottoDom.parent())
	}
	lottoDom.remove()
}

function clickMakeLotto() {
	if (lotto_available_size != 0) {
		alert("숫자를 전부 선택하고 입력해주세요")
		return
	}
	lotteries.push(lotto)
	drawLottoArea()
	initMakeLotto()
}

function initMakeLotto() {
	manual_count += 1
	lotto_available_size = now_mode_size
	lotto = []

	$("#lottoelement-area-old > button").css("color", "black")
	$("#lottoelement-area-old > button").attr("select", "off")
	
	if (manual_count >= count) {
		$("#makelotto-btn").attr("disabled","true")
	}

}

function drawLottoArea() {
	var lottoId = manual_count + 1
	var lottoArea = $("#lotto_"+lottoId+" button")
	for(var i = 0 ; i < lottoArea.length ; i++) {
		lottoArea.eq(i).text(lotto[i])
	}
}

function clickMakeLotteries() {
	var body = {}
	body.lotteries = lotteries
	body.manual_count = manual_count != lotteries.length ? lotteries.length : manual_count
	var queryString = JSON.stringify(body)

	$.ajax({
		type : "POST",
		url : "detailLotteries",
		data : queryString,
		contentType : 'application/json;charset=UTF-8;version=1.0',

		error : function(xhr, status, error) {
			alert(error)
		},
		success : generateLotteries
	})
}

function generateLotteries(data) {
	var dataJson = JSON.parse(data)
	// data 길이랑 count랑 다르면 alert
	generateLotteriesView(dataJson.lotteries)
	changeLotteriesView(dataJson.manual_count, dataJson.auto_count)
	now_mode_size = WIN_LOTTO_SIZE
}

function generateLotteriesView(lotteriesData) {
	temp = lotteriesData
	var lotteriesArea, lottoData, id

	for(var i = 0 ; i < lotteriesData.length ; i++) {
		id = i + 1
		lottoData = lotteriesData[i]
		lotteriesArea = $("#lotto_"+id+" button")
		generateLottoView(lotteriesArea, lottoData)
	}
}

function generateLottoView(lotteriesArea, lottoData) {
	for(var i = 0 ; i < lottoData.length ; i++) {
		lotteriesArea.eq(i).text(lottoData[i])
	}
}

function changeLotteriesView(manual_count, auto_count) {
	$("#manualcnt-text").text(manual_count)
	$("#autocnt-text").text(auto_count)
	$("#lotteriescnt").show("slow")
	$("#end-btn").attr("disabled", "true")
	$("makelotto-btn").attr("disabled", "false")
}