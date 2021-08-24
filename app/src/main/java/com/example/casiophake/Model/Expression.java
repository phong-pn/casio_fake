package com.example.casiophake.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "expression_table")
public class Expression {
    public Expression(String s, float v) {
        setInput(s);
        setOutput(v);
    }

    public Expression() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String input;
    private float output;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public float getOutput() {
        return output;
    }

    public void setOutput(float output) {
        this.output = output;
    }
}
