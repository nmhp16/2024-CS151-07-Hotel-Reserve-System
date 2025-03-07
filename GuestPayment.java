public class GuestPayment implements Payment {

    private double roomCharges;
    private double amenityCharges;
    private int loyaltyPoints;

    public GuestPayment() {
        this.amenityCharges = 0;
        this.loyaltyPoints = 0;
    }

    public double getAmenityCharges() {
        return amenityCharges;
    }

    public double getRoomCharges() {
        return roomCharges;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    //assigns the value of roomCharges
    public void addRoomCharges(double roomCharges) {
        this.roomCharges += roomCharges;
    }

    // Method to add amenity charges 
    public void addAmenityCharges(double amount) {
        amenityCharges += amount;
    }

    // Method to calculate the total guest charges
    @Override
    public double calculateTotal() {
        return roomCharges + amenityCharges + calculateTaxes();
    }

    public void addRewards(double points) {
        loyaltyPoints += points;
    }

    // Method to apply loyalty points to reduce the total bill
    private void redeemLoyaltyPoints() {
        double discount = loyaltyPoints * 0.01; // point = $0.01
        roomCharges = Math.max(0, roomCharges - discount); // Charges >= 0
        loyaltyPoints = 0;
        System.out.printf("Reduced room bill by $%.2f", discount);
        System.out.println(" with loyalty points!");
    }

    // Method to process guest payment
    @Override
    public void processPayment(Hotel h) {
        if (loyaltyPoints > 0) {
            redeemLoyaltyPoints();
        }
        double revenue = calculateTotal();
        System.out.printf("Processing guest payment of $%.2f\n", revenue);
        addRewards(roomCharges + amenityCharges);
        h.addEarnings(revenue);
        recordTransaction();
        roomCharges = 0;
        amenityCharges = 0;
    }

    // Method to calculate taxes on the total bill
    @Override
    public double calculateTaxes() {
        double taxRate = 0.10; 
        return (roomCharges + amenityCharges) * taxRate;
    }

    // Method to generate receipt for guest
    @Override
    public String generateReceipt() {
        return String.format("Guest Payment Receipt: Room Charges: $%.2f" +
                ", Amenity Charges: $%.2f" + ", Taxes: $%.2f" +
                ", Total: $%.2f", roomCharges, amenityCharges,
                calculateTaxes(), calculateTotal());
    }

    // Method to process refund for guest in case of cancellations
    public void processRefund(double refundAmount) {
        System.out.println("Processing refund of $" + refundAmount + " for the guest.");
    }

     // Method to record guest payment transaction for future reference
    @Override
    public void recordTransaction() {
        System.out.println("Recording guest transaction:\n" + generateReceipt());
    }
    
}
