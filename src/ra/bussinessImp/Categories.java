package ra.bussinessImp;

import ra.bussiness.IShop;
import ra.presentation.Main;

import java.util.Comparator;
import java.util.Scanner;

public class Categories implements IShop {
    private int id;
    private String name;
    private String description;
    private boolean status;

    public Categories() {
    }

    public Categories(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.id = inputId();
        this.name = inputName(scanner);
        this.description = inputDescription(scanner);
        this.status = inputStatus(scanner);
    }

    public int inputId() {
        if (Main.listCategories.size() == 0) {
            return 1;
        }
        return Main.listCategories.stream().max(Comparator.comparing(Categories::getId)).get().id;
    }

    public String inputName(Scanner scanner) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            String name = scanner.nextLine();
            int cntCategories = (int) Main.listCategories.stream().filter(categories -> categories.name.equals(name)).count();
            if (cntCategories == 0) {
                return name;
            }
            System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả danh mục:");
        return scanner.nextLine();
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục:");
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
        System.out.printf("Mã DM: %d - Tên DM: %s - Mô tả: %s - Trạng thái: %s\n",
                this.id, this.name, this.description, this.status ? "Hoạt động" : "Không hoạt động");
    }
}
