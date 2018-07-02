package com.newolf.patternlocker.utils;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.newolf.patternlocker.config.Config;

import java.util.List;

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * date :  2018/6/29
 * desc:
 * history:
 * ================================================
 */
public class PatternHelper {

    private int MAX_SIZE;
    private int MAX_TIMES;
    private String GESTURE_PWD_KEY;

    private String message;
    private String storagePwd;
    private String tmpPwd;
    private int times;
    private boolean isFinish;
    private boolean isOk;
    SecurityUtil securityUtil;


    private SharedPreferencesUtil mSp;

    private PatternHelper(Builder builder) {
        MAX_SIZE = builder.mSize;
        MAX_TIMES = builder.mRetrySize;
        GESTURE_PWD_KEY = builder.mSpName;

        mSp = SharedPreferencesUtil.getInstance(builder.mSpName, builder.mApplication);
        securityUtil = SecurityUtil.getInstance(builder.mMasterPassword);
    }

    public void validateForSetting(List<Integer> hitList) {
        this.isFinish = false;
        this.isOk = false;

        if ((hitList == null) || (hitList.size() < MAX_SIZE)) {
            this.tmpPwd = null;
            this.message = getSizeErrorMsg();
            return;
        }

        //1. draw first time
        if (TextUtils.isEmpty(this.tmpPwd)) {
            this.tmpPwd = convert2String(hitList);
            Log.e("tmpPwd", tmpPwd);
            this.message = getReDrawMsg();
            this.isOk = true;
            return;
        }

        //2. draw second times
        if (this.tmpPwd.equals(convert2String(hitList))) {
            this.message = getSettingSuccessMsg();
            saveToStorage(this.tmpPwd);
            this.isOk = true;
            this.isFinish = true;
        } else {
            this.tmpPwd = null;
            this.message = getDiffPreErrorMsg();
        }
    }

    public void validateForChecking(List<Integer> hitList) {
        this.isOk = false;

        if ((hitList == null) || (hitList.size() < MAX_SIZE)) {
            this.times++;
            this.isFinish = this.times >= MAX_SIZE;
            this.message = getPwdErrorMsg();
            return;
        }

        this.storagePwd = getFromStorage();
        if (!TextUtils.isEmpty(this.storagePwd) && this.storagePwd.equals(convert2String(hitList))) {
            this.message = getCheckingSuccessMsg();
            this.isOk = true;
            this.isFinish = true;
        } else {
            this.times++;
            this.isFinish = this.times > MAX_SIZE;
            this.message = getPwdErrorMsg();
        }
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public boolean isOk() {
        return isOk;
    }

    private String getReDrawMsg() {
        return "请再次绘制解锁图案";
    }

    private String getSettingSuccessMsg() {
        return "手势解锁图案设置成功！";
    }

    private String getCheckingSuccessMsg() {
        return "解锁成功！";
    }

    private String getSizeErrorMsg() {
        return String.format("至少连接个%d点，请重新绘制", MAX_SIZE);
    }

    private String getDiffPreErrorMsg() {
        return "与上次绘制不一致，请重新绘制";
    }

    private String getPwdErrorMsg() {
        if (getRemainTimes() == 0) {
            return "密码错误,请重新登录";
        }
        return String.format("密码错误，还剩%d次机会", getRemainTimes());
    }

    private String convert2String(List<Integer> hitList) {
        return hitList.toString();
    }

    private void saveToStorage(String gesturePwd) {
        final String encryptPwd = securityUtil.encrypt(gesturePwd);
        Log.e("tmpPwd", "tmpPwd encryptPwd =" + encryptPwd);

        mSp.saveString(GESTURE_PWD_KEY, encryptPwd);
    }

    private String getFromStorage() {
        final String result = mSp.getString(GESTURE_PWD_KEY);
        return securityUtil.decrypt(result);
    }

    private int getRemainTimes() {
        return (times < 5) ? (MAX_TIMES - times) : 0;
    }

    public void clear() {
        mSp.saveString(GESTURE_PWD_KEY, null);
    }


    public static class Builder {
        private int mSize;
        private int mRetrySize;
        private String mSpName;
        private String mMasterPassword;
        private Application mApplication;



        public Builder setMaxSize(int size) {
            mSize = size;
            return this;
        }

        public Builder setRetrySize(int retrySize) {
            mRetrySize = retrySize;
            mRetrySize = Config.getDefaultRetrySize();
            return this;
        }

        public Builder setSpName(@NonNull String spName) {
            mSpName = spName;
            return this;
        }

        public Builder setMasterPassword(@NonNull String masterPassword) {
            mMasterPassword = masterPassword;
            return this;
        }

        public Builder setApplication(@NonNull Application application) {
            mApplication = application;
            return this;
        }

        public PatternHelper build() {
            if (mSize < 1 || mSize > 9) {
                mSize = Config.getDefaultMaxSize();
            }

            if (mRetrySize < 1 || mRetrySize > 10) {
                mRetrySize = Config.getDefaultRetrySize();
            }

            if (TextUtils.isEmpty(mSpName)) {
                mSpName = Config.getDefaultDelaySpName();
            }
            if (TextUtils.isEmpty(mMasterPassword)) {
                mMasterPassword = Config.getDefaultMasterPassword();
            }

            if (mApplication == null) {
                throw new NullPointerException("application is null,you can setApplication(application) first");
            }


            return new PatternHelper(this);
        }
    }


}
