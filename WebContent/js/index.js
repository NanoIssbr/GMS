$(document).ready(function() {

	$('.his').click(function() {
		$('.formse').slideToggle(1000);
	});
	$("#lel").click(function() {
		$("#myModal").modal();
	});
	$("#dtel").click(function() {
		$("#myModalse").modal();
	});
	$("#adm1").click(function() {
		$("#modalAbonnement").modal();
	});
	$('#allPrdBtn').click(function() {
		$('#allPrdMdl').modal();
	});
	$('#adm').click(function() {
		$('#adm1').stop().slideToggle(1000);
		$('#adm1').click(function() {
			$('#myModalse').modal();
		});
		$('#nr').click(function() {
		});

	});

	// new Product ajax request
	$(document).on("submit", "#newPrd", function(event) {
		var $form = $(this);

		$.post($form.attr("action"), $form.serialize(), function(response) {
			if (response != null) {
				console.log('im logging herer');
				$form.trigger("reset");
				$('#infoMessage').html(response);
				$('.alert').show();
				setTimeout(function() {
					$('.alert').hide();
				}, 20000);
			} else {
				console.log('no response');
			}
		});

		event.preventDefault(); // Important! Prevents submitting the form.
	});

	// get All product ajax request
	$(document).on("submit", "#getAllPrds", function(event) {
		var $form = $(this);
		$.post($form.attr("action"), $form.serialize(), function(response) {
			if (response != null) {
				console.log('im logging herer');
				//$form.trigger("reset");
				$('#allProductShow').html(response);
			} else {
				console.log('no response');
			}
		});

		event.preventDefault(); // Important! Prevents submitting the form.
	});

	// new Abonnement ajax Request
	$(document).on("submit", "#newAbo", function(event) {
		var $form = $(this);

		$.post($form.attr("action"), $form.serialize(), function(response) {
			// $('#messageNP').html("if you see this, so the whole shit is
			// working");
			if (response != null) {
				console.log('im logging herer');
				$form.trigger("reset");
				$('#infoMessage').html(response);
				$('.alert').show();
				setTimeout(function() {
					$('.alert').hide();
				}, 20000);
			} else {
				console.log('no response');
			}
		});

		event.preventDefault(); // Important! Prevents submitting the form.
	});
});