let inputData = {
    money: undefined,
    lottoCount: undefined,
    lottoList: [],
    winningLotto: [],
    bonusNo: undefined
};

let currentRoundNo = 0;
let outputData;

function makelottoTemplete(no) {
    return '<div class="form-group" data-name="lotto" data-no="' + no + '">' +
        '<label>Manual-Lotto</label>' +
        '<div class="input-group">' +
        '<input type="number" class="form-control" min="1" max="45" data-no="0" required>' +
        '<input type="number" class="form-control" min="1" max="45" data-no="1" required>' +
        '<input type="number" class="form-control" min="1" max="45" data-no="2" required>' +
        '<input type="number" class="form-control" min="1" max="45" data-no="3" required>' +
        '<input type="number" class="form-control" min="1" max="45" data-no="4" required>' +
        '<input type="number" class="form-control" min="1" max="45" data-no="5" required>' +
        '</div>' +
        '</div>';
}

function addLottoEvent($e) {
    $e.find('input').on('input', function (event) {
        event.preventDefault();
        let lottoListNo = $(this).closest('div[data-name=lotto]').data('no');
        let lottoNo = $(this).data('no');
        inputData.lottoList[lottoListNo][lottoNo] = $(this).val();
    });
}

$(function () {
    let $lottoList = $('div[data-name=lotto-list]');

    $(".nav-tabs a").click(function () {
        $(this).tab('show');
    });

    $('input#money').on('input', function (event) {
        event.preventDefault();
        let money = $(this).val();
        if (money < 0 || money > 100000) {
            $(this).val(inputData.money);
            return;
        }
        inputData.money = money;
    });

    $('input#lotto-count').on('input', function (event) {
        event.preventDefault();
        inputData.lottoCount = $(this).val() <= 100 ? $(this).val() : 100;
        $lottoList.html('');
        inputData.lottoList = [];
        for (let i = 0; i < inputData.lottoCount; i++) {
            let $lottoTemplete = $(makelottoTemplete(i));
            addLottoEvent($lottoTemplete);
            $lottoList.append($lottoTemplete);
            inputData.lottoList.push([]);
        }
    });

    $('div[data-name=winning-lotto]').find('input').on('input', function (event) {
        event.preventDefault();
        let lottoNo = $(this).data('no');
        inputData.winningLotto[lottoNo] = $(this).val();
    });

    $('input#bonus-no').on('input', function (event) {
        event.preventDefault();
        inputData.bonusNo = $(this).val();
    });

    $('button#submit').on('click', function (event) {
        event.preventDefault();
        $('form').submit();
    });

    $("form").on("submit", function (event) {
        $.ajax({
            method: "POST",
            url: "/lotto",
            contentType: 'application/json; charset=utf-8',
            dataType: "json",
            data: JSON.stringify(inputData)
        })
            .done(function (data) {
                $("#check-lotto-tab").click();
            });
    });

    $("#check-lotto-tab").click(function () {
        $.ajax({
            method: "GET",
            url: "/round/" + currentRoundNo,
            contentType: 'application/json; charset=utf-8',
            dataType: "json",
        })
            .done(function (data) {
                outputData = data;
                renderRound(outputData);
            });
    });

    $('#lotto-round-options').on('change', function () {
        $.ajax({
            method: "GET",
            url: "/round/" + this.value,
            contentType: 'application/json; charset=utf-8',
            dataType: "json",
        })
            .done(function (data) {
                outputData = data;
                renderRound(outputData);
            });
    });
});

function renderRound(outputData) {
    let $lottoRoundOptions = $('#lotto-round-options');
    let $roundOption = $(makeRoundOptionsTemplete(outputData.maxRoundNo));
    $lottoRoundOptions.html('').append($roundOption);
    $lottoRoundOptions.val(outputData.roundNo);

    let $lottoTickets = $('#lotto-tickets');
    let $lottos = $(makeLottosTemplete(outputData.lottoTickets.lottos));
    $lottoTickets.html('').append($lottos);

    let $winningLottoStatus = $('#winning-lotto-status');
    let $status = $(makeWinningStatus(outputData));
    $winningLottoStatus.html('').append($status);
}

function makeWinningStatus(outputData) {
    return makeWinningLotto(outputData.winningLotto.winningLotto.lotto, outputData.winningLotto.bonusNo) +
        '<p class="text-primary">구입금액 : ' + outputData.money.money + '원</p>' +
        '<p class="text-primary">수익률 : ' + outputData.yield + '%</p>' +
        '<p class="text-primary">1등 (6개 일치) : ' + outputData.winningLottoState.winningLottoState.FIRST + '개</p>' +
        '<p class="text-primary">2등 (5개, 보너스 일치) : ' + outputData.winningLottoState.winningLottoState.SECOND + '개</p>' +
        '<p class="text-primary">3등 (5개 일치) : ' + outputData.winningLottoState.winningLottoState.THIRD + '개</p>' +
        '<p class="text-primary">4등 (4개 일치) : ' + outputData.winningLottoState.winningLottoState.FOURTH + '개</p>' +
        '<p class="text-primary">5등 (3개 일치) : ' + outputData.winningLottoState.winningLottoState.FIFTH + '개</p>' +
        '<p class="text-primary">실패 (0개 일치) : ' + outputData.winningLottoState.winningLottoState.MISS + '개</p>';
}

function makeRoundOptionsTemplete(maxRoundNo) {
    let roundOptions = '';
    for (let i = 1; i <= maxRoundNo; i++) {
        roundOptions += '<option value="' + i + '">' + i + '</option>'
    }
    return roundOptions;
}

function makeLottosTemplete(lottos) {
    let lottosTemplete = '';
    for (let i = 0; i < lottos.length; i++) {
        lottosTemplete += makeLottoTemplete(lottos[i]['lotto']);
    }
    return lottosTemplete;
}

function makeWinningLotto(lotto, bonusNo) {
    return '<div class="d-flex p-3 text-white">' +
        '<div class="p-2 bg-info">' + lotto[0]['no'] + '</div>' +
        '<div class="p-2 bg-warning">' + lotto[1]['no'] + '</div>' +
        '<div class="p-2 bg-primary">' + lotto[2]['no'] + '</div>' +
        '<div class="p-2 bg-info">' + lotto[3]['no'] + '</div>' +
        '<div class="p-2 bg-warning">' + lotto[4]['no'] + '</div>' +
        '<div class="p-2 bg-primary">' + lotto[5]['no'] + '</div>' +
        '<div class="p-2 bg-info">' + bonusNo.no + '</div>' +
        '</div>';
}

function makeLottoTemplete(lotto) {
    return '<div class="d-flex p-3 text-white">' +
        '<div class="p-2 bg-info">' + lotto[0]['no'] + '</div>' +
        '<div class="p-2 bg-warning">' + lotto[1]['no'] + '</div>' +
        '<div class="p-2 bg-primary">' + lotto[2]['no'] + '</div>' +
        '<div class="p-2 bg-info">' + lotto[3]['no'] + '</div>' +
        '<div class="p-2 bg-warning">' + lotto[4]['no'] + '</div>' +
        '<div class="p-2 bg-primary">' + lotto[5]['no'] + '</div>' +
        '</div>';
}