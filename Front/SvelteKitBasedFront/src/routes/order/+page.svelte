<script>
  import { onMount } from "svelte";

    let orders = [];

    const fetchOrders = async () => {
        const response = await fetch('http://localhost:8080/api/order', {
            method: 'GET',
            credentials: 'include',
        })

        if (response.ok) {
            orders = await response.json();
        } else if (response.status === 401) {
            alert('You must be logged in to view your orders');
        } else {
            alert('Failed to fetch orders');
        }
    };

    onMount(() => {
        fetchOrders();
    });

    const deleteOrder = async (orderId) => {
        const response = await fetch(`http://localhost:8080/api/order/${orderId}`, { method: 'DELETE' });
        if (response.ok) {
            alert('Order deleted successfully!');
            orders = orders.filter((order) => order.id !== orderId);
        } else {
            alert('Failed to delete order.');
        }
    };

    const editOrder = (orderId) => {
        window.location.href = `/edit-order/${orderId}`;
    };
</script>

<style>
    .container {
        max-width: 800px;
        margin: 50px auto;
        padding: 20px;
        font-family: 'Arial', sans-serif;
    }

    .order-item {
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
        margin-left: 10px;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #e5533f;
    }
</style>

<div class="container">
    <h1>Your Orders</h1>
    {#each orders as order}
        <div class="order-item">
            <div>
                <h3>{order.menuName}</h3>
                <p>Quantity: {order.quantity}</p>
                <p>Total Price: ₩{order.totalPrice}</p>
            </div>
            <div>
                <button on:click={() => editOrder(order.id)}>Edit</button>
                <button on:click={() => deleteOrder(order.id)}>Delete</button>
            </div>
        </div>
    {/each}
</div>
