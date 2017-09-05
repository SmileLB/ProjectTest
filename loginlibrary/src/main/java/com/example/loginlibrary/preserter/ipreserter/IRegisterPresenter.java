package com.example.loginlibrary.preserter.ipreserter;


import com.example.loginlibrary.base.BasePresenter;
import com.example.loginlibrary.base.BaseView;
import com.example.loginlibrary.impl.SuperInterface;

public abstract class IRegisterPresenter implements BasePresenter {
	protected SuperInterface mBaseView;

	public IRegisterPresenter(SuperInterface baseView) {
		mBaseView = baseView;
	}


	protected abstract boolean checkNormalRegister();


	public abstract void normalRegister(String username,String password);



	public interface IRegisterView extends BaseView {

		/**
		 * 注册成功
		 */
		void onSuccess(String username);

		/**
		 * 注册失败
		 *
		 * @param code 错误码
		 * @param msg  错误信息
		 */
		void onFailure(int code, String msg);

	}

}
