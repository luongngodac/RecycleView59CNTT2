package vn.edu.ntu.ngodacluong.recycleview59cntt2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.ngodacluong.controller.ICartController;
import vn.edu.ntu.ngodacluong.model.Product;

public class MainActivity extends AppCompatActivity
{
    RecyclerView rvMatHang;
    Adapter product;
    ICartController friendController ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMatHang = findViewById(R.id.rvMatHang);
        rvMatHang.setLayoutManager(new LinearLayoutManager(this));
        friendController = (ICartController) getApplication();
        product = new Adapter(friendController.getAllProduct());
        rvMatHang.setAdapter(product);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();//getMeunInflater la cua phuong thuc MeunInflater; khong the tao moi(new no duoc)
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnu_close:
                finish();
                break;//de thoat khoi no.
            case R.id.mnu_cart:
                showCart();
                break;//khong co cung duoc.
        }
//
//        switch (item.getItemId()){
//            case R.id.mnu_close:
//                finish();
//                break;
//        }
        return super.onOptionsItemSelected(item);
    }

    //
    private void showCart(){
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    //goi phuong thuc finish chu khong phai viet;
    //viet: override lai.
    //res: resource
    //bat ky layout minh thiet ke thi phai co inflater(MenuInfalter chang han).

    //Khong viet cac phuong thuc cua MainActivity o phia duoi day(viet phia truoc thoai mai)

    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtName ,txtPrice, txtDesc;
        ImageView imvAddToCart;
        Product product;

        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtNamePro);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imvAddToCart  = this.itemView.findViewById(R.id.imvAddToCart);//this phuong thuc dang viet tai day: (ProductViewHolder)
            imvAddToCart.setOnClickListener(this);//this phuong thuc dang viet tai day.(onClickListener)

        }

        public void bind (Product p)
        {
            this.product = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());

        }

        @Override
        public void onClick(View v) {
            if (friendController.addToCart(product)) {
                Toast.makeText(MainActivity.this,
                        "Đã thêm: " + product.getName() + " vào giỏ hàng.",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(MainActivity.this,
                        product.getName() + " đã có trong giỏ hàng.",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }


    }

    private class Adapter extends RecyclerView.Adapter<ProductViewHolder>
    {
        List<Product> productList;

        public Adapter(List<Product> productList) {
            this.productList = productList;
        }
        //chu y Adapter cai cho activity nen co day du phuong thuc roi
        //truong hop cai view phia ngoai thi nen khai bao bien context roi lay ra tu no

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(this.productList.get(position));
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }
    }

}
