(function() { 
	class ROIV2Calculator {
		constructor(roiV2Calculator) {
			this.roiV2Calculator = roiV2Calculator;
			this.roiV2CalculatorForm = this.roiV2Calculator.querySelector('.roi-calculator-v2__form');
			this.roiV2CalculatorResult = this.roiV2Calculator.querySelector('.roi-calculator-v2__result');
			this.userId = this.roiV2CalculatorForm.querySelector('#roi-calculator-v2__userId--id');
			this.investment = this.roiV2CalculatorForm.querySelector('#roi-calculator-v2__investment--id');
			this.revenue = this.roiV2CalculatorForm.querySelector('#roi-calculator-v2__revenue--id');
			this.expenses = this.roiV2CalculatorForm.querySelector('#roi-calculator-v2__expenses--id');
			this.submitButton = this.roiV2CalculatorForm.querySelector('#roi-calculator-v2--button');
			this.historyButton = this.roiV2CalculatorResult.querySelector('#roi-calculator-v2--history-btn');
			this.submitButton.addEventListener("click", this.handleroi2Click);
			this.historyButton.addEventListner("click", this.historyPage);
		}

		handleroi2Click = () => {
			this.userIdVal = this.userId.value;
			this.investmentVal = this.investment.value;
			this.revenueVal = this.revenue.value;
			this.expensesVal = this.expenses.value;

			if (this.validateForm()) {
				this.calculateRoi();
				this.roiV2CalculatorForm.style.display = 'none';
				this.roiV2CalculatorResult.style.display = 'block';
			}
		}

		calculateRoi = () => {
			const url = ('test.roicalc.html');
			$.ajax({
				type: 'POST',
				url: url,
				data: {
					'userId' : this.userIdVal, 
					'investment': this.investmentVal, 
					'revenue': this.revenueVal, 
					'expenses': this.expensesVal
				},
				dataType: 'text',
				success: function(result) {
					$('#roi-calculator-v2__roi--id').html(result);
				}
			});
		}

		validateForm = () => {
			var flag = true;

			if(this.userIdVal == '') {
				this.userId.classList.add('roi-calculator-v2__input--invalid');
				flag = false;
			}
			if(this.investmentVal == '') {
				this.investment.classList.add('roi-calculator-v2__input--invalid');
				flag = false;
			}
			if(this.revenueVal == '') {
				this.revenue.classList.add('roi-calculator-v2__input--invalid');
				flag = false;
			}
			if(this.expensesVal == '') {
				this.expenses.classList.add('roi-calculator-v2__input--invalid');
				flag = false;
			}
			return flag;
		}

		historyPage = () => {
			const historyPageUrl = "/content/demosite/us/en/roihistory.html?userId="+ this.userIdVal;
			window.location.href = historyPageUrl;
		}

	}

	const roiV2Calculators = document.querySelectorAll('.roi-calculator-v2__container');

	roiV2Calculators.forEach(roiV2Calculator => {
		new ROIV2Calculator(roiV2Calculator);
	})
}());
