package com.luo.reja;

import com.luo.reja.model.Product;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.ArrayList;
import java.util.List;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin Grid Demo",
        description = "This is a demo Vaadin Grid.",
        enableInstallPrompt = false)
@CssImport(value = "./styles/custom-grid-styles.css", themeFor = "vaadin-grid")
public class MainView extends VerticalLayout {
    private Grid<Product> productGrid;
    private List<Product> productList;

    public MainView() {
        productList = new ArrayList<>();
        prepareProductItems();
        add(createGrid());
    }

    private Grid createGrid() {
        productGrid = new Grid<>(Product.class);
        productGrid.setItems(productList);
        productGrid.removeColumnByKey("id");
        productGrid.setColumns("name", "type");
        configureColumnStyles();
        return productGrid;
    }

    private void prepareProductItems() {
        Product product = new Product(1, "Apple", "Fruit");
        productList.add(product);
        product = new Product(2, "Asparagus", "Vegetable");
        productList.add(product);
        product = new Product(3, "Broccoli", "Vegetable");
        productList.add(product);
        product = new Product(4, "Avocado", "Fruit");
        productList.add(product);
        product = new Product(5, "Banana", "Fruit");
        productList.add(product);
        product = new Product(6, "Fennel", "Vegetable");
        productList.add(product);
    }

    private void configureColumnStyles() {
        productGrid.setClassNameGenerator((v) -> {
            if (v.getType().toLowerCase().equals("vegetable")) {
                return "vegetable";
            }
            return "";
        });
    }
}
