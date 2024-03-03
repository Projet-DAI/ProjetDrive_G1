 // Function to update the countdown
        function updateCountdown() {
            // Get the current time
            var now = new Date();

            // Set the target time to 6:00 PM of the current day
            var target = new Date();
            target.setHours(18, 0, 0, 0);

            // Calculate the time difference
            var difference = target - now;

            // Calculate hours, minutes, and seconds
            var hours = Math.floor(difference / (1000 * 60 * 60));
            var minutes = Math.floor((difference % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((difference % (1000 * 60)) / 1000);

            // Display the countdown
            document.getElementById("countdown").innerHTML = hours + "H " + minutes + "M " + seconds + "S";

            // Update every second
            setTimeout(updateCountdown, 1000);
        }

        // Call the function when the page loads
        window.onload = updateCountdown;