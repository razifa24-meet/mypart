package com.example.csmypart;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class message {
    private string message_text;
    private string uid;



    public string getMessage_text() {
        return message_text;
    }

    public string getuid() {
        return uid;
    }

    public void setMessage_text(string message_text) {
        this.message_text = message_text;
    }

    public void uid(string uid) {
        this.uid = uid;
    }
}
