document.getElementById("loginForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const errorMsg = document.getElementById("errorMsg");

    errorMsg.textContent = "";

    // Check if fields are empty
    if (!email || !password) {
        errorMsg.textContent = "Please enter both email and password.";
        return;
    }

    // Email format check
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        errorMsg.textContent = "Please enter a valid email address.";
        return;
    }

    // Success (you can replace this with a real login request)
    alert("Login successful!");
});
