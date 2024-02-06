// Add event listener to the Confirm button
document.getElementById("confirmButton").addEventListener("click", function (event) {
    // Check if all required fields are filled in both forms
    if (!document.getElementById("passengerForm").checkValidity() || !document.getElementById("cardForm").checkValidity()) {
        // Prevent form submission if required fields are not filled
        event.preventDefault();
        // Scroll to the top of the page to show validation messages
        window.scrollTo(0, 0);
        // Focus on the first invalid input field in the passenger form
        findFirstInvalidInput(document.getElementById("passengerForm"));
        // Focus on the first invalid input field in the card form if the passenger form is valid
        if (document.getElementById("passengerForm").checkValidity()) {
            findFirstInvalidInput(document.getElementById("cardForm"));
        }
        // Apply styles to invalid input fields
        applyInvalidStyles();
    }
});

// Function to find the first invalid input field
function findFirstInvalidInput(form) {
    // Get all input fields in the form
    var inputs = form.querySelectorAll("input");
    // Loop through each input field
    for (var i = 0; i < inputs.length; i++) {
        // Check if the input field is invalid
        if (!inputs[i].checkValidity()) {
            // Set focus to the first invalid input field
            inputs[i].focus();
            // Exit the function once the first invalid input field is found
            return;
        }
    }
}

// Function to apply invalid styles to input fields
function applyInvalidStyles() {
    // Get all input fields
    var inputs = document.querySelectorAll('input[type="text"], input[type="email"]');
    // Loop through each input field
    inputs.forEach(function (input) {
        // Check if the input is invalid
        if (!input.checkValidity()) {
            // Apply red border color
            input.style.borderColor = "red";
        } else {
            // Remove red border color if input is valid
            input.style.borderColor = "";
        }
    });
}