<script>
    export let params;
    let menuId = params.menuId;

    let quantity = 1;
    let totalPrice = 0;

    const submitOrder = async () => {
        const response = await fetch(`/api/orders`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                menuId: parseInt(menuId),
                quantity,
                priceAtOrder: totalPrice,
            }),
        });

        if (response.ok) {
            alert('Order placed successfully!');
            window.location.href = '/orders';
        } else {
            alert('Failed to place order. Please try again.');
        }
    };
</script>

<style>
    .container {
        max-width: 400px;
        margin: 50px auto;
        padding: 20px;
        font-family: 'Arial', sans-serif;
        text-align: center;
    }

    input {
        width: 60px;
        text-align: center;
    }

    button {
        background-color: #ff6347;
        color: white;
        border: none;
        padding: 10px 15px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #e5533f;
    }
</style>

<div class="container">
    <h1>Order</h1>
    <p>Menu ID: {menuId}</p>
    <label for="quantity">Quantity: </label>
    <input type="number" id="quantity" bind:value={quantity} min="1" />
    <p>Total Price: ₩{quantity * 10000}</p>
    <button on:click={submitOrder}>Place Order</button>
</div>
