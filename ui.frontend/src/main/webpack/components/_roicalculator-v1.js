(function() { 
  class ROICalculator {
    constructor(roiCalculator) {
      this.roiCalculator = roiCalculator;
      this.roiCalculatorForm = this.roiCalculator.querySelector('.roi-calculator__form');
      this.roiCalculatorResult = this.roiCalculator.querySelector('.roi-calculator__result');
      this.employees = this.roiCalculatorForm.querySelector('#roi-calculator__employee--id');
      this.revenue = this.roiCalculatorForm.querySelector('#roi-calculator__revenue--id')
      this.ttr = this.roiCalculatorForm.querySelector('#roi-calculator__ttr--id')
      this.tickets = this.roiCalculatorForm.querySelector('#roi-calculator__tickets--id')
      this.cpt = this.roiCalculatorForm.querySelector('#roi-calculator__cpt--id')

      this.submitButton = this.roiCalculator.querySelector('#roi-calculator--button');
      this.submitButton.addEventListener("click", this.handleroi1Click);
    }

    handleroi1Click = () => {
      this.employeesVal = this.employees.value;
      this.revenueVal = this.revenue.value;
      this.ttrVal = this.ttr.value;
      this.ticketsVal = this.tickets.value;
      this.cptVal = this.cpt.value;

      if (this.validateForm()) {
        this.calculateResolved();
        this.calculateAccelerated();
        this.calculateProductive();
        this.calculateWait();
  
        this.roiCalculatorForm.style.display = "none";
        this.roiCalculatorResult.style.display = "block";
      }
    }

    validateForm = () => {
			var flag = true;

			if(this.employeesVal == '') {
				this.employees.classList.add('roi-calculator__input--invalid');
				flag = false;
			}
			if(this.revenueVal == '') {
				this.revenue.classList.add('roi-calculator__input--invalid');
				flag = false;
			}
			if(this.ttrVal == '') {
				this.ttr.classList.add('roi-calculator__input--invalid');
				flag = false;
			}
			if(this.ticketsVal == '') {
				this.tickets.classList.add('roi-calculator__input--invalid');
				flag = false;
			}
      if(this.cptVal == '') {
				this.cpt.classList.add('roi-calculator__input--invalid');
				flag = false;
			}
			return flag;
		}

    calculateResolved = () => {
      this.resolved = this.ticketsVal * .2;
      this.roiCalculatorResult.querySelector('#roi-calculator__tickets--id').innerHTML = this.resolved;
    }

    calculateAccelerated = () => {
      this.accelerated = this.ticketsVal * .15;
      this.roiCalculatorResult.querySelector('#roi-calculator__accelerated--id').innerHTML = this.accelerated;
    }

    calculateProductive = () => {
      if (this.employees != 0) {
        this.productive = 0.05 * ((this.resolved * this.ttrVal) + (this.accelerated * this.ttrVal * 0.2)) / this.employeesVal;
        this.roiCalculatorResult.querySelector('#roi-calculator__productive--id').innerHTML = this.productive;
      }
    }

    calculateWait = () => {
      if (this.employees != 0) {
        this.wait = ((this.resolved * this.ttrVal) + (this.accelerated * this.ttrVal * 0.2)) / this.employeesVal;
        this.roiCalculatorResult.querySelector('#roi-calculator__wait--id').innerHTML = this.wait;
      }
    }
  }

  const roiCalculators = document.querySelectorAll('.roi-calculator__container');
  
  roiCalculators.forEach(roiCalculator => {
        new ROICalculator(roiCalculator);
    });
  
}());
