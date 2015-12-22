package com.myshops.shops.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.myshops.shops.adapter.ClientFragmentAdapter;
import com.myshops.shops.bean.Conmments;
import com.myshops.shops.myshops.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 卖家 ———— 客户管理框架
 * */
public class ClientFragment extends Fragment {

    ClientFragmentAdapter cfAdapter;
    List<Conmments> conmmentsList = new ArrayList<>();
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_client, container, false);
        View view = inflater.inflate(R.layout.fragment_client, container, false);

        listView = (ListView) view.findViewById(R.id.listView_client_fragment);

        conmmentsList.add(new Conmments("用户名","单价",14,"差评","商品名称"));
        cfAdapter = new ClientFragmentAdapter(getActivity().getApplicationContext(), conmmentsList);
        listView.setAdapter(cfAdapter);

        return view;
    }

}
