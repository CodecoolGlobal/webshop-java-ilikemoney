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
        Supplier wizardly = new Supplier("Mr. Wizardly", "Lazily put together magic items");
        supplierDataStore.add(wizardly);
        Supplier blibdoolpoolp = new Supplier("Blibdoolpoolp", "The Sea Mother, The Drowning Goddess, Whip of Whips");
        supplierDataStore.add(blibdoolpoolp);

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
        ProductCategory nickNack = new ProductCategory("Nick-nack", "Whatever... Am I supposed to know? Like, you run the shop, don't you. Jeez man...", "Our finest ornamental objects");
        productCategoryDataStore.add(nickNack);
        ProductCategory food = new ProductCategory("Food", "Magical Edible", "Finest quality protein and vitamins included");
        productCategoryDataStore.add(food);


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Sock of Water Walking", 62.5f, "NOK", "you can walk on water but gets terribly wet and uncomfortable", clothing, wizardly));
        productDataStore.add(new Product("Boots of Reverse Flying", 124.5f, "NOK", "You will feel upside down floating on the surface", clothing, wizardly));
        productDataStore.add(new Product("Not water", 0, "SOS","Look, smells and tastes like water, but you know it's not water, neither us nor the cosmos know where it comes from", other, blibdoolpoolp));
        productDataStore.add(new Product("Common fruit", 1, "SOS", "common", other, blibdoolpoolp));
        productDataStore.add(new Product("Tie of Protection +1", 32, "NOK", "Protects your tie from weapon damage", clothing, wizardly));
        productDataStore.add(new Product("Endless vacuum", 340, "HUF", "You can't deny the fact that nothing is either none or infinite", nickNack, tronley));
        productDataStore.add(new Product("Linked List", 12005, "HUF", "A pair of shopping lists tied together, you always know where the next one is when you hold either one", scroll, tronley));
        productDataStore.add(new Product("Actually Instant Noodles", 1200, "HUF", "Just a can of baked noodles, but I'm sure you'll love it!", food, tronley));
        productDataStore.add(new Product("Rubber-duck of Silence", 44, "NOK", "If you'd speak to it, your talk won't leave a noise", nickNack, wizardly));
        productDataStore.add(new Product("Suit of Mirage", 26515, "HUF", "The ones looking at you will experience a mirage of miracles", clothing, tronley));
        productDataStore.add(new Product("Chalice of Suffering", 469, "HUF", "It seriously hurts, don't touch it. What else do you have friends for?", cutlery, tronley));
        productDataStore.add(new Product("Helm of Sight", 11999.99f, "HUF", "Perfect vision no matter where are your eyes, if your eyes work properly, that is", clothing, tronley));
        productDataStore.add(new Product("Helm of Insight", 2, "SOS", "Grants us eyes, Grant us eyes! By Kosm or some say Cos, You shall experience the cosmic truth", clothing, blibdoolpoolp));
        productDataStore.add(new Product("Instant Skeletal Buzz", 590, "HUF", "You will feel your whole skeleton while you drink this miracle elixir. At least for a good second... also tastes like beer", potion, tronley));
        productDataStore.add(new Product("Void Box", 1, "SOS", "It is not Void because it is empty, It is, because we do not know what it is full of", other, blibdoolpoolp));
        productDataStore.add(new Product("Bag of Bag of Holding Holding", 112, "NOK", "A bag of holding that holds bag of holdings", clothing, wizardly));
    }
}
