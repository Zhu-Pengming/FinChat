package com.example.financialmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import okhttp3.*;

public class ChatActivity extends AppCompatActivity {

    private static final int PICK_FILE = 1;
    private RecyclerView chatsRV;
    private ImageView sendMsgIB,sendCSVIB;
    private EditText userMsgEdt;
    private ArrayList<Message> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;

    private ImageView returnTo,setting;

    private static final String API_URL = "https://api.openai-proxy.org/v1/chat/completions";
    private static final String API_KEY = "sk-O1RiVN9ZHxJBfYfMruhixCrBJE72Ds9lhYKi2R1M2f0WKL8s";

    private WebView webView;

    private String USER_KEY = "user";
    private String BOT_KEY = "bot";

    private Uri uri;

    private OkHttpClient client;
    private Gson gson;

    private int REQUEST_CODE_PERMISSION = 2;

    private String currentType,targetType;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if(getSupportActionBar() !=null) {
            getSupportActionBar().hide();
        }

        currentType = getCurrentType();
        targetType = getTargetType();

        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);


        messageModalArrayList = new ArrayList<>();
        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        chatsRV.setLayoutManager(linearLayoutManager);
        chatsRV.setAdapter(messageRVAdapter);



        userMsgEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });



        returnTo = findViewById(R.id.chat_return);
        returnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
        setting = findViewById(R.id.chat_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, InstructionActivity.class);
                startActivity(intent);
            }
        });



        webView = findViewById(R.id.tes);
        webView.clearCache(true);
        webView.loadUrl("file:///android_asset/tea.html");
        webView.setVisibility(View.GONE);



        // 添加机器人的欢迎消息
        addBotWelcomeMessage();

        sendCSVIB =findViewById(R.id.idIBPicture);
        //触发反应
        sendCSVIB = findViewById(R.id.idIBPicture);
        sendCSVIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.setVisibility(View.VISIBLE);
                readFromAssets();
            }
        });





        sendMsgIB.setOnClickListener(v -> {

            if (userMsgEdt.getText().toString().isEmpty() && uri == null) {
                Toast.makeText(ChatActivity.this, "Please enter a message or select an image.", Toast.LENGTH_SHORT).show();
            } else {
                webView.setVisibility(View.VISIBLE);

                String inputText = userMsgEdt.getText().toString();

                processTextOnly(inputText);
                updateUserChat1(inputText);

                userMsgEdt.setText("");

            }
        });

    }

    private void readFromAssets() {

        AssetManager assetManager = getAssets();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open("alipay_record.txt")));
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            Log.d("readFromAssets",stringBuilder.toString());

            // 调用方法
            updateUserChat2(Uri.parse("alipay_record.txt"));
            getGPT4Response(stringBuilder.toString());

            reader.close();



        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "读取文件失败", Toast.LENGTH_SHORT).show();
        }
    }



    private String getCurrentType() {
        SharedPreferences preferences = getSharedPreferences("ConsumerPreferences", MODE_PRIVATE);
        return preferences.getString("currentType", "未设置");
    }

    private String getTargetType() {
        SharedPreferences preferences = getSharedPreferences("ConsumerPreferences", MODE_PRIVATE);
        return preferences.getString("targetType", "未设置");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限已授予，启动Intent
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, PICK_FILE);
            } else {
                // 权限被拒绝
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }








    private void getGPT4Response(String prompt) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        // 构建JSON请求体
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("model", "gpt-4-32k");

            // 构建messages数组
            JSONArray messagesArray = new JSONArray();
            JSONObject messageObject = new JSONObject();
            messageObject.put("role", "user");
            messageObject.put("content", prompt +"以上是我这个月的消费记录，目前的消费人格是"+currentType+"想要成为的消费人格是"+targetType+"我想让你给我建议");
            messagesArray.put(messageObject);

            jsonObject.put("messages", messagesArray);
            jsonObject.put("max_tokens", 100); // 设置生成文本的长度
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(ChatActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d("Response code", String.valueOf(response.code()));
                if (response.body() != null) {
                    try {
                        String responseBody = response.body().string();
                        Log.d("Response body", responseBody);
                        // 处理GPT-4 API响应
                        runOnUiThread(() -> {
                            updateChatWithBotResponse(responseBody);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                        runOnUiThread(() -> {
                            Toast.makeText(ChatActivity.this, "处理响应失败", Toast.LENGTH_SHORT).show();
                        });
                    }
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(ChatActivity.this, "请求未成功", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }




    private void processTextOnly(String input) {
        Log.d("jjjjjj","hhhhhh");
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();

                // Create JSON for the body
                JSONObject message = new JSONObject();
                message.put("role", "user");


                message.put("content", input);

                JSONArray messages = new JSONArray();
                messages.put(message);

                JSONObject bodyJson = new JSONObject();
                bodyJson.put("messages", messages);
                bodyJson.put("model", "gpt-3.5-turbo");

                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, bodyJson.toString());

                Request request = new Request.Builder()
                        .url(API_URL)
                        .post(body)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("Content-Type", "application/json")
                        .build();

                okhttp3.Response responseText = client.newCall(request).execute();
                String responseBody = responseText.body().string();

                // Parse JSON response
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(responseBody);
                JsonObject jsonResponse = jsonElement.getAsJsonObject();
                String botResponse = jsonResponse.getAsJsonArray("choices")
                        .get(0).getAsJsonObject()
                        .get("message").getAsJsonObject()
                        .get("content").getAsString();

                updateChatWithBotResponse(botResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }




    private void updateChatWithBotResponse(String botMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                webView.setVisibility(View.GONE);
                Message botMessageObject = new Message(botMessage,BOT_KEY, null);
                messageModalArrayList.add(botMessageObject);
                messageRVAdapter.notifyDataSetChanged();
                chatsRV.scrollToPosition(messageModalArrayList.size() - 1);


            }
        });
    }

    private void updateUserChat1(String input){
        Message botMessageObject = new Message(input,USER_KEY, null);
        messageModalArrayList.add(botMessageObject);
        messageRVAdapter.notifyDataSetChanged();
        chatsRV.scrollToPosition(messageModalArrayList.size() - 1);

    }



    private void updateUserChat2(Uri uri){
        Message messageObject = new Message(null, USER_KEY, uri);
        messageModalArrayList.add(messageObject);
        messageRVAdapter.notifyDataSetChanged();
        chatsRV.scrollToPosition(messageModalArrayList.size() - 1);

    }

    private void addBotWelcomeMessage() {
        String welcomeMessage = "您好！欢迎回到理财大师，今天你想了解：\n" +
                "我的消费情况\n" +
                "读懂理财术语\n" +
                "聊聊金融想法\n" +
                "模拟理财娱乐";
        Message welcomeMsg = new Message(welcomeMessage, BOT_KEY,null);
        messageModalArrayList.add(welcomeMsg);
        messageRVAdapter.notifyItemInserted(messageModalArrayList.size() - 1);
        chatsRV.scrollToPosition(messageModalArrayList.size() - 1);

    }


}