package com.example.csmypart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    private Button sendButton;
    private EditText messageText;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        String currentForum = intent.getStringExtra("current_forum");
        String currentChat = intent.getStringExtra("current_chat");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        sendButton = findViewById(R.id.send_button);
        messageText = findViewById(R.id.message_text);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageContent = messageText.getText().toString();
                String uid = mAuth.getCurrentUser().getUid();

                Message messageObject = new Message();
                messageObject.setUid(uid);
                messageObject.setMessageText(messageContent);

                DatabaseReference messageRef = database.getReference("forums")
                        .child(currentForum)
                        .child(currentChat)
                        .push();

                messageRef.setValue(messageObject);
            }
        });
    }
}
