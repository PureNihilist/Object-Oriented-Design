/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;

/**
 *
 * @author student
 */
public class Order {
    int order_id;
    Client client;
    Order (int orderID, Client client) {
        this.client = client;
        order_id = orderID;
    }
}
