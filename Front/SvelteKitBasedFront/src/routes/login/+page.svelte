<script>
    let email = '';
    let password = '';

    const handleSubmit = async () => {
        const formData = new FormData();
        formData.append('email', email);
        formData.append('password', password);

        const response = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            // headers: {
            //     'Content-Type' : 'application/x-www-form-urlencoded',
            // },
            body: formData,
            credentials: 'include',
        });

        if (response.ok) {
            alert('Login successful!');
            window.location.href = '/menu'; // 로그인 성공 시 주문 페이지로 이동
        } else if (response.status === 401) {
            alert('Invalid email or password. Please try again.');
        } else {
            alert('Login failed. Please try again later.');
        }
    };
</script>

<style>
    :global(body) {
        font-family: 'Arial', sans-serif;
        background-color: #fef5f3;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 100%;
        max-width: 400px;
        margin: 50px auto;
        padding: 20px;
        background: white;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border: 1px solid #fcd7d1;
    }

    h1 {
        text-align: center;
        color: #ff6347;
        margin-bottom: 20px;
    }

    form {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }

    label {
        font-weight: bold;
        color: #333;
    }

    input[type="email"],
    input[type="password"] {
        padding: 10px;
        font-size: 14px;
        border: 1px solid #fcd7d1;
        border-radius: 5px;
        outline: none;
        transition: border-color 0.3s;
    }

    input[type="email"]:focus,
    input[type="password"]:focus {
        border-color: #ff6347;
    }

    button {
        padding: 12px;
        font-size: 16px;
        font-weight: bold;
        color: white;
        background-color: #ff6347;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    button:hover {
        background-color: #e5533f;
    }

    .note {
        text-align: center;
        color: #666;
        font-size: 14px;
        margin-top: 10px;
    }

    .note a {
        color: #ff6347;
        text-decoration: none;
    }

    .note a:hover {
        text-decoration: underline;
    }
</style>

<div class="container">
    <h1>Login</h1>
    <form on:submit|preventDefault={handleSubmit}>
        <label for="email">Email</label>
        <input type="email" id="email" bind:value={email} placeholder="Enter your email" required>

        <label for="password">Password</label>
        <input type="password" id="password" bind:value={password} placeholder="Enter your password" required>

        <button type="submit">Login</button>
    </form>
    <p class="note">Don't have an account? <a href="/register">Register here</a></p>
</div>
