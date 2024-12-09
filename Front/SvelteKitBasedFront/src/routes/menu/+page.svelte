<script>
    import { onMount } from 'svelte';

    // 로그인한 사용자 정보
    let user = null;

    // 메뉴 아이템 (예제)
    let menuItems = [
        { id: 1, name: 'Pizza', price: 12000 },
        { id: 2, name: 'Burger', price: 8000 },
        { id: 3, name: 'Pasta', price: 10000 },
    ];

    // 로그인 여부 확인 및 사용자 정보 가져오기
    onMount(async () => {
        const response = await fetch('http://localhost:8080/api/user', {
            method: 'GET',
            credentials: 'include', // 세션 또는 쿠키 정보를 포함
        });

        if (response.ok) {
            user = await response.json(); // 사용자 정보
            console.log("Session Checked: ", user);
        } else if (response.status === 403) {
            alert('Access denied. Please log in.');
            window.location.href = '/login';
        } else {
            alert('Failed to fetch user info');
        }
    });

    // 메뉴 주문 처리
    const orderMenu = (menuId) => {
        window.location.href = `/order/${menuId}`;
    };
</script>

<style>
    .container {
        max-width: 800px;
        margin: 50px auto;
        padding: 20px;
        font-family: 'Arial', sans-serif;
    }

    .welcome {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }

    .menu-item {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
        margin-bottom: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    button {
        background-color: #ff6347;
        color: white;
        border: none;
        padding: 8px 12px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #e5533f;
    }
</style>

<div class="container">
    {#if user}
        <div class="welcome">
            <h1>Welcome, {user.name}!</h1>
            <p>Here are the delicious items on our menu:</p>
        </div>
        {#each menuItems as menu}
            <div class="menu-item">
                <div>
                    <h3>{menu.name}</h3>
                    <p>Price: ₩{menu.price}</p>
                </div>
                <button on:click={() => orderMenu(menu.id)}>Order</button>
            </div>
        {/each}
    {:else}
        <p>Loading...</p>
    {/if}
</div>
