package com.wtho.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
   int quantity = 0;
   int price = 500;
   TextView orderSummaryTextView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
   }

   /**
    * This method is called when the order button is clicked.
    */
   public void submitOrder(View view) {
      int totalPrice = calculatePrice(quantity, price);
      createOrderSummary(totalPrice);
   }

   public void increment(View view) {
      quantity = quantity + 1;
      displayQuantity(quantity);
   }

   public void decrement(View view) {
      quantity = quantity - 1;
      displayQuantity(quantity);
   }

   /**
    * Calculate Price
    **/
   private int calculatePrice(int cupCount, int price) {
      int totalPrice = cupCount * price;
      return totalPrice;
   }

   /**
    * create order summary
    **/
   private void createOrderSummary(int priceOfOrder) {
      String message = "Name: Winnie" + "\n" + "Quantity: " + quantity + "\n" + "Total: $" + priceOfOrder + "\n" + "Thank You!";
      displayMessage(message);
   }

   /**
    * This method displays the given quantity value on the screen.
    */
   private void displayQuantity(int number) {
      TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
      quantityTextView.setText("" + number);
   }

   /**
    * This method displays the given text on the screen.
    */
   private void displayMessage(String message) {
      orderSummaryTextView.setText(message);
   }
}
