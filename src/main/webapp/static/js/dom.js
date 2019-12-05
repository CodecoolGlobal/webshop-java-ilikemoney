import {listeners} from "./EventListeners.js";

export const dom = {

    init: function () {
        dom.sortProducts();
    },


    sortProducts: function () {
        const suppSelector = document.querySelector("#supplier-selector");
        const catSelector = document.querySelector("#category-selector");
        listeners.addSortBtnListener((e) => {
            let supplier = suppSelector.options[suppSelector.selectedIndex].value;
            let category = catSelector.options[catSelector.selectedIndex].value;
            dom.showSortedProducts(supplier, category);
        });
    },

    showSortedProducts(supplier, category) {
        let products = document.querySelectorAll(".product");
        for (let product of products) {
            if ((product.dataset.supplier === supplier || supplier === "All") &&
                (product.dataset.category === category || category === "All")) {
                product.style.display = "block";
            } else {
                product.style.display = "none";
            }
        }
    }

};
