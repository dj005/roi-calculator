$(document).ready(function() {
	var calculateROIUrl = ('test.roicalc.html');

	function calculateRoi(userId, investment, revenue, expenses) {
		$.ajax({
			type: 'POST',
			url: calculateROIUrl,
			data: {
				'userId': userId,
				'investment': investment,
				'revenue': revenue,
				'expenses': expenses
			},
			dataType: 'text',
			success: function(result) {
				$('#result-roi').html(result);
				$("#form").hide();
				$("#form-results").show();
			}
		});
	}

	$(document).on('click', '#roi2-submit-button', function() {
		var userId = $('#userId').val();
		var investment = $('#investment').val();
		var revenue = $('#revenue').val();
		var expenses = $('#expenses').val();

		if (userId == "") {
			alert("Please enter User Id");
			return;
		}
		if (investment == "") {
			alert("Please enter investment");
			return;
		}
		if (revenue == "") {
			alert("Please enter revenue");
			return;
		}
		if (expenses == "") {
			alert("Please enter new value for expenses");
			return;
		}
		calculateRoi(userId, investment, revenue, expenses);
	});

});
