package com.slash.simplelife.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.afollestad.materialdialogs.MaterialDialog;
import com.slash.simplelife.R;
import com.slash.simplelife.base.Constant;
import com.slash.simplelife.event.LogonOutEvent;
import com.slash.simplelife.util.AppInfoUtil;
import com.slash.simplelife.util.MemberUtil;
import com.slash.simplelife.util.PreferencesUtils;
import com.slash.simplelife.util.ToastUtils;
import com.slash.simplelife.view.niftydialog.Effectstype;
import com.slash.simplelife.view.niftydialog.NiftyDialogBuilder;

import org.greenrobot.eventbus.EventBus;

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener,Constant {
    private Preference aboutApp;
    private Preference appVersion;
    private SwitchPreference loginOut;
    private Effectstype effect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        aboutApp = findPreference(ABOUT_APP);
        appVersion = findPreference(APP_VERSION);
        loginOut = (SwitchPreference) findPreference(LOGIN_OUT);

        appVersion.setTitle(AppInfoUtil.getVersionName(getActivity()));
        loginOut.setChecked(false);
        loginOut.setOnPreferenceChangeListener((preference, newValue) -> {
            if ((Boolean) newValue) {
                final NiftyDialogBuilder dialogBuilder = new NiftyDialogBuilder(getActivity(), R.style.dialog_untran);
                effect = Effectstype.RotateBottom;
                try {
                    dialogBuilder
                            .withTitle("温馨提示")
                            .withTitleColor("#FFFFFF")
                            .withDividerColor("#11000000")
                            .withMessage("确认退出当前账号吗？")
                            .withMessageColor("#594691")
                            .withIcon(getResources().getDrawable(R.drawable.chrome_google_96px))
                            .isCancelableOnTouchOutside(true)
                            .withDuration(700)
                            .withEffect(effect)
                            .withButton1Text("确认")
                            .withButton2Text("取消")
                            .setButton1Click(v1 -> {
                                if (dialogBuilder != null) {
                                    dialogBuilder.dismiss();
                                    MemberUtil.clearMember(getActivity());
                                    PreferencesUtils.putString(getActivity(), BG_IMG, null);
                                    EventBus.getDefault().post(new LogonOutEvent(RANDOM_IMG));
                                    ToastUtils.show(getActivity(), "已退出当前账号");
                                    getActivity().finish();
                                }
                            })
//                            .setButton2Click(v1 -> dialogBuilder.dismiss())
                            .setButton2Click(view -> {
                                loginOut.setChecked(false);
                                dialogBuilder.dismiss();
                            })
                            .show();
                } catch (Exception e) {
                }
            }
            return true;
        });
        aboutApp.setOnPreferenceClickListener(this);
        appVersion.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();

        if(key.equals(ABOUT_APP)) {

                    new MaterialDialog.Builder(getActivity())
                    .title("老司机的简单生活")
                    .backgroundColor(getResources().getColor(R.color.purple))
                    .contentColor(getResources().getColor(R.color.white))
                    .positiveColor(getResources().getColor(R.color.white))
                    .negativeColor(getResources().getColor(R.color.white))
                    .neutralColor(getResources().getColor(R.color.white))
                    .titleColor(getResources().getColor(R.color.white))
                    .content("我是老司机，欢迎加我QQ41014314，一起捡肥皂哟^_^")
                    .positiveText("GitHub")
                    .neutralText("OSChina")
                    .onPositive((dialog1, which) -> {
                        // TODO
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TangStudio/SimpleLife")));
                        dialog1.dismiss();
                    })
                    .onNeutral((dialog1, which) -> {
                        // TODO
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://git.oschina.net/EffectiveLife/SimpleLife")));
                    })
                    .build().show();
        }else if(key.equals(APP_VERSION)){
                    new MaterialDialog.Builder(getActivity())
                    .title("致敬：")
                    .backgroundColor(getResources().getColor(R.color.green))
                    .contentColor(getResources().getColor(R.color.white))
                    .positiveColor(getResources().getColor(R.color.white))
                    .titleColor(getResources().getColor(R.color.white))
                    .content("向此作用到的开源库作者致敬（在此不一一列举）")
                    .positiveText("确认")
                    .onPositive((dialog1, which) -> {
                        // TODO
                        dialog1.dismiss();
                    })
                    .build().show();
        }
        return true;
    }
}
