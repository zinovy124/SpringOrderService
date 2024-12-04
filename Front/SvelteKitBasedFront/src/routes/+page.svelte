<script>
    // 여기에 추가적으로 페이지에서 필요한 스크립트를 작성할 수 있습니다.
</script>

<style>
    /* 기본 설정 */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    /* 그냥 body, html 을 쓰면 적용 안된다. */
    :global(body), :global(html) {
        margin: 0;
        padding: 0;
        height: 100%;
        overflow: hidden;
    }

    .container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background: linear-gradient(135deg, #ff6347, #ffd700);
        color: white;
        font-family: 'Arial', sans-serif;
        text-align: center;
    }

    h1 {
        font-size: 3rem;
        margin: 0 0 1rem;
        text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
    }

    p {
        font-size: 1.2rem;
        max-width: 600px;
        margin: 0 0 2rem;
        line-height: 1.5;
    }

    .buttons {
        display: flex;
        gap: 20px;
    }

    a {
        display: inline-block;
        padding: 15px 30px;
        font-size: 1.2rem;
        color: #ff6347;
        background: white;
        text-decoration: none;
        border-radius: 8px;
        transition: transform 0.2s ease, background 0.2s ease, color 0.2s ease;
        font-weight: bold;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    a:hover {
        transform: scale(1.1);
        background: #f5f5f5;
        color: #ff4500;
    }

    .footer {
        position: absolute;
        bottom: 20px;
        text-align: center;
        width: 100%;
        font-size: 0.9rem;
        color: rgba(255, 255, 255, 0.8);
    }
</style>

<div class="container">
    <h1>Welcome to Foodie Paradise!</h1>
    <p>
        Indulge in the best food ordering experience with our service. Quick, easy, and delicious at your fingertips!
    </p>
    <div class="buttons">
        <a href="/login">Login</a>
        <a href="/register">Sign Up</a>
    </div>
    <div class="footer">
        Made with ❤️ by Foodie Paradise
    </div>
</div>
