package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier tronley = new Supplier("Doctor Jonathan Tronley", "Miracle products");
        supplierDataStore.add(tronley);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory potion = new ProductCategory("Potion", "Liquid", "Bottles containing fluids of magical property");
        productCategoryDataStore.add(potion);
        ProductCategory scroll = new ProductCategory("Scroll", "Spellware", "Spells scribed to scrolls");
        productCategoryDataStore.add(scroll);
        ProductCategory clothing = new ProductCategory("Clothes", "Clothware", "Cloths weaved from magical materials, but in reality they were simple clothes and the magic was added later");
        productCategoryDataStore.add(clothing);
        ProductCategory cutlery = new ProductCategory("Cutlery", "Cutlery", "Cutlery of all kinds");
        productCategoryDataStore.add(cutlery);
        ProductCategory other = new ProductCategory("Other", "Other", "Items of eldritch origin");
        productCategoryDataStore.add(other);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Sock of Water Walking", 62, "GP", "you can walk on water but gets terribly wet and uncomfortable", clothing, tronley));
    }
}
