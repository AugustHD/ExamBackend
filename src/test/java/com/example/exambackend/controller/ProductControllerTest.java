package com.example.exambackend.controller;

import com.example.exambackend.product.controller.ProductController;
import com.example.exambackend.product.model.Product;
import com.example.exambackend.product.service.ProductService;
import com.example.exambackend.repo.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    //TODO: Find ud af hvofor mockmvc & mapper ikke vil autowire
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ProductService productService;

    Product PRODUCT_1 = new Product(1L, "Mælk", 25.00, 3000, null);
    Product PRODUCT_2 = new Product(2L, "Slik", 30.25, 400, null);
    Product PRODUCT_3 = new Product(3L, "Cola", 12.90, 300, null);

    @Test
    public void getAllProducts_success() throws Exception {
        Set<Product> products = new HashSet<>(Arrays.asList(PRODUCT_1, PRODUCT_2, PRODUCT_3));

        Mockito.when(productService.findAll()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("Mælk")));
    }

    // Denne test virker ikke: Mangler productID parameter.
    @Test
    public void getProductById_success() throws Exception {
        Mockito.when(productService.findById(PRODUCT_2.getId())).thenReturn(java.util.Optional.of(PRODUCT_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/findProduct")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name").value("Slik"));
    }

    // Virker heller ikke, body returner null-værdier af en eller anden grund. Måske pga. mock.perform()
    // TODO: Fix Unit-tests
    @Test
    public void addProduct_success() throws Exception {
        Product product = Product.builder()
                .name("TestProduct")
                .price(500.95)
                .weight(200)
                .Id(1L)
                .build();

        Mockito.when(productService.save(product)).thenReturn(product);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/products/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(product));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name").value("TestProduct"));
    }
}
