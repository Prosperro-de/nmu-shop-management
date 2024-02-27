document.getElementById('getAllOrders').addEventListener('click', function() {
    fetch('http://java-http-demo.eu-central-1.elasticbeanstalk.com/orders')
        .then(response => response.json())
        .then(data => displayOrders(data))
        .catch(error => console.error('Error:', error));
});

document.getElementById('getAllCustomers').addEventListener('click', function() {
    fetch('http://java-http-demo.eu-central-1.elasticbeanstalk.com/customers')
        .then(response => response.json())
        .then(data => displayCustomers(data))
        .catch(error => console.error('Error:', error));
});

document.getElementById('getAllProducts').addEventListener('click', function() {
    fetch('http://java-http-demo.eu-central-1.elasticbeanstalk.com/products')
        .then(response => response.json())
        .then(data => displayProducts(data))
        .catch(error => console.error('Error:', error));
});

document.getElementById('getCustomReport').addEventListener('click', function() {
    addCustomReportButtons();
});


function displayOrders(orders) {
    let htmlContent = '<table class="table table-striped table-bordered"><thead><tr><th>ID</th><th>Ordered At</th><th>Delivered At</th><th>Status</th><th>Customer</th><th>Items</th></tr></thead><tbody>';

    for (const order of orders) {
        htmlContent += `
            <tr>
                <td>${order.id}</td>
                <td>${order.orderedAt}</td>
                <td>${order.deliveredAt}</td>
                <td>${order.orderStatus}</td>
                <td>${order.customer.name}, ${order.customer.city}</td>
                <td>${order.persistedOrderItems.map(item => `${item.product.name} (Qty: ${item.quantity})`).join(', ')}</td>
            </tr>
        `;
    }

    htmlContent += '</tbody></table>';
    document.getElementById('dataDisplay').innerHTML = htmlContent;
}

function displayCustomers(customers) {
    let htmlContent = '<table class="table table-striped table-bordered"><thead><tr><th>ID</th><th>Name</th><th>Address</th><th>City</th><th>PostalCode</th><th>Country</th><th>Phone</th></tr></thead><tbody>';

    for (const customer of customers) {
        htmlContent += `
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.address}</td>
                <td>${customer.city}</td>
                <td>${customer.postalCode}</td>
                <td>${customer.country}</td>
                <td>${customer.phone}</td>
            </tr>
        `;
    }

    htmlContent += '</tbody></table>';
    document.getElementById('dataDisplay').innerHTML = htmlContent;
}

function displayProducts(products) {
    let htmlContent = '<table class="table table-striped table-bordered"><thead><tr><th>ID</th><th>Name</th><th>Price for one unit</th><th>Quantity in stock</th><th>Supplier</th><th>Category</th></tr></thead><tbody>';

    for (const product of products) {
        htmlContent += `
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.unitPrice}</td>
                <td>${product.quantityInStock}</td>
                <td>${product.supplier ? product.supplier.supplierName : 'N/A'}</td>
                <td>${product.category ? product.category.categoryName : 'N/A'}</td>
    
            </tr>
        `;
    }
    htmlContent += '</tbody></table>';
    document.getElementById('dataDisplay').innerHTML = htmlContent;
}

function addCustomReportButtons() {
    let htmlContent = `
        <div class="button-row d-flex justify-content-center mt-3">
            <button id="findOrdersBetweenDates" class="btn btn-primary m-2">Find Order Between Dates</button>
            <button id="findOrdersForCustomer" class="btn btn-primary m-2">Find Order for Specific Customer</button>
            <button id="getMoneyIncome" class="btn btn-primary m-2">Show revenue between dates</button>
        </div>
        <div id="dateInputContainer" class="mt-3 d-flex justify-content-center" style="display: none;">
            <!-- Date inputs and Find button will be added here -->
        </div>`;

    document.getElementById('dataDisplay').innerHTML = htmlContent;

    document.getElementById('findOrdersBetweenDates').addEventListener('click', function() {
        showDateInputs();
    });
    document.getElementById('findOrdersForCustomer').addEventListener('click', function() {
        showCustomerDropdown();
    });

    document.getElementById('getMoneyIncome').addEventListener('click', function() {
        showRevenueDateInputs();
    });

}

