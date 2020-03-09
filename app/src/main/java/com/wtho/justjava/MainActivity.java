package com.wtho.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
   private int quantity = 2;
   private int price = 5;
   private int totalPrice = 0;
   private EditText edtUserName;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      edtUserName = (EditText) findViewById(R.id.edt_user_name);
   }

   /**
    * This method is called when the order button is clicked.
    */
   public void submitOrder(View view) {
      CheckBox chkWhippedCream = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
      CheckBox chkChocolate = (CheckBox) findViewById(R.id.checkbox_chocolate);
      boolean whippedCreamChecked = chkWhippedCream.isChecked();
      boolean chocolateCheck = chkChocolate.isChecked();
      String name = edtUserName.getText().toString();
      if (whippedCreamChecked == true) {
         totalPrice = calculatePrice(quantity, price + 1);
      } else if (chocolateCheck == true) {
         totalPrice = calculatePrice(quantity, price + 2);
      } else {
         totalPrice = calculatePrice(quantity, price);
      }
      createOrderSummary(name, totalPrice, whippedCreamChecked, chocolateCheck);

      Intent intent = new Intent(Intent.ACTION_SENDTO);
      intent.setData(Uri.parse("mailto:"));
      intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for " + name);
      if (intent.resolveActivity(getPackageManager()) != null) {
         startActivity(intent);
      }
   }

   public void increment(View view) {
      if (quantity == 100) {
         Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
      }
      quantity = quantity + 1;
      displayQuantity(quantity);
   }

   public void decrement(View view) {
      if (quantity == 1) {
         Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
      }
      quantity = quantity - 1;
      displayQuantity(quantity);
   }

   /**
    * Calculate Price
    **/
   private int calculatePrice(int cupCount, int price) {
      totalPrice = cupCount * price;
      return totalPrice;
   }

   /**
    * create order summary
    **/
   private void createOrderSummary(String name, int priceOfOrder, boolean creamChecked, boolean chocolateChecked) {
      String message = "Name: " + name + "\n" + "Add Whipped cream ?" + creamChecked + "\n" + "Add Chocolate ? " + chocolateChecked + "\n" +
              "Quantity: " + quantity + "\n" + "Total: $ " + priceOfOrder + "\n" + "Thank You!";
   }

   /**
    * This method displays the given quantity value on the screen.
    */
   private void displayQuantity(int number) {
      TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
      quantityTextView.setText("" + number);
   }

}
