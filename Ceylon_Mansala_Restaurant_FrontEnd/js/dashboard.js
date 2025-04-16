function validateSearch() {
    const input = document.getElementById("searchInput").value.trim();
    if (input === "") {
        alert("Please enter a keyword to search.");
        return;
    }

    // You can implement actual search logic here
    alert("Searching for: " + input);
}
