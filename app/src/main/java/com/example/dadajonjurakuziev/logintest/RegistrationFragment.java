package com.example.dadajonjurakuziev.logintest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    private static final String TAG = "RegistrationFragment";

    private EditText Name, UserName, UserPassword;
    private Button btnSignUp;


    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        Name = view.findViewById(R.id.txt_name);
        UserName = view.findViewById(R.id.txt_user_name);
        UserPassword = view.findViewById(R.id.txt_password);
        btnSignUp = view.findViewById(R.id.signupBtn);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on SIGN UP button.");
                performRegistration();
            }
        });

        return view;
    }

    public void performRegistration(){
        Log.d(TAG, "performRegistration: started REGISTRATION");
        String name = Name.getText().toString();
        String user_name = UserName.getText().toString();
        String user_password = UserPassword.getText().toString();

        Call<User> call = MainActivity.apiInterface.performRegistration(name, user_name, user_password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){
                    MainActivity.prefConfig.displayToast("Registration SUCCEEDED");
                }
                else if (response.body().getResponse().equals("exist")) {
                    MainActivity.prefConfig.displayToast("User already exist");
                }
                else if (response.body().getResponse().equals("error")){
                    MainActivity.prefConfig.displayToast("Registration FAILED");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.prefConfig.displayToast("FATAL ERROR "+t);
            }
        });
        Name.setText("");
        UserName.setText("");
        UserPassword.setText("");
    }

}
