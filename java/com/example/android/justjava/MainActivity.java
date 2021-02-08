/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    int quant = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view)
    {
        Toast toast = Toast.makeText(this, "Order Limit Reached", Toast.LENGTH_SHORT);
        //toast.cancel();
        if(quant==20) {
            toast.show();

            return;
        }
        ++quant;
        displayQuantity(quant);
    }

    public void decrement(View view) {
        Toast toast = Toast.makeText(this, "Do You Really Want To Have Coffee", Toast.LENGTH_SHORT);
       // toast.cancel();
        if(quant==1) {
            toast.show();

            return;
        }
        --quant;
        displayQuantity(quant);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
       CheckBox check = (CheckBox) findViewById(R.id.notify_me_checkbox2);
       EditText text = (EditText) findViewById(R.id.name_field);
       String name = text.getText().toString();
       String message =(createOrderSummary(calculatePrice(checkbox1(),checkbox2()), checkbox1(), checkbox2(), name));
       ///////////////////////////////////////////////////////////////////////////////////
       Intent intent = new Intent(Intent.ACTION_SENDTO);
       intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,new String[] { "meharshiv@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public boolean checkbox1() {
        CheckBox check = (CheckBox) findViewById(R.id.notify_me_checkbox);
        boolean whippedcream = check.isChecked();
        return whippedcream;
    }

    public boolean checkbox2() {
        CheckBox check = (CheckBox) findViewById(R.id.notify_me_checkbox2);
        boolean choco = check.isChecked();
        return choco;
    }

    private int calculatePrice(boolean whip,boolean choco) {

        int price = 5; // only coffee price
        if (whip == true) {
            price+= 1; //whip cream for each coffee
        }
        if (choco == true) {
            price+= 2;  //chocolate for each coffee
        }
        return (price*quant);
    }

    private String createOrderSummary(int price, boolean whip, boolean choco, String name) {
        String pricemssg = "Name: " + name + "\n";
        if (whip == true) {
            pricemssg += "Whipped Cream: Added = $1\n";
        }
        if (choco == true) {
            pricemssg += "Chocolate: Added = $2\n";
        }
        pricemssg += "Quantity: " + quant + "\nTotal: $" + price + "\nThankyou!";
        return pricemssg;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}