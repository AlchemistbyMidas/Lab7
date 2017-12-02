package Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Basket {
    private String color;
    private LocalDate timeOfPicingkUp;
    private int spaciousness; // Large // small//average
    //private Set<Product> products;
    private List<Product> products;
    public Basket(LocalDate timeOfPickUp,String color,int spaciousness) {
        this.timeOfPicingkUp=timeOfPickUp;
        this.color=color;
        this.spaciousness=spaciousness;
        this.products=new ArrayList<Product>();
    }
    public Basket(LocalDate timeOfPickUp,String color,int spaciousness,List<Product> newproducts) {
        this.timeOfPicingkUp=timeOfPickUp;
        this.color=color;
        this.spaciousness=spaciousness;
        this.products=newproducts;
    }



    @Override
    public boolean equals(Object obj) {
        if(obj==this) {
            return true;
        }
        if(obj.getClass()!=this.getClass()) {
            return false;
        }
        Basket p =(Basket)obj;
        return (this.spaciousness==p.spaciousness&&this.color.equals(p.color)&&this.timeOfPicingkUp.equals(p.timeOfPicingkUp));
    }
    /*@Override
    public String toString() {
        return String.format("This is %s basket of size: %s", this.color,this.spaciousness);
    }*/
    @Override
    public int hashCode() {
        return this.timeOfPicingkUp.hashCode()+this.color.hashCode();
    }
    public LocalDate getTimeOfPicingkUp(){
        return this.timeOfPicingkUp;
    }
    public int getSpaciousness() {
        return this.spaciousness;
    }
    public void setSpaciousness(int newspaciousness) {
        this.spaciousness=newspaciousness;
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String newColor) {
        this.color=newColor;
    }
    public LocalDate gettime() {
        return this.timeOfPicingkUp;
    }



    public void setSetOfProducts (List<Product> newList) {
        this.products=newList;
    }
    public void addNewProducts(Product newProduct) {
        this.products.add(newProduct);
    }
    public List<Product> getListOfProduct(){
        return this.products;
    }
    public int countSomeProduct(String productName) {
        int count=0;
        for(Product s : this.products) {
            if(s.getName().equals(productName)) {
                count++;
            }
        }
        return count;
    }
    public int getAmountOfPrdoucts() {
        return this.products.size();
    }
    public boolean removeProduct(String productName) {
        boolean flag=false;
        for(Product z: this.products) {
            if(z.getName().equals(productName)) {
                this.products.remove(z);
                flag=true;
            }
        }
        return flag;
    }




    public List<Product> sortProductsByPriceNormal() {


        List<Product> temp= new ArrayList<Product>();
        temp=this.products;
        Collections.sort(temp);
        return temp;


    }
    public List<Product> sortProductsByPriceReverse() {
        Comparator<Product> REVERSE = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if(o1.getPrice()>o2.getPrice()) {
                    return -1;
                }
                else if(o1.getPrice()<o2.getPrice()) {
                    return 1;
                }
                return 0;
            }
        };
        List<Product> temp= new ArrayList<Product>();
        temp=this.products;
        Collections.sort(temp,REVERSE);
        return temp;
    }







    public List<Product> getTheCheapsetProduct() {
        double max= Double.MAX_VALUE;
        List<Product> temp= new ArrayList<Product>();
        for(Product z: this.products) {
            if(z.getPrice()<max) {
                max=z.getPrice();
            }
        }
        for(Product product: this.products) {
            if(Math.abs(product.getPrice()-max)<0.01) {
                temp.add(product);
            }
        }
        return temp;
    }
    public List<Product> getTheMostExpensiveProduct() {
        double min=Double.MIN_VALUE;
        List<Product> temp= new ArrayList<Product>();
        for(Product product: this.products) {
            if(product.getPrice()>min) {
                min=product.getPrice();
            }
        }
        for(Product product: this.products) {
            if(Math.abs(product.getPrice()-min)<0.01) {
                temp.add(product);
            }
        }
        return temp;
    }
}
