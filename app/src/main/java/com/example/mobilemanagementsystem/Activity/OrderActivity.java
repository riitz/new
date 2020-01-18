 package com.example.mobilemanagementsystem.Activity;

 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.ImageView;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;

 import com.example.mobilemanagementsystem.Interface.orderApi;
 import com.example.mobilemanagementsystem.Model.order;
 import com.example.mobilemanagementsystem.R;
 import com.example.mobilemanagementsystem.URL.Url;
 import com.squareup.picasso.Picasso;

 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;

 public class OrderActivity extends AppCompatActivity {

    ImageView selectitem_image;
    TextView txtdesc,no_of_item,totalprice, prodPrice;
    Button btnincrement,btndecrement,btnOrder;
     public static float price, totprice;

    public static int counter;
    public static int c2 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        selectitem_image = findViewById(R.id.selfoodimg);
        txtdesc = findViewById(R.id.description);

        no_of_item = findViewById(R.id.no_of_items);
        totalprice = findViewById(R.id.totatcost);
        prodPrice = findViewById(R.id.prodPrice);
        btnincrement = findViewById(R.id.increment);
        btndecrement = findViewById(R.id.decrement);
        btnOrder = findViewById(R.id.btnOrder);

        prodPrice.setText(getIntent().getStringExtra("prodPrice"));
        totalprice.setText(getIntent().getStringExtra("prodPrice"));
        Picasso.get().load(getIntent().getStringExtra("prodImage")).into(selectitem_image);
        txtdesc.setText(getIntent().getStringExtra("prodName"));
        System.out.println(getIntent().getStringExtra("prodPrice"));
        price = Float.parseFloat(getIntent().getStringExtra("prodPrice"));

        btnincrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = Integer.parseInt(no_of_item.getText().toString());
                if(counter > 0 )
                {
                    counter++;
                    totprice = price * counter;
                    String count = Integer.toString(counter);

                    no_of_item.setText(count);

                    String totalcost = Float.toString(totprice);
                    totalprice.setText(totalcost);
                }
                else {
                    no_of_item.setText("1");
                    totalprice.setText(getIntent().getStringExtra("prodPrice"));
                    counter = 1;
                }
            }
        });

        btndecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = Integer.parseInt(no_of_item.getText().toString());
                if(counter > 1)
                {
                    counter--;
                    totprice = Float.parseFloat(totalprice.getText().toString());
                    totprice = totprice  - price;

                    String c1 = Integer.toString(counter);
                    no_of_item.setText(c1);

                    String totalCost = Float.toString(totprice);
                    totalprice.setText(totalCost);

                }
                else {
                    no_of_item.setText("1");
                    totalprice.setText(getIntent().getStringExtra("prodPrice"));
                    counter = 1;
                }
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order orders = new order(getIntent().getStringExtra("prodId"), no_of_item.getText().toString());
                orderApi orderApi = Url.getInstance().create(orderApi.class);
                Call<order> orderCall = orderApi.orderItems(Url.token, orders);

                orderCall.enqueue(new Callback<order>() {
                    @Override
                    public void onResponse(Call<order> call, Response<order> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(OrderActivity.this, "Couldn't place order", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(OrderActivity.this, "Item Ordered Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<order> call, Throwable t) {
                        Toast.makeText(OrderActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
