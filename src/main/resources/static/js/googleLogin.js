async function handleToken(googleUser) {
	const responses = await Promise.all([
		fetch(`http://localhost:8080/login/google_authen?googleJwt=${googleUser.credential}`,{
			method: "POST",
		}), 
		fetch(`http://localhost:8080/login/is_user_new?username=abc`)
	]);
	
	const isValid = await responses[0].text();
	const isNew = await responses[1].text();
	
	if (isValid == "Valid") {
		if (isNew == "true") {
			window.location.href = "http://localhost:8080/register";
		} else { 
			window.location.href = "http://localhost:8080/home";
		}
	}
}