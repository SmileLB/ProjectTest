package com.example.loginlibrary.base;

import android.content.Context;

/**
 * @description: MVP中View的基本接口
 */
public interface BaseView<T> {


	/**
	 * @description: 网络加载或耗时加载时界面显示
	 */
	void showLoading();

	/**
	 * @description: 网络加载或耗时加载完成时界面显示
	 */
	void dismissLoading();

	/**
	 * @description: 获取Context
	 */
	Context getContext();

}
