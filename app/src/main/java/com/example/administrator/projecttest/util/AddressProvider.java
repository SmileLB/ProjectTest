package com.example.administrator.projecttest.util;

import android.content.Context;
import android.util.SparseArray;

import com.example.administrator.projecttest.bean.Address;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiBing
 * on 2017/8/11 0011
 * describe:
 */

public class AddressProvider {


    public static final String CART_JSON = "cart_json";

    private SparseArray<Address> datas = null;

    private Context mContext;

    public AddressProvider(Context context) {
        mContext = context;
        datas = new SparseArray<>(10);
        listToSparse();
    }

    private void listToSparse() {

        List<Address> carts = getDataFromLocal();

        if (carts != null && carts.size() > 0) {

            for (int i=0;i<carts.size();i++) {
                datas.put(i,carts.get(i));
            }
        }
    }

    public ArrayList<Address> getDataFromLocal() {
        String json = PreferencesUtils.getString(mContext, CART_JSON);
        ArrayList<Address> address = null;
        if (json != null) {
            address = JSONUtil.fromJson(json, new TypeToken<List<Address>>() {
            }.getType());
        }
        return address;
    }




    public List<Address> getAll() {
        return getDataFromLocal();
    }

    public void commit() {
        List<Address> carts = sparseToList();
        PreferencesUtils.putString(mContext, CART_JSON, JSONUtil.toJSON(carts));
    }


    public void save(List<Address> carts) {

        PreferencesUtils.putString(mContext, CART_JSON, JSONUtil.toJSON(carts));
    }

    private List<Address> sparseToList() {
        int size = datas.size();
        List<Address> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(datas.valueAt(i));
        }
        return list;
    }

    public Address convertData(Address item){

        Address cart = new Address();

        cart.setName(item.getName());
        cart.setAddress(item.getAddress());
        cart.setPhone(item.getPhone());

        return cart;
    }

    public void put(Address address){
        Address cart = convertData(address);
        put(cart);
    }
}
