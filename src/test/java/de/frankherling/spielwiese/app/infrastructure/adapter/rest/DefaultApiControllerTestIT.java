package de.frankherling.spielwiese.app.infrastructure.adapter.rest;

import de.frankherling.spielwiese.app.infrastructure.adapter.rest.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DefaultApiControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testOrdersGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testOrdersOrderIdGet() throws Exception {
        String orderId = UUID.randomUUID().toString();
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/{orderId}", orderId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId));
    }

    @Test
    void testOrdersPost() throws Exception {
        String orderJson = "{\"item\":\"item\",\"quantity\":1,\"price\":10.0}";

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.item").value("item"))
                .andExpect(jsonPath("$.quantity").value(1))
                .andExpect(jsonPath("$.price").value(10.0));
    }
}