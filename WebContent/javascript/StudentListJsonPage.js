/**
 * 
 */

 $(document).on("click", "#button", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
	$.get("studentListJsonService", function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
		$("#loading").text(responseText); // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
	});
});
