<script>
    let email = '';
    let password = '';
    let name = '';
    let profileImage = null;

    const handleFileChange = (event) => {
        profileImage = event.target.files[0];
    };

    const handleSubmit = async () => {
        const formData = new FormData();
        formData.append('email', email);
        formData.append('password', password);
        formData.append('name', name);
        formData.append('profileImage', profileImage);

        const response = await fetch('/api/register', {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            alert('Registration successful!');
        } else {
            alert('Failed to register. Please try again.');
        }
    };

</script>

<style>
    body {
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

    input[type="text"],
    input[type="email"],
    input[type="password"],
    input[type="file"] {
        padding: 10px;
        font-size: 14px;
        border: 1px solid #fcd7d1;
        border-radius: 5px;
        outline: none;
        transition: border-color 0.3s;
    }

    input[type="text"]:focus,
    input[type="email"]:focus,
    input[type="password"]:focus,
    input[type="file"]:focus {
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
    <h1>Register</h1>
    <form on:submit|preventDefault={handleSubmit}>
        <label for="email">Email</label>
        <input type="email" id="email" bind:value={email} placeholder="Enter your email" required>

        <label for="password">Password</label>
        <input type="password" id="password" bind:value={password} placeholder="Enter your password" required>

        <label for="name">Name</label>
        <input type="text" id="name" bind:value={name} placeholder="Enter your name" required>

        <label for="profileImage">Profile Image</label>
        <input type="file" id="profileImage" accept="image/*" on:change={handleFileChange}>

        <button type="submit">Register</button>
    </form>
    <p class="note">Already have an account? <a href="/login">Login here</a></p>
</div>
