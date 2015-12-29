package com.myshops.shops.myshops;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChoosePlaceActivity extends AppCompatActivity implements
        GeocodeSearch.OnGeocodeSearchListener,AMap.OnCameraChangeListener {
    //声明变量
    private MapView mapView;
    private AMap aMap;
    private UiSettings mUiSettings;

    public static double x,y;     //经纬度
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    //设置是否返回地址信息（默认返回地址信息）
    public String city;
    SharedPreferences sp;
    float f = (float) 16.0;
    public static LatLng latLng;
    Button btn_chooseplace_back;

    //
    private String addressName;
    private LatLonPoint latLonPoint = new LatLonPoint(39.90865, 116.39751);
    private GeocodeSearch geocoderSearch;
    private TextView text_chooseplace_place;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_place);

        btn_chooseplace_back = (Button) findViewById(R.id.btn_chooseplace_back);
        text_chooseplace_place = (TextView) findViewById(R.id.text_chooseplace_place);

        //在onCreat方法中给aMap对象赋值
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 必须要写
        if (aMap == null) {
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setCompassEnabled(true);
            mUiSettings.setScaleControlsEnabled(true);
            mUiSettings.setTiltGesturesEnabled(false);
            mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
        }
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        dingwei();

        aMap.setOnCameraChangeListener(this);

        /**
         * 对单击地图事件回调
         */
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.i("onMapClick","-----onMapClick-----"+latLng+"");

                Toast.makeText(ChoosePlaceActivity.this,"位置已选择，请返回",Toast.LENGTH_SHORT).show();
                btn_chooseplace_back.setVisibility(View.VISIBLE);

                //改变地图视图为自己的位置
                ChoosePlaceActivity.this.latLng = latLng;
                addMarkersToMap(); // 往地图上添加marker

                x = latLng.latitude;
                y = latLng.longitude;
                Intent data = new Intent();
                data.putExtra("weidu", x);
                data.putExtra("jingdu", y);
                setResult(3, data);

                latLonPoint = new LatLonPoint(x,y);
                getAddress(latLonPoint);

                // 获取当前地图中心点的坐标
                float mZoom = aMap.getCameraPosition().zoom;
                Log.i("suofanfang", mZoom + "");
            }
        });
    }

    public void choosePlaceBack(View view){
        this.finish();
    }


    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {
        Log.i("ampmapclikc", "响应逆地理编码");
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 100,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
    }
    /**
     * 逆地理编码回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        Log.i("ampmapclikc", "逆地理编码回调");
        if (rCode == 0) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {

                text_chooseplace_place.setVisibility(View.VISIBLE);
                addressName = result.getRegeocodeAddress().getFormatAddress();
                text_chooseplace_place.setText(addressName);


            } else {

            }
        } else if (rCode == 27) {

        } else if (rCode == 32) {

        } else {

        }
    }

    /**
     * 地理编码查询回调
     */
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {

    }

    // 移动地图
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

        x = cameraPosition.target.latitude;
        y = cameraPosition.target.longitude;
        //改变地图视图为自己的位置
        ChoosePlaceActivity.this.latLng = cameraPosition.target;
        addMarkersToMap(); // 往地图上添加marker
    }

    //移动地图结束
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        latLonPoint = new LatLonPoint(cameraPosition.target.latitude,cameraPosition.target.longitude);
        getAddress(latLonPoint);
        Toast.makeText(ChoosePlaceActivity.this,"位置已选择，请返回",Toast.LENGTH_SHORT).show();
        btn_chooseplace_back.setVisibility(View.VISIBLE);
        ChoosePlaceActivity.this.latLng = cameraPosition.target;
        addMarkersToMap();
    }

    /**
     * 定位
     *
     * */
    public void dingwei(){
        //定位
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听

        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {

                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        Log.i("vv", amapLocation.getLatitude() + "");
                        amapLocation.getLatitude();//获取经度
                        x = amapLocation.getLatitude();
                        Log.i("vv", amapLocation.getLongitude() + "");
                        amapLocation.getLongitude();//获取纬度
                        y = amapLocation.getLongitude();

                        Intent data = new Intent();
                        data.putExtra("weidu", x);
                        data.putExtra("jingdu", y);
                        setResult(3, data);

                        init();

                        amapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间
                        //  mLocationOption.setNeedAddress(true);
                        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                        amapLocation.getCountry();//国家信息
                        amapLocation.getProvince();//省信息
                        amapLocation.getCity();//城市信息

                        amapLocation.getDistrict();//城区信息
                        amapLocation.getRoad();//街道信息
                        amapLocation.getCityCode();//城市编码
                        amapLocation.getAdCode();//地区编码
                        city = amapLocation.getCity();

                        Log.i("AmapError", amapLocation.getDistrict() + df.format(date) + amapLocation.getCity());
                        String s = amapLocation.getCity();
                        //Ttv.setText(s);

                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.i("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }


    public void init(){

        //改变地图视图为自己的位置
        latLng = new LatLng(x, y);
        LatLng mTarget = aMap.getCameraPosition().target;
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, f));
        Log.i("logss",""+mTarget);
        addMarkersToMap(); // 往地图上添加marker

        // 获取当前地图中心点的坐标
        float mZoom = aMap.getCameraPosition().zoom;

        Log.i("suofanfang", mZoom + "");

    }


    private void addMarkersToMap() {
        //文字显示标注，可以设置显示内容，位置，字体大小颜色，背景色旋转角度
        TextOptions textOptions = new TextOptions().position(latLng)
                .text("Text").fontColor(Color.BLACK)
                .backgroundColor(Color.BLUE).fontSize(30).rotate(20).align(Text.ALIGN_CENTER_HORIZONTAL, Text.ALIGN_CENTER_VERTICAL)
                .zIndex(1.f).typeface(Typeface.DEFAULT_BOLD)
                ;

        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .visible(true)
                .title("123123")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.qb_group_troop_location_pin));
        aMap.clear();
        aMap.addMarker(markerOptions);

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}
