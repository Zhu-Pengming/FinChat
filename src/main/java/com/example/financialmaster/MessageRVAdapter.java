package com.example.financialmaster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class MessageRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Message> messageModalArrayList;

    private static final int TYPE_MESSAGE_TEXT = 0; // Only text
    private static final int TYPE_MESSAGE_CSV = 1; // Only CSV file

    private String USER_KEY = "user";
    private String BOT_KEY = "bot";

    private Context context;

    public MessageRVAdapter(ArrayList<Message> messageModalArrayList, Context context) {
        this.messageModalArrayList = messageModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_MESSAGE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
                return new TextViewHolder(view);
            case TYPE_MESSAGE_CSV:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_csv, parent, false);
                return new CsvViewHolder(view);
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_item, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageModalArrayList.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_MESSAGE_TEXT:
                TextViewHolder textHolder = (TextViewHolder) holder;
                textHolder.userTV.setText(message.getMessage());
                break;
            case TYPE_MESSAGE_CSV:
                CsvViewHolder csvHolder = (CsvViewHolder) holder;
                String filePath = message.getCsvFilePath();
                csvHolder.csvTV.setText(getFileName(filePath));
                csvHolder.fileSizeTV.setText(getFileSize(filePath) + " KB");
                break;
            case 3:
                BotViewHolder botHolder = (BotViewHolder) holder;
                botHolder.botTV.setText(message.getMessage());
                break;
        }
    }

    private String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }

    private long getFileSize(String filePath) {
        File file = new File(filePath);
        return file.length() / 1024;  // Size in kilobytes
    }



    @Override
    public int getItemViewType(int position) {
        Message message = messageModalArrayList.get(position);
        if (message.getSender().equals(USER_KEY)) {
            if (message.getCsvFilePath() != null) {
                return TYPE_MESSAGE_CSV;
            } else {
                return TYPE_MESSAGE_TEXT;
            }
        } else if (message.getSender().equals(BOT_KEY)) {
            return 3;
        } else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return messageModalArrayList.size();
    }
    private String loadCsvFile(String csvFilePath) {
        // Load the CSV file and return its content as a string.
        // This is a placeholder. You'll need to implement this method based on how you want to handle CSV files.
        return "CSV file content";
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder {
        TextView botTV;
        ImageView botIV;

        @SuppressLint("WrongViewCast")
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botTV = itemView.findViewById(R.id.idTVBot);

        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView userTV;
        ImageView userIV;



        @SuppressLint("WrongViewCast")
        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser2_2);
            userIV = itemView.findViewById(R.id.idIVUser2_1);

        }
    }

    public static class CsvViewHolder extends RecyclerView.ViewHolder {
        TextView csvTV;
        TextView fileSizeTV;  // New TextView for file size

        public CsvViewHolder(@NonNull View itemView) {
            super(itemView);
            csvTV = itemView.findViewById(R.id.idTVUser1_2);
            fileSizeTV = itemView.findViewById(R.id.textView);  // Initialize the new TextView
        }
    }
}
