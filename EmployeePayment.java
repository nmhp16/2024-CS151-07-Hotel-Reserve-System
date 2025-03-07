public class EmployeePayment implements Payment {

    private double hourlyRate;
    private double hoursWorked = 0;

    public EmployeePayment(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    // Method to modify hourly rate
    public void modifyHourlyRate(double newRate) {
        this.hourlyRate = newRate;
    }

    // Method to modify hoursWorked
    public void modifyhoursWorked(double hoursWorked) {
        if(hoursWorked >= 0){
            this.hoursWorked = hoursWorked;
        }
    }

    // Method to log hours worked
    public void logHours(double hours) {
        this.hoursWorked += hours;
        System.out.println("Recorded " + hours + " to timecard.");
    }

    // Method to calculate total payment based on hours worked
    @Override
    public double calculateTotal() {
        return hourlyRate * hoursWorked - calculateTaxes();
    }

    @Override
    public double calculateTaxes() {
        double totalIncomeBeforeTax = hourlyRate * hoursWorked;
        double taxAmount = 0;
        if(totalIncomeBeforeTax <= 10099/12){
            taxAmount = totalIncomeBeforeTax * 0.01;
        }
        else if(totalIncomeBeforeTax >= 10100/12 && totalIncomeBeforeTax <= 23942/12){
            taxAmount = totalIncomeBeforeTax * 0.02;
        }
        else if(totalIncomeBeforeTax >= 23942/12 && totalIncomeBeforeTax <= 37788/12){
            taxAmount = totalIncomeBeforeTax * 0.04;
        }
        else if(totalIncomeBeforeTax >= 37789/12 && totalIncomeBeforeTax <= 52455/12){
            taxAmount = totalIncomeBeforeTax * 0.06;
        }
        else if(totalIncomeBeforeTax >= 52456/12 && totalIncomeBeforeTax <= 66295/12){
            taxAmount = totalIncomeBeforeTax * 0.08;
        }
        else{
            taxAmount = totalIncomeBeforeTax * 0.093;
        }

        return taxAmount;
    }

    // Method to process payment
    @Override
    public void processPayment(Hotel h) {
        recordTransaction();
        hoursWorked = 0;

    }

    // Method to generate employee paycheck details
    @Override
    public String generateReceipt() {
        return String.format("Employee Payment Receipt:\nTotal Hours Worked: " +
                hoursWorked + ", Total taxes: $%.2f, Total Payment: $%.2f",
                calculateTaxes(), calculateTotal());
    }

    @Override
    public void recordTransaction() {
        System.out.println("Processing employee transaction:\n" + generateReceipt());
    }
}
