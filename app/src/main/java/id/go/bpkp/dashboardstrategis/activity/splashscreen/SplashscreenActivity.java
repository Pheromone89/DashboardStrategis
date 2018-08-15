package id.go.bpkp.dashboardstrategis.activity.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import id.go.bpkp.dashboardstrategis.R;
import id.go.bpkp.dashboardstrategis.activity.login.LoginActivity;

public class SplashscreenActivity extends AppCompatActivity {

    private static int splashInterval = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splashscreen);

        ImageView loginProgress = findViewById(R.id.splash_progress);
        Glide.with(this).asGif().load(R.raw.logo_bpkp_animated).into(loginProgress);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(SplashscreenActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                this.finish();
            }

            private void finish() {
            }
        }, splashInterval);
    }
}
