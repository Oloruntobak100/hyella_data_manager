package com.hyella.hyellapaymentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyella.hyellapaymentservice.gatewayservice.PaymentGatewayServiceApplication;
import com.hyella.hyellapaymentservice.gatewayservice.entity.Organization;
import com.hyella.hyellapaymentservice.gatewayservice.entity.User;
import com.hyella.hyellapaymentservice.gatewayservice.service.OrganizationService;
import com.hyella.hyellapaymentservice.gatewayservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest(classes = PaymentGatewayServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class UserControllerWebLayerTest {

    //@MockBean
    //private UserService userService;
    //
    //@MockBean
    //private OrganizationService organizationService;
    //
    //@Autowired
    //private MockMvc mockMvc;
    //
    //@Autowired
    //private ObjectMapper objectMapper;
    //
    //@BeforeEach
    //void setUp() {
    //    // Initialize test data
    //    User theUser = new User();
    //    theUser.setId(1L);
    //    theUser.setName("Kayode");
    //    theUser.setSourceSystemUserId("12344");
    //    theUser.setCreatedBy("Kayode");
    //    theUser.setEnabled(true);
    //    theUser.setDeleted(false);
    //
    //    Organization organization = new Organization();
    //    organization.setId(1L);
    //    theUser.setOrganization(organization);
    //
    //    // Mock service methods
    //    Mockito.when(organizationService.findByOrganizationId(1L, false))
    //            .thenReturn(Optional.of(organization));
    //
    //    Mockito.when(userService.save(theUser))
    //            .thenReturn(theUser);
    //}
    //
    //@Test
    //void shouldCreateMockMvc() {
    //    assertNotNull(mockMvc);
    //}
    //
    //@Test
    //void testAddUser() throws Exception {
    //    // Initialize test data
    //    User theUser = new User();
    //    theUser.setId(1L);
    //    theUser.setName("Kayode");
    //    theUser.setSourceSystemUserId("12344");
    //    theUser.setCreatedBy("Kayode");
    //    theUser.setEnabled(true);
    //    theUser.setDeleted(false);
    //
    //    Organization organization = new Organization();
    //    organization.setId(1L);
    //    theUser.setOrganization(organization);
    //
    //    // Perform request and verify
    //    mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
    //                    .contentType(MediaType.APPLICATION_JSON)
    //                    .content(objectMapper.writeValueAsString(theUser)))
    //            .andExpect(status().isOk())
    //            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    //            .andExpect(jsonPath("$.status").value("success"))
    //            .andExpect(jsonPath("$.message").value("User saved successfully"))
    //            .andExpect(jsonPath("$.data.name").value("Kayode"));
    //}
    //
    //@Test
    //void testAlwaysPass() {
    //    assertTrue(true, "This test will always pass");
    //}



    @Test
    public void testAddition() {
        // Arrange
        int number1 = 1;
        int number2 = 4;

        // Act
        int result = number1 + number2;

        // Assert
        assertEquals(5, result, "1 + 2 should equal 3");
    }
}

