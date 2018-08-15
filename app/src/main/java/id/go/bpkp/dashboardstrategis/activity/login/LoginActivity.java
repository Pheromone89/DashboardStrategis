package id.go.bpkp.dashboardstrategis.activity.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.activity.dashboard.DashboardActivity;
import id.go.bpkp.dashboardstrategis.konfigurasi.Konfigurasi;
import id.go.bpkp.dashboardstrategis.konfigurasi.SavedInstance;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    CardView loginView;
    TextView loginButton;
    ImageView loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        initiateView();
        populateView();
        setOnClick();

    }

    private void initiateView(){
        usernameEditText = findViewById(R.id.login_username_input);
        passwordEditText = findViewById(R.id.login_password_input);
        loginView = findViewById(R.id.login_login_view);
        loginButton = findViewById(R.id.login_login_button);
        loginProgress = findViewById(R.id.login_login_progress);

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    validasiLogin();
                }
                return false;
            }
        });
    }

    private void populateView(){
        Glide.with(this).asGif().load(R.raw.logo_bpkp_animated).into(loginProgress);
    }

    private void setOnClick(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });
    }

    private void validasiLogin(){
        usernameEditText.setError(null);
        usernameEditText.setError(null);

        usernameEditText.setText("mas muhamad dzulfikar");
        passwordEditText.setText("132015");

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean cancel = false;

        if(TextUtils.isEmpty(username)){
            usernameEditText.setError("kolom ini harus diisi");
            cancel = true;
        }
        if(TextUtils.isEmpty(password)){
            passwordEditText.setError("kolom ini harus diisi");
            cancel = true;
        }

        if (!cancel){
            showLoading(true);
            loginAttempt(username, password);
        }
    }

    private void loginAttempt(final String username, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_POST_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("success").equals("true")){
                                String apiToken = jsonObject.getString("api_token");

                                Intent loginIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                                loginIntent.putExtra("api_token", apiToken);
                                SavedInstance.saveData(response);
                                startActivity(loginIntent);
                                showLoading(false);
                            } else {
                                String failedLoginMessage = jsonObject.getString("message");
                                if (failedLoginMessage.equals("Your username or password incorrect!")) {
                                    Snackbar.make(loginButton, "Username atau kata sandi yang Anda masukkan salah", Snackbar.LENGTH_LONG).setAction("Message", null).show();
                                } else if (failedLoginMessage.equals("Your password incorrect!")) {
                                    Snackbar.make(loginButton, "Kata sandi yang Anda masukkan salah", Snackbar.LENGTH_LONG).setAction("Message", null).show();
                                } else {
                                    Snackbar.make(loginButton, "Gagal melakukan Login", Snackbar.LENGTH_LONG).setAction("Message", null).show();
                                }
                                showLoading(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showLoading(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof AuthFailureError) {
                            Snackbar.make(loginButton, "Gagal mengotentifikasi", Snackbar.LENGTH_LONG).setAction("Message", null).show();
                        } else if (error instanceof ServerError) {
                            Snackbar.make(loginButton, "Masalah pada server", Snackbar.LENGTH_LONG).setAction("Message", null).show();
                        } else if (error instanceof TimeoutError) {
                            Snackbar.make(loginButton, "Waktu koneksi habis", Snackbar.LENGTH_LONG).setAction("Message", null).show();
                        } else if (error instanceof NetworkError) {
                            Snackbar.make(loginButton, "Gagal menghubungkan dengan jaringan", Snackbar.LENGTH_LONG).setAction("Message", null).show();
                        } else if (error instanceof ParseError) {
                            Snackbar.make(loginButton, "Gagal parsing data", Snackbar.LENGTH_LONG).setAction("Message", null).show();
                        }
                        showLoading(false);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json");
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void showLoading(boolean status){
        if(status){
            loginProgress.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
        } else {
            loginProgress.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
        }
    }

}
