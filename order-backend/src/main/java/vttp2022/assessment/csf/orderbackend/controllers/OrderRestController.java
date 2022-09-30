package vttp2022.assessment.csf.orderbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.services.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

@RestController
@RequestMapping(path="/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {

    @Autowired
    private OrderService orderSvc;

// @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
// public ResponseEntity<Order> postOrder(@RequestBody Order newOrder){
//     Order order = orderSvc.createOrder(order);
//     return ResponseEntity.ok
    
// }

@GetMapping(path="{email}")
public ResponseEntity<String> getOrdersByEmail(@PathVariable String email, Model model){

    List<OrderSummary> summaries = orderSvc.getOrdersByEmail(email);

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    for (OrderSummary summary: summaries)
        arrayBuilder.add(summary.toJson());

    return ResponseEntity.ok(arrayBuilder.build().toString());
}




}
