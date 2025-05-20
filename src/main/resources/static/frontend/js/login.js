document.addEventListener("DOMContentLoaded", function () {
  //alert("Login script loaded"); // optional for testing

  const form = document.getElementById("loginForm");
  if (!form) {
    alert("Form not found!");
    return;
  }

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const data = { username, password };

    fetch("http://localhost:8085/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    })
    .then(async res => {
      if (!res.ok) {
        const text = await res.text();
        throw new Error(text);
      }
      return res.json();
    })
    .then(response => {
      if (response.token) {
        localStorage.setItem("token", response.token);
        alert("Login successful!");
        window.location.href = "/frontend/welcome.html";
        //window.location.href = "/api/welcome";
      } else {
        alert("Login failed: Token missing");
      }
    })
    .catch(err => {
      console.error("Login error:", err);
      alert("Login failed: " + err.message);
    });
  });
});
