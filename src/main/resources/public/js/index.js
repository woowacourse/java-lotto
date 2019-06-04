const ctx = {
    state: {
        numOfManualLottos: 0,
        winningNumberInputsToScan: new Set(),
    },
    view: {
        updateNumOfManual: () => {
            document.querySelector(".qty-input").value = ctx.state.numOfManualLottos;
        },
        scanWinningNumbers: () => {
            let allValid = true;
            const winningNumSet = new Set();
            ctx.state.winningNumberInputsToScan.forEach((elm) => {
                const val = elm.value;
                winningNumSet.add(val);
                if (!ctx.util.isValidLottoNumber(val)) {
                    allValid = false;
                }
            });

            if (winningNumSet.size != ctx.state.winningNumberInputsToScan.size) {
                allValid = false;
            }

            if (allValid) {
                document.querySelector(".winning-number-group .alert").classList.add("d-none");
                return;
            }
            document.querySelector(".winning-number-group .alert").classList.remove("d-none");
        }
    },
    templates: { },
    eventHandlers: {
        onAddManualNumberButton: (evt) => {
            const elm = document.createElement("div");
            elm.innerHTML = ctx.templates.manualNumber();
            elm.classList.add("input-group-lottonumber");
            $(".manual-numbers").append(elm);
            ctx.state.numOfManualLottos++;
            ctx.view.updateNumOfManual();
            elm.querySelector("button").addEventListener("click", () => {
                console.log("제거");
                document.querySelector(".manual-numbers").removeChild(elm);
                ctx.state.numOfManualLottos--;
                ctx.view.updateNumOfManual();
            });
        },
        onLottoNumberInput: (evt) => {
            ctx.state.winningNumberInputsToScan.add(evt.target);
            let val = evt.target.value;
            if (val.length > 2) {
                val = val.substring(0, 2);
                evt.target.value = val;
            }
            ctx.view.scanWinningNumbers();
        }
    },
    util: {
        isValidLottoNumber: (val) => {
            const num = Number(val);
            if (num === NaN) {
                return false;
            }
            if (num < 1 || num > 45) {
                return false;
            }
        
            return true;
        }
    }
};
document.addEventListener("DOMContentLoaded", () => {
    ctx.templates["manualNumber"] = Handlebars.compile($("#lottonumber-input-group").html());

    $(".btn-add-manual-number").click(ctx.eventHandlers.onAddManualNumberButton);
    document.querySelectorAll(".lotto-number-input").forEach((v) => {
        v.addEventListener("input", ctx.eventHandlers.onLottoNumberInput);
    });
});
