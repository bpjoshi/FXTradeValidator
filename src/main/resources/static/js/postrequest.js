/**
 *
 * @file JS file to handle POST request for trade information and display validation results
 * @author bpjoshi(Bhagwati Prasad) <write2bpj@gmail.com>
 * @version 0.1
 */
$(document).ready(
		function() {
			//On Submit of Form
			$("#tradeInformationForm").submit(function(event) {
				// Prevent form from submitting via browser
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {
				var tradeInfo = $("#tradeInfo").val();
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/trades",
					data : tradeInfo,
					dataType : 'json',
					success : function(result) {
						//TO DO
					},
					error : function(e) {
						//TO DO
					}
				});
			}
		})