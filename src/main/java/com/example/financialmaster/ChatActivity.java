package com.example.financialmaster;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import okhttp3.*;

public class ChatActivity extends AppCompatActivity {

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

    private int PICK_CSV_FILE =1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if(getSupportActionBar() !=null) {
            getSupportActionBar().hide();
        }

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
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("text/csv");

                startActivityForResult(intent, PICK_CSV_FILE);
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

    private void processCSVOnly(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                String prompt = generatePromptFromRecord(record);
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!目前的人格和目标人格
                getGPT4Response(prompt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CSV_FILE && resultCode == RESULT_OK) {
             uri = data.getData();
            // Handle the selected CSV file

            processCSVOnly(uri);
            updateUserChat2(uri);
        }
    }

    private String generatePromptFromRecord(String[] record) {
        // Concatenate all elements in the record array into a single string
        StringBuilder prompt = new StringBuilder();
        for (String element : record) {
            prompt.append(element).append(" ");
        }
        return prompt.toString().trim(); // Remove trailing space
    }


    private void getGPT4Response(String prompt) {

        client = new OkHttpClient();
        gson = new Gson();

        RequestBody body = new FormBody.Builder()
                .add("model", "gpt-4")
                .add("prompt", prompt)
                .add("max_tokens", "100") // 设置生成文本的长度
                .build();

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String responseBody = response.body().string();
                        // Handle GPT-4 API response
                        runOnUiThread(() -> {
                            updateChatWithBotResponse(responseBody);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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