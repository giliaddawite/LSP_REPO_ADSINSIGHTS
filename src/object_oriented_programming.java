class Customer {
    constructor(name, address, telephone, licenseNumber) {
        this.customerID = this.generateCustomerID();
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.licenseNumber = licenseNumber;
        this.orders = [];

        generateCustomerID() {
            return 'CUST-' + Date.now() + '_' + Math.floor(Math.random() * 1000000);
        }


        placeOrder(order {
            this.orders.push(order);
            console.log('Order $order.orderNumber} placed by ${this.name}');
        })

        viewOrderHistory() {
            console.log('Order History for ${this.name}:');
            this.orders.forEach(order => {
                console.log('- Order #${order.orderNumber} on ${order.orderDate}');
            });
        }

        updateContactInfo(newAddress, newTelephone) {
            this.address = newAddress;
            this.telephone = newTelephone;
            console.log('Contact info updates for ${this.name}:');
        }

        displayInfo() {
            console.log('=== Customer Information ===');
            console.log('ID: ${this.customerID}');
            console.log('Name: ${this.name}');
            console.log('Address: ${this.address}');
            console.log('Telephone: ${this.telephone}');
            console.log('License Number: ${this.licenseNumber}');
        }