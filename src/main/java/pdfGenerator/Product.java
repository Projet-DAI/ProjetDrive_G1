package pdfGenerator;

public class Product {
        private String productName;
        private int quantity;
        private String status;

        public Product(String productName, int quantity, String status) {
            this.productName = productName;
            this.quantity = quantity;
            this.status = status;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getStatus() {
            return status;
        }
}
