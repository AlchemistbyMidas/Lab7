package Store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Product implements Comparable<Product>{
    // private Set<Basket> baskets;
    private String name;
    private LocalDate dateOfCreation;

    private int shelfTimes;

    private double price;
    int id ;




   /* public void writeObjectToFile(Product object) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(oos);
    }*/

    public Product() {

    }
    public Product (String name , LocalDate dateofCreation) {
        this.name=name;
        this.dateOfCreation=dateofCreation;
    }


    public Product(String name, LocalDate dateoOfCreation, int shelfTime) {
        this.name = name;
        this.dateOfCreation = dateoOfCreation;
        this.shelfTimes = shelfTime;
        this.price=0;
    }
    public Product(String name, LocalDate dateoOfCreation, int shelfTime,double price) {
        this.name = name;
        this.dateOfCreation = dateoOfCreation;
        this.shelfTimes = shelfTime;
        this.price=price;
    }

    @Override
    public int compareTo(Product product) {
        if(this.price<product.price) {
            return -1;
        }
        else if(this.price>product.price) {
            return 1;
        }
        return 0;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Product d = (Product) o;
        return this.name.equals(d.name);
    }

    @Override
    public int hashCode() {
        // return this.name.hashCode()+this.dateOfCreation.toString().hashCode();
        return this.name.hashCode();
    }



    public String getName() {
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }


    public LocalDate getDateOfCreation() {
        return this.dateOfCreation;
    }

    public int getShelfTime() {
        return this.shelfTimes;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setDateOfCreation(LocalDate newDateOfCreation) {
        this.dateOfCreation = newDateOfCreation;
    }

    public void setShelfTimes(int newShelfTime) {
        this.shelfTimes = newShelfTime;
    }

    @Override
    public String toString() {
        return String.format("This is %s,which was created on %s", this.name, this.dateOfCreation.toString());
    }

    public boolean isGood() {
        LocalDate temp;
        temp = this.dateOfCreation;
        temp = temp.plusMonths(shelfTimes);
        return (!temp.isBefore(LocalDate.now()));
    }

    public int howManyDaysLeft() {
        LocalDate temp;
        int days;
        temp = this.dateOfCreation;
        temp = temp.plusMonths(shelfTimes);
        if (!temp.isBefore(LocalDate.now())) {
            days = (int) ChronoUnit.DAYS.between(LocalDate.now(), temp);
        } else {
            days = 0;
        }
        return Math.abs(days);

    }

    public double getPrice() {
        return this.price;
    }
    public void setPrice(float newPrice) {
        this.price=newPrice;
    }

}