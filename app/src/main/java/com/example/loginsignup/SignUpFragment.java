package com.example.loginsignup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    private EditText etEmail , etPassword, etConfirmPassword;
    private Button btnSignup;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {
        etEmail = getView().findViewById(R.id.etEmailSignup);
        etPassword = getView().findViewById(R.id.etPasswordSignup);
        etConfirmPassword = getView().findViewById(R.id.etConfirmPasswordSignup);
        btnSignup = getView().findViewById(R.id.btnSignupSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if(email.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty())
                {
                    Toast.makeText(getActivity(),"Some fields are missing!",Toast.LENGTH_SHORT).show();
                }

                if ( !isEmailValid(email))
                {
                    Toast.makeText(getActivity(),"Email is incorrect!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( !isPasswordValid(password))
                {
                    Toast.makeText(getActivity(),"Password is incorrect!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword))
                {
                    Toast.makeText(getActivity(),"Password not identical!",Toast.LENGTH_SHORT).show();
                    return;
                }


            }
            private boolean isPasswordValid(String password)
            {
                Pattern pattern;
                Matcher matcher;
                final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(password);

                return matcher.matches();
            }

            public boolean isEmailValid( String email)
            {
                String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }
        });
    }
}