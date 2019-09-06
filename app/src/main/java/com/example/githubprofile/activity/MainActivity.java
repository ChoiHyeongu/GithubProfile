package com.example.githubprofile.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubprofile.R;
import com.example.githubprofile.model.GithubProfile;
import com.example.githubprofile.presenter.Presenter;
import com.example.githubprofile.presenter.ProfilePresenter;
import com.example.githubprofile.util.GithubProfileCallback;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Presenter.View, GithubProfileCallback {

    private static final String TAG = "MainActivity";

    ProfilePresenter profilePresenter;

    ImageView profileImage;
    TextView username;
    TextView maxContribution;
    TextView todayContribution;
    TextView repoCount;
    TextView followers;
    TextView following;
    TextView bio;

    Handler handler = new Handler();
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        initView();

        profilePresenter = new ProfilePresenter(this);
        profilePresenter.attachView(this);
        profilePresenter.getUserInfo(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profilePresenter.detachView();
    }

    private void initView(){
        profileImage = findViewById(R.id.main_img_profile);
        username = findViewById(R.id.main_text_username);
        maxContribution = findViewById(R.id.main_text_max_contribution);
        todayContribution = findViewById(R.id.main_text_today_contribution);
        repoCount = findViewById(R.id.main_text_repo);
        followers = findViewById(R.id.main_text_followers);
        following = findViewById(R.id.main_text_following);
        bio = findViewById(R.id.main_text_bio);
    }

    private void checkPermissions(){
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("API를 위해 권한을 허락해주세요.")
                .setDeniedMessage("거부하셨습니다.\n[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                .setPermissions(Manifest.permission.INTERNET)
                .check();
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(getApplicationContext(),"승인이 허가 되었습니다.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(getApplicationContext(), "승인 받지 못했습니다.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void setProfile(GithubProfile githubProfile) {

        Log.d(TAG, "MAX : " + githubProfile.getMaxContribution()
                + "\nTODAY : " + githubProfile.getTodayContribution()
                + "\nREPOS : " + githubProfile.getRepo()
                + "\nFOLLOWING, FOLLOWERS : " + githubProfile.getFollowing() + ", " + githubProfile.getFollowers()
                + "\nProflie Image : " + githubProfile.getImage());

        setProfileImage(githubProfile.getImage());
        username.setText(githubProfile.getUsername());
        maxContribution.setText(githubProfile.getMaxContribution());
        todayContribution.setText(githubProfile.getTodayContribution());
        repoCount.setText(githubProfile.getRepo());
        followers.setText(githubProfile.getFollowers());
        following.setText(githubProfile.getFollowing());
        bio.setText(githubProfile.getBio());
    }

    private void setProfileImage(final String imageURL){

        Log.d(TAG, "setProfileImage");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "setProfileImage");
                    URL url = new URL(imageURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();

                    InputStream in = url.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(in);
                    final RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                    roundedBitmapDrawable.setCornerRadius(3f);
                    roundedBitmapDrawable.setAntiAlias(true);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            profileImage.setImageDrawable(roundedBitmapDrawable);
                        }
                    });
                } catch (Exception e){
                    Log.d(TAG, "setProfileImage : Error");
                    e.printStackTrace();
                }
            }
        });

        t.start();

        try{
            t.join();
            profileImage.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(GithubProfile githubProfile) {
        setProfile(githubProfile);
    }

    @Override
    public void onError() {
        Log.d(TAG, "Fail to receive profile");
    }
}
