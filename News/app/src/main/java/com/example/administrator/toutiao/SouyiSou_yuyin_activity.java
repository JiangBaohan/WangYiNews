package com.example.administrator.toutiao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.toutiao.Bean.XFBean;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;

public class SouyiSou_yuyin_activity extends AppCompatActivity {
private  StringBuilder mStringBuilder;
    private EditText yuyin_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_souyi_sou_yuyin_activity);
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=59954040");

        yuyin_et = (EditText) findViewById(R.id.yuyin_et);
        Button yuyin_btn= (Button) findViewById(R.id.yuyin_button);
        yuyin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.创建RecognizerDialog对象,第二参数就是一个初始化的监听,我们用不上就设置为null
                RecognizerDialog mDialog = new RecognizerDialog(SouyiSou_yuyin_activity.this,null);
                //2.设置accent、language等参数
                mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//设置为中文模式
                mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");//设置为普通话模式
                //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
                //mDialog.setParameter("asr_sch", "1");
                //mDialog.setParameter("nlp_version", "2.0");
                //创建一个装每次解析数据的容器(你说话有停顿,解析就是一段一段的,而用容器就能拼接成一句话了)
                mStringBuilder = new StringBuilder();
                //3.设置回调接口,语音识别后,得到数据,做响应的处理.
                mDialog.setListener(new RecognizerDialogListener() {
                    //识别成功执行 参数1 recognizerResult:识别出的结果,Json格式(用户可参见附录12.1)
                    // 参数2 b:等于true时会话结束。方法才不会继续回调
                    //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加,(关于解析Json的代码可参见MscDemo中JsonParser类)
                    public void onResult(RecognizerResult recognizerResult, boolean b) {
                        //拿到讯飞识别的结果
                        String resultString = recognizerResult.getResultString();
                        System.out.println("讯飞识别的结果 "+resultString);
                        //自定义解析bean数据的一个方法.用到了Gson,在项目里关联一下.
                        String text = parseData(resultString);
                        //容器装解析出来的数据
                        mStringBuilder.append(text);
                        //对参数2进行判断,如果为true,代表这个方法不会再回调,就把容器里的数据转成字符串.拿来使用.
                        if(b){
                            String result = mStringBuilder.toString();
                            yuyin_et.setText(result);
                            System.out.println("话说完,最终的结果 : "+ result);
                        }
                    }
                    @Override//识别失败执行 speechError:错误码
                    public void onError(SpeechError speechError) {
                    }
                });
                //4.显示dialog，接收语音输入
                mDialog.show();

            }
        });
    }
    private String parseData(String resultString) {
        //创建gson对象,记得要关联一下gson.jar包方可以使用.
        Gson gson = new Gson();
        //参数 1.String类型的json数据  参数 2.存放json数据对应的bean类
        XFBean xfBean = gson.fromJson(resultString, XFBean.class);
        //创建一个集合,用来存放bean类里的对象.
        ArrayList<XFBean.WS> ws = xfBean.ws;
        //创建一个容器,用来存放从每个集合里拿到的数据,使用StringBuilder效率更高
        StringBuilder stringBuilder = new StringBuilder();
        //使用高级for循环,取出特定属性的特有数据,装入StringBuilder中
        for ( XFBean.WS w: ws) {
            String text = w.cw.get(0).w;
            stringBuilder.append(text);
        }
        //把容器内的数据转为字符串返回出去.
        return stringBuilder.toString();
    }
}
