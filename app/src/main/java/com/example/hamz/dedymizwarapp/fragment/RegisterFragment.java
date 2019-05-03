package com.example.hamz.dedymizwarapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.activity.MessageActivity;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.helper.PrefManager;
import com.example.hamz.dedymizwarapp.list.UserList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    private String TAG = RegisterFragment.class.getSimpleName();

    private EditText etName, etEmail, etPassword, etConfirm;
    private Button btnRegister;

    AlertDialog dialog;
    ApiInterface apiInterface;
    PrefManager prefManager;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        dialog = new SpotsDialog(view.getContext());
        apiInterface = new ApiClient().getClient().create(ApiInterface.class);
        prefManager = new PrefManager(view.getContext());

        etName = view.findViewById(R.id.name);
        etEmail = view.findViewById(R.id.email);
        etPassword = view.findViewById(R.id.password);
        etConfirm = view.findViewById(R.id.confirm);

        btnRegister = view.findViewById(R.id.register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        return view;
    }

    private void register()
    {
        dialog.show();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        apiInterface.register(name, email, password).enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
                    dialog.dismiss();

                    if (response.body().getMessage() != null)
                        Toast.makeText(RegisterFragment.this.getContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    else {

                        prefManager.setIsLogged(true);
                        prefManager.setId(response.body().getId());
                        prefManager.setToken(response.body().getToken());
                        prefManager.setName(response.body().getName());
                        prefManager.setEmail(response.body().getEmail());

                        Intent intent = new Intent(RegisterFragment.this.getContext(), MessageActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}
