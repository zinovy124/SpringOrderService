<script>
  import { derived, writable } from 'svelte/store';

    /**
     * @type {{ data: import('./$types').PageData }}
     */
    let { data } = $props();
    // export let params;
    let orderId = Math.floor(Math.random() * 10000);
    let userId = data.userId;
    let menuId = data.menuId;
    let date = new Date();
    let dateString = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}T${String(date.getHours()).padStart(2, '0')}:${String(date.getMinutes()).padStart(2, '0')}`;

    let quantity = writable(1);
    let totalPrice = derived(quantity, $quantity => $quantity * 10000);

    const submitOrder = async () => {
        const response = await fetch(`http://localhost:8080/api/order`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include',
            body: JSON.stringify({
                id: orderId,
                userEmail: userId,
                menuId: parseInt(menuId),
                quantity,
                priceAtOrder: $totalPrice,
                orderDate: dateString,
            }),
        });

        if (response.ok) {
            alert('Order placed successfully!');
            window.location.href = '/order';
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
    <p>Total Price: ₩{$totalPrice}</p>
    <button on:click={submitOrder}>Place Order</button>
</div>
