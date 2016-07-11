package com.slash.simplelife.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.slash.multi_image_selector.MultiImageSelectorActivity;
import com.slash.simplelife.Model.UserModel;
import com.slash.simplelife.R;
import com.slash.simplelife.base.BaseActivity;
import com.slash.simplelife.event.BgImgUpdateEvent;
import com.slash.simplelife.util.MemberUtil;
import com.slash.simplelife.util.PreferencesUtils;
import com.slash.simplelife.util.permission.MPermission;
import com.slash.simplelife.util.permission.PermissionListener;
import com.slash.simplelife.util.permission.PermissionOptions;
import com.slash.simplelife.view.EditDialogFragment;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener, EditDialogFragment.LoginInputListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.book_detail_img)
    ImageView img;
    @Bind(R.id.main_fab_menu)
    FloatingActionMenu mFloatingActionMenu;
//    @Bind(R.id.main_fab_select_img)
//    FloatingActionButton mSelectImg;
    @Bind(R.id.main_fab_edit_nickname)
    FloatingActionButton mEditNickname;
    @Bind(R.id.main_fab_editintro)
    FloatingActionButton mEditIntro;
    @Bind(R.id.main_fab_bgimg)
    FloatingActionButton mBgImg;
    @Bind(R.id.first_content)
    TextView firstContent;
    @Bind(R.id.second_content)
    TextView secondContent;

    private int contentChoice = 0;
    private String picPath;
    private Boolean readSD=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        ButterKnife.bind(this);
        initPermission();
        initData();
        initView();
    }

    private void initPermission() {
        MPermission.getInstance(this).request(new PermissionOptions.Builder()
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .build(),
                new PermissionListener() {
                    @Override
                    public void onGranted() {
                        readSD = true;
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        readSD = false;
                        mBgImg.setClickable(false);
//                        ToastUtils.show(PersonalCenterActivity.this, permissions.toString() + R.string.permission_denied);
                        showToast(permissions.toString() + R.string.permission_denied);
                    }
                });
    }

    @Override
    protected void initView() {
        toolbar.setTitle(R.string.drawer_item_center);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_actionbar_back);
        toolbar.setBackgroundColor(getResources().getColor(R.color.purple));
//        mSelectImg.setOnClickListener(this);
        mEditNickname.setOnClickListener(this);
        mEditIntro.setOnClickListener(this);
        mBgImg.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        getUserInfo();
    }

    private void getUserInfo() {
        BmobQuery<UserModel> query = new BmobQuery<>();
        query.addWhereEqualTo("username", PreferencesUtils.getString(this, USER_NAME));
        query.findObjects(this, new FindListener<UserModel>() {
            @Override
            public void onSuccess(List<UserModel> object) {
                // TODO Auto-generated method stub
                firstContent.setText(object.get(0).getNickname());
                secondContent.setText(object.get(0).getContent());
                if(PreferencesUtils.getString(PersonalCenterActivity.this,BG_IMG)==null){
                    Glide.with(PersonalCenterActivity.this).load(RANDOM_IMG).centerCrop().into(img);
                }else{
                    Glide.with(PersonalCenterActivity.this).load(new File(PreferencesUtils.getString(PersonalCenterActivity.this,BG_IMG))).centerCrop().into(img);
                }
                MemberUtil.saveMember(PersonalCenterActivity.this, object.get(0).getNickname(), object.get(0).getContent());
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.main_fab_select_img:
//                break;
            case R.id.main_fab_edit_nickname:
                contentChoice = 1;
                showEditDialog();
                break;
            case R.id.main_fab_editintro:
                contentChoice = 2;
                showEditDialog();
                break;
            case R.id.main_fab_bgimg:
                if(readSD){
                    selectImg();
                }else {
//                    ToastUtils.show(PersonalCenterActivity.this,R.string.sorry_for_permission);
                    showToast(getResources().getText(R.string.sorry_for_permission));
                }
                break;
        }
        mFloatingActionMenu.toggle(false);
    }

    /**
     * 选择图片
     */
    public void selectImg(){
        MultiImageSelectorActivity.startSelect(PersonalCenterActivity.this,
                RESULT_CHOOSE_PHOTO, 1, MultiImageSelectorActivity.MODE_SINGLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CHOOSE_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> mSelectPath =
                        data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);

                for (String p : mSelectPath) {
                    Picasso.with(PersonalCenterActivity.this)
                            .load(new File(p))
                            .resize(200, 200)
                            .centerCrop()
                            .into(img);
                    picPath = p;
                    PreferencesUtils.putString(PersonalCenterActivity.this,BG_IMG,picPath);
                    EventBus.getDefault().post(new BgImgUpdateEvent(picPath));
                }
            }
        }
    }

    private void showEditDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        EditDialogFragment dialog = new EditDialogFragment();
        dialog.show(fragmentManager, "dialog");
    }

    @Override
    public void onLoginInputComplete(String content) {
        if (contentChoice == 1) {
            firstContent.setText(content);
        } else if (contentChoice == 2) {
            secondContent.setText(content);
        }
        updateContent2Http(content);
    }

    public void updateContent2Http(String content) {
        UserModel newUser = new UserModel();
        if (contentChoice == 1) {
            newUser.setNickname(content);
        } else if (contentChoice == 2) {
            newUser.setContent(content);
        }
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        newUser.update(this, bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                MemberUtil.saveMember(PersonalCenterActivity.this, firstContent.getText().toString(), secondContent.getText().toString());
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart("PersonalCenterActivity_Start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd("PersonalCenterActivity_End");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MemberUtil.saveMember(PersonalCenterActivity.this, firstContent.getText().toString(), secondContent.getText().toString());
        ButterKnife.unbind(this);
    }
}
