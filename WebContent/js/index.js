$(document).ready(function() {
	$("#lel").click(function() {
		$("#myModal").modal();
	});
	$("#updPrdKey").click(function() {
		$("#modalUpdatePrdOnStock").modal();
	});
	$("#sell").click(function() {
		$("#venteModal").modal();
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
			$form.trigger("reset");
			$('#allProductShow').html(response);
		} else {
			console.log('no response');
		}
	});

	event.preventDefault(); // Important! Prevents submitting the form.
});

//// get All product ajax request from modal
//$(document).on("submit", "#getAllPrdsModal", function(event) {
//	var $form = $(this);
//	$.post($form.attr("action"), $form.serialize(), function(response) {
//		if (response != null) {
//			console.log('im logging here modal vente');
//			$form.trigger("reset");
//			$('#allProductShowModal').html(response);
//		} else {
//			console.log('no response');
//		}
//	});
//	event.preventDefault(); // Important! Prevents submitting the form.
//});
// get All product ajax request from modal
$(document).on("submit", "#getAllPrdsModal", function(event) {
	var $form = $(this);
	$.post($form.attr("action"), $form.serialize(), function(response) {
		if (response != null) {
			console.log('im logging here modal vente');
			$form.trigger("reset");
			$('#allProductShowModal').html(response);
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

// test
$("#venteID").click(function() {
	console.log('ffffffffffffffffffffffffffffffffffffffff');
});