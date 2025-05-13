$(document).ready(function() {
	var roiHistoryUrl = ('test.roihistory.html');

	function getRoiHistory(userId) {
		$.ajax({
			type: 'GET',
			url: roiHistoryUrl,
			data: {
				'userId': userId,
			},
			dataType: 'json',
			success: function(result) {
				$("#form").hide();
				$("#form-results").show();

				if (result.length > 0) {
					$.each(result, function(key, value) {
						var $row;
							$row = "<div>" + value.userId + "</div><div>" + value.investment + "</div><div>" + value.revenue + "</div><div>" + value.expenses + "</div><div>" + value.roi + "</div>";

						$('#form-results-table-row').append($row);
					});
				}
			}
		});
	}

	$(document).on('click', '#roihistory-submit-button', function() {
		var userId = $('#userId').val();

		if (userId == "") {
			alert("Please enter User Id");
			return;
		}

		getRoiHistory(userId);
	});

});
