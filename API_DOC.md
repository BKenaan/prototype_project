***Backend API Documentation***

This README provides all the information needed for the frontend developer to interact with the backend API for the shared grocery cart prototype.

*API Base URL*

The backend server is running at:
http://localhost:5400

**Endpoints Overview**

1. Create a Shared Cart
Endpoint: POST /api/helpers/create-cart
Request Body:
{
    "hostId": "user123",
    "participants": ["user456", "user789"],
    "deadline": "2024-12-31T23:59:59"
}

Response:
Returns a unique cart ID.

2. Add an Item to a Cart
Endpoint: POST /api/helpers/add-item

Steps:
Use GET /api/helpers/fetch-products?query={query} to search for available products.
Choose a product and pass its details in the request.
Request Body:

{
    "cartId": "cart1",
    "userId": "user123",
    "item": {
        "productId": "prod123",
        "name": "Apple",
        "quantity": 2,
        "price": 1.5
    }
}
3. View Cart Details
Endpoint: GET /api/helpers/view-cart/{cartId}
Response:


    {
        "productId": "prod123",
        "name": "Apple",
        "userId": "user123",
        "quantity": 2,
        "price": 1.5
    }

4. Calculate Total Cost of a Cart
Endpoint: GET /api/helpers/calculate-total/{cartId}
Response:
Returns a double value representing the total cost.

5. Get Individual Costs for a Cart
Endpoint: GET /api/helpers/individual-costs/{cartId}
Response:

{
    "user123": 10.0,
    "user456": 5.0
}

6. Fetch Products from External API
Endpoint: GET /api/helpers/fetch-products?query={query}
Response:

[
    {
        "productId": "3017620425035",
        "name": "Nutella",
        "price": 4.5,
        "stockQuantity": 50
    }
]

7. Finalize an Order
Endpoint: POST /api/helpers/finalize-order/{cartId}

8. Get Order Status
Endpoint: GET /api/helpers/order-status/{cartId}
Response:
Returns a String such as "Open" or "Finalized".

9. Validate Cart Contents
Endpoint: GET /api/helpers/validate-cart/{cartId}
Response:
Returns true or false.

**Frontend Setup Notes**

**CORS Configuration**
CORS is configured to allow all origins (*) for development. The frontend can make requests without any restrictions.
