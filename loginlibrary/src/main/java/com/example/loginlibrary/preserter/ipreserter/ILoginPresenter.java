package com.example.loginlibrary.preserter.ipreserter;


import com.example.loginlibrary.base.BasePresenter;
import com.example.loginlibrary.base.BaseView;
import com.example.loginlibrary.impl.SuperInterface;

public abstract class ILoginPresenter implements BasePresenter {
    protected SuperInterface mBaseView;

    public ILoginPresenter(SuperInterface baseView) {
        mBaseView = baseView;
    }

    /**
     * 检查用户名密码合理性

     */
    public abstract boolean checkUserNameLogin(String username,String password);

    public abstract void usernameLogin(String username,String password);



    public interface ILoginView extends BaseView {
        /**
         * 登陆成功
         */
        public void loginSuccess();

        public void showMsg(String msg);

        /**
         * 登陆失败
         */
        public void loginFailed(int status, String msg);

        /**
         * 错误信息
         *
         * @param errorMsg
         */
        public void usernameError(String errorMsg);

        /**
         * 错误信息
         *
         * @param errorMsg
         */
        public void passwordError(String errorMsg);

    }
}
