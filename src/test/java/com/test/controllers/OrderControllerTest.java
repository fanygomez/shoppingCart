package com.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingCart.dto.OrderDTO;
import com.shoppingCart.dto.OrderReqDTO;
import com.shoppingCart.dto.OrderRespDTO;
import com.shoppingCart.models.Order;
import com.shoppingCart.models.OrderDetail;
import com.shoppingCart.repositories.OrderRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class OrderControllerTest {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private MockMvc mockMvc;
    @Before
    public void setup() {
        orderRepository.deleteAll();
        om.setDateFormat(simpleDateFormat);
    }
    @Test
    void save() throws Exception {
        OrderReqDTO expectedRecord = getTestData().get(0);
        System.out.println("expectedRecord");
        System.out.println(expectedRecord);
//        OrderRespDTO actualRecord = om.readValue(mockMvc.perform(post("/orders")
//                        .contentType("application/json")
//                        .content(om.writeValueAsString(getTestData().get(0))))
//                .andDo(print())
//                .andExpect(jsonPath("$.userId", greaterThan(0)))
//                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), OrderRespDTO.class);
//
//        Assert.assertTrue(new ReflectionEquals(expectedRecord, "orderId").matches(actualRecord));
//        assertEquals(true, orderRepository.findById(actualRecord.getOrderId()).isPresent());
    }

    @Test
    void getById() {
    }

    @Test
    void cancel() {
    }

    private List<OrderReqDTO> getTestData() throws ParseException {
        List<OrderReqDTO> data = new ArrayList<>();

        OrderReqDTO orderTest1 = new OrderReqDTO();
        orderTest1.setOrder(new OrderDTO(1L));
        data.add(orderTest1);

        OrderReqDTO orderTest2 = new OrderReqDTO();
        orderTest1.setOrder(new OrderDTO(2L));
        data.add(orderTest2);

        return data;
    }
}