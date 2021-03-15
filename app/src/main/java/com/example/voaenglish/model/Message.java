package com.example.voaenglish.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "message_gmail_table")
public class Message {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "subject")
    public String subject;
    @ColumnInfo(name = "message")
    public String message;


}
