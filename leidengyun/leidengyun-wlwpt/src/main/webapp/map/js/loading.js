function showLoading(show) {

	if (show) {
		document.getElementById("over").style.display = "block";
		document.getElementById("layout").style.display = "block";
	} else {
		document.getElementById("over").style.display = "none";
		document.getElementById("layout").style.display = "none";
	}
}