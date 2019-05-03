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

import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;


public class LoginFragment extends Fragment {

    private String TAG = LoginFragment.class.getSimpleName();

    EditText username, password;
    Button login;
    ApiInterface apiInterface;
    AlertDialog dialog;
    PrefManager prefManager;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);

        prefManager = new PrefManager(view.getContext());

        dialog = new SpotsDialog(view.getContext());

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proses_login();
            }
        });

        return view;
    }

    private void proses_login() {
        if (validate()) {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            dialog.show();
            apiInterface.login(user, pass).enqueue(new Callback<UserList>() {
                @Override
                public void onResponse(Call<UserList> call, Response<UserList> response) {
                    String http_status = Integer.toString(response.code()); // get http status

                    if (response.isSuccessful()) {
                        dialog.dismiss();

                        if (response.body().getMessage() != null)
                            Toast.makeText(LoginFragment.this.getContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        else {

                            prefManager.setIsLogged(true);
                            prefManager.setId(response.body().getId());
                            prefManager.setToken(response.body().getToken());
                            prefManager.setName(response.body().getName());
                            prefManager.setEmail(response.body().getEmail());

                            Intent intent = new Intent(LoginFragment.this.getContext(), MessageActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }

                    } else {
                        dialog.dismiss();
                        Toast.makeText(LoginFragment.this.getContext(), "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                    }

                    Log.d(TAG, "onResponse: HTTP status = " + http_status);
                }

                @Override
                public void onFailure(Call<UserList> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        } else {
            Toast.makeText(LoginFragment.this.getContext(), "Harap isi semua!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate() {
        if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
            return false;
        return true;
    }


}
