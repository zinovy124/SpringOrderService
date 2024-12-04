<script>
    let menuItems = [
        { id: 1, name: 'Pizza', price: 12000 },
        { id: 2, name: 'Burger', price: 8000 },
        { id: 3, name: 'Pasta', price: 10000 },
    ];

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
    <h1>Menu</h1>
    {#each menuItems as menu}
        <div class="menu-item">
            <div>
                <h3>{menu.name}</h3>
                <p>Price: ₩{menu.price}</p>
            </div>
            <button on:click={() => orderMenu(menu.id)}>Order</button>
        </div>
    {/each}
</div>