function showDateInputs() {
    let dateInputHtml = `
        <input type="date" id="startDate" class="m-2">
        <input type="date" id="endDate" class="m-2">
        <button id="findDateRangeOrders" class="btn btn-success m-2">Find</button>
    `;

    const dateInputContainer = document.getElementById('dateInputContainer');
    dateInputContainer.innerHTML = dateInputHtml;
    dateInputContainer.style.display = 'flex';

    document.getElementById('findDateRangeOrders').addEventListener('click', fetchOrdersBetweenDates);
}

function fetchOrdersBetweenDates() {
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;

    if (startDate && endDate) {
        const url = `http://java-http-demo.eu-central-1.elasticbeanstalk.com/orders/between-dates?startDate=${startDate}&endDate=${endDate}`;
        fetch(url)
            .then(response => response.json())
            .then(data => displayOrders(data)) // Assuming displayOrders function exists
            .catch(error => console.error('Error:', error));
    } else {
        alert("Please select both start and end dates.");
    }
}

function showCustomerDropdown() {
    fetch('http://java-http-demo.eu-central-1.elasticbeanstalk.com/customers')
        .then(response => response.json())
        .then(customers => {
            let dropdownHtml = '<select id="customerSelect" class="m-2">';
            customers.forEach(customer => {
                dropdownHtml += `<option value="${customer.id}">${customer.name}</option>`;
            });
            dropdownHtml += '</select>';
            dropdownHtml += '<button id="findOrdersForSpecificCustomer" class="btn btn-success m-2">Find</button>';

            document.getElementById('dataDisplay').innerHTML = dropdownHtml;

            document.getElementById('findOrdersForSpecificCustomer').addEventListener('click', function() {
                const selectedCustomerId = document.getElementById('customerSelect').value;
                fetchOrdersForCustomer(selectedCustomerId);
            });
        })
        .catch(error => console.error('Error:', error));
}


function fetchOrdersForCustomer(customerId) {
    const url = `http://java-http-demo.eu-central-1.elasticbeanstalk.com/orders/customer?id=${customerId}`;
    fetch(url)
        .then(response => response.json())
        .then(data => displayOrders(data)) // Assuming displayOrders function exists
        .catch(error => console.error('Error:', error));
}

function showRevenueDateInputs() {
    let htmlContent = `
        <div class="date-inputs mt-3 d-flex justify-content-center">
            <input type="date" id="revenueStartDate" class="m-2">
            <input type="date" id="revenueEndDate" class="m-2">
            <button id="findRevenue" class="btn btn-success m-2">Find</button>
        </div>`;

    document.getElementById('dataDisplay').innerHTML = htmlContent;

    document.getElementById('findRevenue').addEventListener('click', fetchRevenueBetweenDates);
}

function fetchRevenueBetweenDates() {
    const startDate = document.getElementById('revenueStartDate').value;
    const endDate = document.getElementById('revenueEndDate').value;

    if (startDate && endDate) {
        const url = `http://java-http-demo.eu-central-1.elasticbeanstalk.com/revenue/between-dates?startDate=${startDate}&endDate=${endDate}`;
        fetch(url)
            .then(response => response.json())
            .then(revenue => displayRevenue(startDate, endDate, revenue))
            .catch(error => console.error('Error:', error));
    } else {
        alert("Please select both start and end dates.");
    }
}

function displayRevenue(startDate, endDate, revenue) {
    let htmlContent = `
        <table class="table table-striped table-bordered">
            <thead>
                <tr><th>Start Date</th><th>End Date</th><th>Revenue</th></tr>
            </thead>
            <tbody>
                <tr>
                    <td>${startDate}</td>
                    <td>${endDate}</td>
                    <td>$ ${revenue.toFixed(2)}</td>
                </tr>
            </tbody>
        </table>`;

    document.getElementById('dataDisplay').innerHTML = htmlContent;
}
