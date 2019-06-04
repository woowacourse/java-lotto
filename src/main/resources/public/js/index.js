const ctx = {
    state: {
        numOfManualLottos: 0,
        winningNumberInputsToScan: new Set(),
    },
    view: {
        updateNumOfManual: () =>
        {
            document.querySelector(".qty-input").value = ctx.state.numOfManualLottos;
        }, scanWinningNumbers: () =>
        {
            let allValid = true;
            const winningNumSet = new Set();
            ctx.state.winningNumberInputsToScan.forEach((elm) =>
            {
                const val = elm.value;
                winningNumSet.add(val);
                if (!ctx.util.isValidLottoNumber(val))
                {
                    allValid = false;
                }
            });

            if (winningNumSet.size != ctx.state.winningNumberInputsToScan.size)
            {
                allValid = false;
            }

            if (allValid)
            {
                document.querySelector(".winning-number-group .alert").classList.add("d-none");
                return;
            }
            document.querySelector(".winning-number-group .alert").classList.remove("d-none");
        }, getBuyingMoney: () =>
        {
            return Number(document.querySelector("#input-money").value);
        }, getWinningNumbers: () =>
        {
            const nums = [];
            document.querySelectorAll("input.winning-number")
                .forEach((elm) =>
                {
                    nums.push(Number(elm.value));
                });
            return nums;
        }, getWinningBonusNumber: () =>
        {
            return Number(document.querySelector(".winning-bonus-number").value);
        }, getManualNumbers: () => {
            const numLists = [];
            const numContainers = document.querySelectorAll(".manual-numbers > div");
            
            numContainers.forEach((container) => {
                const nums = [];
                for (i of container.querySelectorAll("input")) {
                    nums.push(Number(i.value));
                }
                numLists.push(nums);
            });
            return numLists;
        }, setAddManualNumberBtnDisable: (disabled) => {
            document.querySelector(".btn-add-manual-number").setAttribute("disabled", disabled);
        }, setResultTableVisible: (visible) => {
            if (visible) {
                document.querySelector(".result-table-wrapper").classList.remove("d-none");
                return;
            }
            document.querySelector(".result-table-wrapper").classList.add("d-none");
        }, setResultTableRowValue: (rowName, value) => {
            const row = document.querySelector(".result-table-wrapper tr.result-row-" + rowName)
            row.children[1].innerText = value;
        }
    },
    templates: {},
    eventHandlers: {
        onAddManualNumberButton: (evt) =>
        {
            const elm = document.createElement("div");
            elm.innerHTML = ctx.templates.manualNumber();
            elm.classList.add("input-group-lottonumber");
            document.querySelector(".manual-numbers").append(elm);
            ctx.state.numOfManualLottos++;
            ctx.view.updateNumOfManual();
            elm.querySelector("button").addEventListener("click", () =>
            {
                console.log("제거");
                document.querySelector(".manual-numbers").removeChild(elm);
                ctx.state.numOfManualLottos--;
                ctx.view.updateNumOfManual();
            });
        }, onLottoNumberInput: (evt) =>
        {
            ctx.state.winningNumberInputsToScan.add(evt.target);
            let val = evt.target.value;
            if (val.length > 2)
            {
                val = val.substring(0, 2);
                evt.target.value = val;
            }
            ctx.view.scanWinningNumbers();
        }, onBtnBuyClick: (evt) => {
            console.log("buy Lotto!");
            const { view } = ctx;
            const data = {};
            data["buyingMoney"] = view.getBuyingMoney();
            data["winningNumbers"] = view.getWinningNumbers();
            data["winningBonusNumber"] = view.getWinningBonusNumber();
            data["manualNumbers"] = view.getManualNumbers();

            ctx.view.setAddManualNumberBtnDisable(true);

            // TODO: REMOVE THIS LINE!!
            console.log(data);

            const xhr = new XMLHttpRequest();
            xhr.addEventListener("load", () => ctx.eventHandlers.onBuyResult(JSON.parse(xhr.response)));
            xhr.open("POST", "/buy");
            xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8");
            xhr.send(JSON.stringify(data));
        }, onBtnResetClick: (evt) => {
            // 뷰 상태 초기화
            document.querySelector(".manual-numbers").innerHTML = "";
            ctx.state.numOfManualLottos = 0;
            ctx.view.updateNumOfManual();
            ctx.state.winningNumberInputsToScan = [];
            ctx.view.setAddManualNumberBtnDisable(false);
            ctx.view.setResultTableVisible(false);
        }, onBuyResult: (res) => {
            if (res["result"] !== "ok") {
                // TODO: error handling
                return;
            }

            const { view } = ctx;
            view.setResultTableRowValue("first", res["aggregation"]["rankCounts"]["first"]);
            view.setResultTableRowValue("second", res["aggregation"]["rankCounts"]["second"]);
            view.setResultTableRowValue("third", res["aggregation"]["rankCounts"]["third"]);
            view.setResultTableRowValue("fourth", res["aggregation"]["rankCounts"]["fourth"]);
            view.setResultTableRowValue("fifth", res["aggregation"]["rankCounts"]["fifth"]);
            view.setResultTableRowValue("none", res["aggregation"]["rankCounts"]["none"]);
            view.setResultTableRowValue("earning-rate", Math.round(res["aggregation"]["earningRate"] * 100) + "%");
            view.setResultTableVisible(true);
        }
    },
    util: {
        isValidLottoNumber: (val) =>
        {
            const num = Number(val);
            if (num === NaN)
            {
                return false;
            }
            if (num < 1 || num > 45)
            {
                return false;
            }

            return true;
        }
    }
};

document.addEventListener("DOMContentLoaded", () =>
{
    const { eventHandlers } = ctx;
    ctx.templates["manualNumber"] = Handlebars.compile($("#lottonumber-input-group").html());

    document.querySelector(".btn-add-manual-number")
        .addEventListener("click", eventHandlers.onAddManualNumberButton);
    document.querySelector(".btn-buy")
        .addEventListener("click", eventHandlers.onBtnBuyClick)
    document.querySelector(".btn-reset")
        .addEventListener("click", eventHandlers.onBtnResetClick);
    document.querySelectorAll(".lotto-number-input").forEach((v) =>
    {
        v.addEventListener("input", ctx.eventHandlers.onLottoNumberInput);
    });
});
