(function() {

	class ROIHistory {
		constructor(roiHistory) {
			this.roiHistory = roiHistory;
			this.userId = this.roiHistory.querySelector('#roi-history__userId--id');
			this.roiHistoryForm = this.roiHistory.querySelector('.roi-history__form');
			this.roiHistoryResult = this.roiHistory.querySelector('.roi-history__result');
			this.submitButton = this.roiHistory.querySelector('#roi-history--button');
			this.submitButton.addEventListener('click', this.handleSubmit);
		}

		handleSubmit = () => {
			this.userIdVal = this.userId.value;

			if (this.validateForm()) {
				this.getRoiHistory();
				this.roiHistoryForm.style.display = 'none';
				this.roiHistoryResult.style.display = 'block';
			}
		}

		validateForm = () => {
			var flag = true;

			if(this.userIdVal == '') {
				this.userId.classList.add('roi-history__input--invalid');
				flag = false;
			}
			return flag;
		}
		
		getRoiHistory = () => {
			const roiHistoryUrl = ('test.roihistory.html');

			$.ajax({
				type: 'GET',
				url: roiHistoryUrl,
				data: {
					'userId': this.userIdVal,
				},
				dataType: 'json',
				success: function(result) {
					if (result.length > 0) {
						var headerRow = '<div class="roi-history__table-header">UserId</div>' +
							'<div class="roi-history__table-header">Investment</div>' +
							'<div class="roi-history__table-header">Revenue</div>' +
							'<div class="roi-history__table-header">Expenses</div>' +
							'<div class="roi-history__table-header">Roi</div>';
							$('.roi-history__table').append(headerRow);
						$.each(result, function(key, value) {
							var $row;
							$row = '<div class="roi-history__table-data">' + value.userId + '</div>'+
								'<div class="roi-history__table-data">' + value.investment + '</div>' + 
								'<div class="roi-history__table-data">' + value.revenue + '</div>' + 
								'<div class="roi-history__table-data">' + value.expenses + '</div>'+
								'<div class="roi-history__table-data">' + value.roi + '</div>';
				
							$('.roi-history__table').append($row);
						});
					} else {
						$('.roi-history__table').append('<div>No result found</div>');
					}
				},
				error: function(xhr, status, error) {
					$('.roi-history__table').append('<div>No result found</div>');
				}
			});
		}
	}

	const roiHistoryList = document.querySelectorAll('.roi-history__container');

	roiHistoryList.forEach(roiHistory => {
		new ROIHistory(roiHistory);
	})
}());
