package com.example.githubprofile.activity;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubprofile.R;
import com.example.githubprofile.model.GithubProfile;
import com.example.githubprofile.presenter.Presenter;
import com.example.githubprofile.presenter.ProfilePresenter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MainActivity extends AppCompatActivity implements Presenter.View {

    private static final String TAG = "MainActivity";
    ProfilePresenter profilePresenter;

    TextView username;
    TextView maxContribution;
    TextView todayContribution;
    TextView repoCount;
    TextView followers;
    TextView following;
    TextView bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        initView();

        profilePresenter = new ProfilePresenter(this);
        profilePresenter.attachView(this);
        profilePresenter.getUserInfo();
        GithubProfile githubProfile = profilePresenter.makeProfileInstance();
        //Log.d(TAG, githubProfile.getUsername());
        //setProfile(githubProfile);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profilePresenter.detachView();
    }

    private void initView(){
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
        username.setText(githubProfile.getUsername());
        maxContribution.setText(githubProfile.getMaxContribution());
        maxContribution.setText(githubProfile.getTodayContribution());
        repoCount.setText(githubProfile.getRepo());
        followers.setText(githubProfile.getFollowers());
        following.setText(githubProfile.getFollowing());
        bio.setText(githubProfile.getBio());
    }
}
