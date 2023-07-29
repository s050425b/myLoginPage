const loginForm = document.querySelector('form');
const loginBtn = document.querySelector('#login-btn');

loginForm.addEventListener('submit', async (event) => {
	event.preventDefault();

	const username = loginForm.username.value;
	const password = loginForm.password.value;

	// TODO: Validate the username and password
	try {
		const response = await checkIsValid(username, password);
		window.location.href = 'http://localhost:8080/home';
	} catch (error) {
		alert("Incorrect username/password");
	}

});

function checkIsValid(username, password) {
	return new Promise((resolve, reject) =>
		fetch("http://localhost:8080/login/normal_authen", {
			method: "POST",
			body: JSON.stringify({
				username: username,
				password: password
			}),
			headers: {
				"Content-Type": "application/json",
				// 'Content-Type': 'application/x-www-form-urlencoded',
			},
		})
			.then(response => {
				if (response.ok) {
					resolve("Authorized");
				} else {
					reject("Unauthorized")
				}
			})

	);
} 
