package vn.edu.ntu.ngodacluong.recycleview59cntt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.ngodacluong.controller.ICartController;
import vn.edu.ntu.ngodacluong.model.Product;

public class ShoppingCartActivity extends AppCompatActivity {
    TextView txtShoppingCart;
    ICartController controller;
    Button btnOK, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        controller = (ICartController) getApplication();
        addViews();

    }

    private void addViews() {
        txtShoppingCart = findViewById(R.id.txtCart);
        displayShoppingCart();
    }

    private void displayShoppingCart() {

        List<Product> shoppingCart;
        shoppingCart = controller.getShoppingCart();
        final StringBuilder builder = new StringBuilder();
        int sum = 0;
        for (Product p:shoppingCart){
            sum = sum + p.getPrice();
            builder.append(p.getName())
                    .append("\t\t\t")
                    .append(p.getPrice())
                    .append("VND\n");
        }builder.append("Tiền phải thanh toán là:\t\t").append(sum).append("VND\n");

        if (builder.toString().length() > 0)
        {
            txtShoppingCart.setText(builder.toString());

            btnOK = findViewById(R.id.btnOK);
            btnDelete = findViewById(R.id.btnDelete);
            //cai dat su kien.
            btnOK.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast toast=Toast.makeText(ShoppingCartActivity.this, "" +
                                    "Bạn đã mua hàng thành công.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    displayShoppingCart();

//                    View v = View.findViewById(R.id.btnOK);
                    txtShoppingCart.setText("Không có mặt hàng nào trong giỏ hàng. Tiếp tục mua hàng nào.");

                }
            });



            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast =  Toast.makeText(ShoppingCartActivity.this, "Giỏ hàng của bạn đã trống.", Toast.LENGTH_SHORT);
                    toast.show();
                    txtShoppingCart.setText(" ");
                }
            });
        }
        else
            txtShoppingCart.setText("Không có mặt hàng nào trong giỏ hàng.");
    }

}
