package id.ac.ui.cs.advprog.testproject.controller;

import id.ac.ui.cs.advprog.testproject.model.Car;
import id.ac.ui.cs.advprog.testproject.model.Product;
import id.ac.ui.cs.advprog.testproject.service.CarServiceImpl;
import id.ac.ui.cs.advprog.testproject.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute("product") Product product, Model model) {
        service.create(product);
        return "redirect:list";

    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = service.findById(productId);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute("product") Product product) {
        service.update(product);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductPage(@PathVariable String id, Model model) {
        Product product = service.findById(id);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "deleteProduct";
    }

    @PostMapping("/delete")
    public String deleteProductPost(@ModelAttribute("product") Product product) {
        service.delete(product);
        return "redirect:list";
    }

    @Controller
    @RequestMapping("/car")
    class CarController extends ProductController {
        @Autowired
        private CarServiceImpl carservice;

        @GetMapping("/createCar")
        public String createCarPage(Model model) {
            Car car = new Car();
            model.addAttribute("car", car);
            return "createCar";
        }


        @PostMapping("/createCar")
        public String createCarPost(@ModelAttribute Car car, Model model) {
            carservice.create(car);
            return "redirect: ListCar";
        }

        @GetMapping("/ListCar")
        public String carListPage(Model model) {
            List<Car> allCars = carservice.findAll();
            model.addAttribute("cars", allCars);
            return "carList";
        }
        @GetMapping("/editCar/{carId}")
        public String editCarPage(@PathVariable String carId, Model model) {
            Car car = carservice.findById(carId);
            model.addAttribute("car", car);
            return "editCar";
        }

        @PostMapping("/editCar")
        public String editCarPost(@ModelAttribute Car car, Model model) {
            System.out.println(car.getCarId());
            carservice.update(car.getCarId(), car);
            return "redirect: ListCar";
        }

        @PostMapping("/deleteCar")
            public String deleteCar(@RequestParam("carId") String carId){
                carservice.deleteCarById(carId);
                return "redirect: ListCar";
            }
    }

}
