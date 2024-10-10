package ra.bussinessImp;

import ra.bussiness.IShop;
import ra.presentation.Main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IShop {
    private String id;
    private String name;
    private int catalogId;
    private float importPrice;
    private float exportPrice;
    private boolean status;

    public Product() {
    }

    public Product(String id, String name, int catalogId, float importPrice, float exportPrice, boolean status) {
        this.id = id;
        this.name = name;
        this.catalogId = catalogId;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.id = inputId(scanner);
        this.name = inputName(scanner);
        this.catalogId = inputCatalogId(scanner);
        this.importPrice = inputImportPrice(scanner);
        this.exportPrice = this.importPrice * IShop.RATE;
        this.status = inputStatus(scanner);
    }

    public String inputId(Scanner scanner) {
        String idRegex = "P\\d{3}";
        System.out.println("Nhập vào mã sản phẩm:");
        do {
            String id = scanner.nextLine();
            if (Pattern.matches(idRegex, id)) {
                int cnt = (int) Main.listProducts.stream().filter(product -> product.getId().equals(id)).count();
                if (cnt == 0) {
                    return id;
                }
                System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
            } else {
                System.err.println("Mã sản phẩm không đúng định dạng, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String name = scanner.nextLine();
            int cnt = (int) Main.listProducts.stream().filter(product -> product.name.equals(name)).count();
            if (cnt == 0) {
                return name;
            }
            System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
        } while (true);
    }

    public int inputCatalogId(Scanner scanner) {
        System.out.println("Chọn danh mục của sản phẩm:");
        for (int i = 0; i < Main.listCategories.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), Main.listCategories.get(i).getName());
        }
        System.out.print("Lựa chọn của bạn:");
        int choice = Integer.parseInt(scanner.nextLine());
        return Main.listCategories.get(choice - 1).getId();
    }

    public float inputImportPrice(Scanner scanner) {
        System.out.println("Nhập vào giá nhập của sản phẩm:");
        do {
            try {
                float importPrice = Float.parseFloat(scanner.nextLine());
                if (importPrice > 0) {
                    return importPrice;
                }
                System.err.println("Giá nhập có giá trị lớn hơn 0, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Không đúng định dạng giá nhập, vui lòng nhập lại");
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm:");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            }
            System.err.println("Trang thái chỉ nhận giá trị true hoặc false, vui lòng nhập lại");
        } while (true);
    }

    @Override
    public void displayData() {
        System.out.printf("Mã SP: %s - Tên SP: %s - Tên DM %s\n",
                this.id, this.name,
                Main.listCategories.stream().filter(categories -> categories.getId() == this.catalogId).toList().get(0).getName());
        System.out.printf("Giá nhập: %.1f - Giá xuất: %.1f - Trạng thái: %s\n",
                this.importPrice, this.exportPrice, this.status ? "Hoạt động" : "Không hoạt động");
    }
}
