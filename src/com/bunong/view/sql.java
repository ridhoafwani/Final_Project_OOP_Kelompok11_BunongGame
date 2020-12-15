/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bunong.view;

import java.sql.*;

/**
 *
 * @author zikrurridhoafwani
 */
public class sql {
   static final Connection CONN = connect();

   static Connection connect() {
      Connection conn = null;

      try {
         conn = DriverManager.getConnection("jdbc:mysql://192.168.64.2/bunongDB", "yridho", "yridho");
      } catch (Exception e) {
         e.printStackTrace();
      }
      return conn;
   }

   static void executeUpdateQuerry(String querry) {
      try {
         Statement stt = CONN.createStatement();
         stt.executeUpdate(querry);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static ResultSet executeReadQuerry(String querry) {
      ResultSet rs = null;
      try {
         Statement stt = CONN.createStatement();
         rs = stt.executeQuery(querry);

      } catch (Exception e) {
         e.printStackTrace();
      }
      return rs;
   }

}
