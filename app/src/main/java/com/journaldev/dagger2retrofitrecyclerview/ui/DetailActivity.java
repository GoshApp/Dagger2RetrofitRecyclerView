package com.journaldev.dagger2retrofitrecyclerview.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.journaldev.dagger2retrofitrecyclerview.MyApplication;
import com.journaldev.dagger2retrofitrecyclerview.R;
import com.journaldev.dagger2retrofitrecyclerview.di.component.ApplicationComponent;
import com.journaldev.dagger2retrofitrecyclerview.di.component.DaggerDetailActivityComponent;
import com.journaldev.dagger2retrofitrecyclerview.di.component.DetailActivityComponent;
import com.journaldev.dagger2retrofitrecyclerview.di.qualifier.ApplicationContext;
import com.journaldev.dagger2retrofitrecyclerview.retrofit.APIInterface;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    DetailActivityComponent detailActivityComponent;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    TextInputEditText txtCellPhone;
    TextInputEditText txtEmail;
    TextInputEditText txtCity;
    TextView txtFullName;
    TextView txtYearsOld;
    CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtCellPhone = findViewById(R.id.txt_cell_phone);
        txtEmail = findViewById(R.id.txt_email);
        txtCity = findViewById(R.id.txt_city);
        txtFullName = findViewById(R.id.txt_full_name);
        txtYearsOld = findViewById(R.id.txt_years_old);
        profileImage = findViewById(R.id.profile_image);

        String url = getIntent().getStringExtra("url");

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        detailActivityComponent = DaggerDetailActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build();

        detailActivityComponent.inject(this);


//        apiInterface.getUser().enqueue(new Callback<RandomUser>() {
//            @Override
//            public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
//                txtCellPhone.setText(response.body().getResults().get(0).getPhone());
//                txtEmail.setText(response.body().getResults().get(0).getEmail());
//                txtCity.setText(response.body().getResults().get(0).getLocation().getCity());
//                txtFullName.setText(response.body().getResults().get(0).getName().getFirst() + " " + response.body().getResults().get(0).getName().getLast());
//                txtYearsOld.setText(response.body().getResults().get(0).getDob().getAge().toString());
//
//                Picasso.with(getApplicationContext()).load(response.body().getResults().get(0).getPicture().getLarge())
//                        .into(profileImage);
//            }
//
//            @Override
//            public void onFailure(Call<RandomUser> call, Throwable t) {
//
//            }
//        });

    }
}
