package de.frankherling.spielwiese.app.infrastructure.adapter.rest.impl;


import de.frankherling.spielwiese.app.application.port.in.OrdersPort;
import de.frankherling.spielwiese.app.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {"spring.liquibase.enabled=false", "adapter.jms.enabled=false"})
@Testcontainers
@AutoConfigureMockMvc
class OrdersApiControllerTestIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withInitScripts("preliquibase/postgresql.sql")
            .withDatabaseName("db")
            .withUsername("user")
            .withPassword("pwd");

    @Container
    static GenericContainer<?> artemis = new GenericContainer<>("vromero/activemq-artemis:latest")
            .withExposedPorts(61616);

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> (postgres.getJdbcUrl()) + "?currentSchema=app");
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.artemis.broker-url", () -> "tcp://" + artemis.getHost() + ":" + artemis.getMappedPort(61616));
    }

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrdersPort ordersPort;

    @Test
    @WithMockUser(username = "user", roles = "APPUSER")
    void testOrdersGet() throws Exception {
        List<Order> orders = new ArrayList<>();
        // Add dummy data for demonstration
        orders.add(Order.builder().build());
        orders.add(Order.builder().build());

        when(ordersPort.getOrders()).thenReturn(orders);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(ordersPort).getOrders();
    }

    @Test
    @WithMockUser(username = "user", roles = "APPUSER")
    void testOrdersOrderIdGet() throws Exception {
        String orderId = UUID.randomUUID().toString();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/{orderId}", orderId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId));
    }

    @Test
    @WithMockUser(username = "user", roles = "APPUSER")
    void testOrdersPost() throws Exception {
        String orderJson = "{\"id\":\"id\", \"item\":\"item\",\"quantity\":1,\"price\":10.0}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.item").value("item"))
                .andExpect(jsonPath("$.quantity").value(1))
                .andExpect(jsonPath("$.price").value(10.0));
    }
}