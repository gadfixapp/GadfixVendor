package com.app.gadfixvendor.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.app.gadfixvendor.Adapters.PaymentAdapter;
import com.app.gadfixvendor.R;
import com.app.gadfixvendor.databinding.ActivityPaymentBinding;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding> {

    RecyclerView recyclerView;
    String Sname[],Saddress[],Scontact[];
    int images[]={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile,
            R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile,
            R.drawable.profile,R.drawable.profile,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.recyclerView);
        Sname = getResources().getStringArray(R.array.shop_name);
        Saddress = getResources().getStringArray(R.array.address);
        Scontact = getResources().getStringArray(R.array.contact);

        PaymentAdapter Padpt=new PaymentAdapter(this,Sname,Saddress,Scontact,images);
        recyclerView.setAdapter(Padpt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_payment;
    }

    @Override
    protected int getNavigationMenuItemId() {
        return R.id.navigation_payment;
    }
}